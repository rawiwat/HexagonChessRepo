package com.example.hexagonalchess.presentation_layer.viewmodel

import android.app.GameState
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hexagonalchess.R
import com.example.hexagonalchess.data_layer.model.TilePair
import com.example.hexagonalchess.data_layer.model.blackPawnForwardTwo
import com.example.hexagonalchess.data_layer.model.pieces.ChessPiece
import com.example.hexagonalchess.data_layer.model.tile.Tile
import com.example.hexagonalchess.data_layer.model.whitePawnForwardTwo
import com.example.hexagonalchess.domain_layer.BoardType
import com.example.hexagonalchess.domain_layer.ChessGameState
import com.example.hexagonalchess.domain_layer.ChessPieceKeyWord
import com.example.hexagonalchess.domain_layer.GameEndMethod
import com.example.hexagonalchess.domain_layer.GameMode
import com.example.hexagonalchess.domain_layer.PieceColor
import com.example.hexagonalchess.domain_layer.PieceType.BISHOP
import com.example.hexagonalchess.domain_layer.PieceType.KING
import com.example.hexagonalchess.domain_layer.PieceType.KNIGHT
import com.example.hexagonalchess.domain_layer.PieceType.PAWN
import com.example.hexagonalchess.domain_layer.PieceType.QUEEN
import com.example.hexagonalchess.domain_layer.PieceType.ROOK
import com.example.hexagonalchess.domain_layer.TileDirections
import com.example.hexagonalchess.domain_layer.TileId
import com.example.hexagonalchess.domain_layer.findTile
import com.example.hexagonalchess.domain_layer.getChessPieceFromKeyWord
import com.example.hexagonalchess.domain_layer.getListOfPromotionTile
import com.example.hexagonalchess.domain_layer.getTileIndex
import com.example.hexagonalchess.domain_layer.opposite
import com.example.hexagonalchess.domain_layer.piecemove.bishopMove
import com.example.hexagonalchess.domain_layer.piecemove.kingMove
import com.example.hexagonalchess.domain_layer.piecemove.knightMove
import com.example.hexagonalchess.domain_layer.piecemove.pawnMove
import com.example.hexagonalchess.domain_layer.piecemove.queenMove
import com.example.hexagonalchess.domain_layer.piecemove.rookMove
import com.example.hexagonalchess.domain_layer.playSoundEffect
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.random.Random

class ChessBoardViewModel(
    allTiles: List<Tile>,
    val boardType: BoardType,
    val context: Context,
    val gameMode: GameMode,
    val playerColor: PieceColor
) : ViewModel() {
    private val _chessBoard = MutableStateFlow(allTiles)
    val chessBoard: StateFlow<List<Tile>> = _chessBoard

    private val _whiteCaptured = MutableStateFlow(mutableListOf<ChessPiece>())
    val whiteCaptured:StateFlow<List<ChessPiece>> = _whiteCaptured

    private var whiteMaterial = 0

    private val _whiteAdvantage = MutableStateFlow(0)
    val whiteAdvantage:StateFlow<Int> = _whiteAdvantage

    private val _blackCaptured = MutableStateFlow(mutableListOf<ChessPiece>())
    val blackCaptured:StateFlow<List<ChessPiece>> = _blackCaptured

    private var blackMaterial = 0

    private val _blackAdvantage = MutableStateFlow(0)
    val blackAdvantage:StateFlow<Int> = _blackAdvantage

    private val _currentTurn = MutableStateFlow(PieceColor.WHITE)
    val currentTurn:StateFlow<PieceColor> = _currentTurn

    private val _gameState = MutableStateFlow(
            when(gameMode) {
                GameMode.LOCAL -> ChessGameState.PLAYER1_TURN
                GameMode.ONLINE -> ChessGameState.PLAYER1_TURN
                GameMode.CPU -> when(playerColor){
                    PieceColor.WHITE -> ChessGameState.PLAYER1_TURN
                    PieceColor.BLACK -> ChessGameState.CPU_TURN
            }
        }
    )

    val gameState:StateFlow<ChessGameState> = _gameState

    private val _gameOverMessage = MutableStateFlow("Game Over man Game Over")
    val gameOverMessage:StateFlow<String> = _gameOverMessage

    private var movingTile:Tile? = null

    private var selectingTile:Tile? = null

    private val cpuColor = playerColor.opposite()

    private val _backMenu = MutableStateFlow(false)
    val backMenu:StateFlow<Boolean> = _backMenu

    private val _whiteOfferedDraw = MutableStateFlow(false)
    val whiteOfferedDraw:StateFlow<Boolean> = _whiteOfferedDraw

    private val _blackOfferedDraw = MutableStateFlow(false)
    val blackOfferedDraw:StateFlow<Boolean> = _blackOfferedDraw

    fun onClickPieces(tile: Tile) {
        var result = listOf<TileId?>()
        if (_gameState.value == ChessGameState.PLAYER1_TURN || _gameState.value == ChessGameState.PLAYER1_TURN) {
            viewModelScope.launch {
                for (tiles in _chessBoard.value) {
                    tiles.isAPossibleMove = false
                }
                tile.chessPiece?.let {
                    if (checkTurn(tile)) {
                        result = when(it.type) {
                            PAWN -> pawnMove(tile, _chessBoard.value, boardType)
                            KNIGHT -> knightMove(tile, _chessBoard.value, boardType)
                            BISHOP -> bishopMove(tile, _chessBoard.value, boardType)
                            ROOK -> rookMove(tile, _chessBoard.value, boardType)
                            QUEEN -> queenMove(tile, _chessBoard.value, boardType)
                            KING -> kingMove(tile, _chessBoard.value, boardType)
                        }
                    }
                }
            }
            resolveMoveResult(result, tile)
            updateBoard()
        }
    }

    fun onClickTargeted(targetedTile: Tile) {
        playSoundEffect(context,R.raw.move)
        movingTile?.let { movingTile ->
            selectingTile = targetedTile
            for (tile in _chessBoard.value) {
                tile.isAPossibleMove = false
            }
            val targetedIndex = getTileIndex(targetedTile.id, boardType)
            if (_chessBoard.value[targetedIndex].chessPiece != null ) {
                capturePiece(_chessBoard.value[targetedIndex].chessPiece)
            }
            _chessBoard.value[targetedIndex].chessPiece = movingTile.chessPiece
            val selectedTileIndex = getTileIndex(movingTile.id, boardType)
            _chessBoard.value[selectedTileIndex].chessPiece = null
            if (movingTile.chessPiece!!.type == PAWN && getListOfPromotionTile(boardType, movingTile.chessPiece!!.color).contains(targetedTile.id)) {
                if (_gameState.value == ChessGameState.PLAYER1_TURN) {
                    _gameState.value = ChessGameState.PLAYER1_PROMOTE
                } else if (_gameState.value == ChessGameState.PLAYER2_TURN) {
                    _gameState.value = ChessGameState.PLAYER2_PROMOTE
                }
            }

            val currentMovePath = TilePair(
                startingPoint = movingTile.id,
                endPoint = selectingTile!!.id
            )
            movingTile.chessPiece?.let {
                if (it.type == PAWN) {
                    enPassantEnable(currentMovePath,targetedTile)
                    checkAndPerformEnPassant(
                        movingTileId = movingTile.id,
                        targetedTileId = targetedTile.id,
                        enPassantLeftEnable = it.enPassantLeftEnable,
                        enPassantRightEnable = it.enPassantRightEnable,
                        color = it.color,
                        board = _chessBoard.value
                    )
                }
            }

            changeTurn()
            //checkForCheckmate(_chessBoard.value, _currentTurn.value)
            updateBoard()
        }
    }
    private fun processTileAsync(tileId: TileId?, boardType: BoardType, chessBoard: MutableStateFlow<List<Tile>>, selectedTile: Tile) {
        tileId?.let {
            val index = getTileIndex(it, boardType)
            if (chessBoard.value[index].chessPiece == null) {
                chessBoard.value[index].isAPossibleMove = true
            }
            chessBoard.value[index].chessPiece?.let {
                selectedTile.chessPiece?.let {
                    chessBoard.value[index].isAPossibleMove = true
                }
            }
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun resolveMoveResult(result:List<TileId?>, selectedTile: Tile) {
        val deferredResults = result.map { tileId ->
            GlobalScope.async {
                processTileAsync(tileId, boardType, _chessBoard, selectedTile)
            }
        }

        runBlocking {
            deferredResults.awaitAll()
        }

        movingTile = selectedTile
    }

    private fun updateBoard() {
        val updatedChessBoard = _chessBoard.value.map { tile ->
            tile.copy()
        }
        _chessBoard.value = updatedChessBoard
    }

    private fun changeTurn() {
        when(gameMode) {
            GameMode.CPU -> {
                if (_gameState.value == ChessGameState.CPU_TURN) {
                    _gameState.value = ChessGameState.PLAYER1_TURN
                } else if (_gameState.value == ChessGameState.PLAYER1_TURN) {
                    _gameState.value = ChessGameState.CPU_TURN
                    cpuMove(_chessBoard.value, context)
                }
            }
            else -> {
                if (_gameState.value == ChessGameState.PLAYER2_TURN) {
                    _gameState.value = ChessGameState.PLAYER1_TURN
                } else if (_gameState.value == ChessGameState.PLAYER1_TURN) {
                    _gameState.value = ChessGameState.PLAYER2_TURN
                    cpuMove(_chessBoard.value, context)
                }
            }
        }
        when(_currentTurn.value) {
            PieceColor.BLACK -> {
                _currentTurn.value = PieceColor.WHITE
                for (tile in _chessBoard.value) {
                    tile.chessPiece?.let {
                        if (it.color == PieceColor.BLACK) {
                            it.enPassantLeftEnable = false
                            it.enPassantRightEnable = false
                        }
                    }
                }
            }

            PieceColor.WHITE -> {
                _currentTurn.value = PieceColor.BLACK
                for (tile in _chessBoard.value) {
                    tile.chessPiece?.let {
                        if (it.color == PieceColor.WHITE) {
                            it.enPassantLeftEnable = false
                            it.enPassantRightEnable = false
                        }
                    }
                }
            }
        }
    }

    private fun checkTurn(selectedTile: Tile):Boolean {
        selectedTile.chessPiece?.let {
            if (it.color == _currentTurn.value) {
                return true
            }
        }
        return false
    }


    private fun enPassantEnable(currentMovePath: TilePair, targetedTile: Tile) {
        when(movingTile?.chessPiece!!.color) {
            PieceColor.BLACK ->
                for (pair in blackPawnForwardTwo) {
                    if (pair == currentMovePath) {
                        val enPassant1 = findTile(targetedTile.id,TileDirections.UPPER_RIGHT,_chessBoard.value, boardType)
                        val enPassant2 = findTile(targetedTile.id,TileDirections.UPPER_LEFT,_chessBoard.value, boardType)
                        enPassant1?.let {
                            _chessBoard.value[getTileIndex(enPassant1, boardType)].chessPiece?.let {
                                if (it.type == PAWN)
                                    it.enPassantLeftEnable = true
                            }
                        }
                        enPassant2?.let {
                            _chessBoard.value[getTileIndex(enPassant2, boardType)].chessPiece?.let {
                                if (it.type == PAWN)
                                    it.enPassantRightEnable = true
                            }
                        }
                        break
                    }
                }
            PieceColor.WHITE ->
                for (pair in whitePawnForwardTwo) {
                    if (pair == currentMovePath) {
                        val enPassant1 = findTile(targetedTile.id,TileDirections.UNDER_RIGHT,_chessBoard.value, boardType)
                        val enPassant2 = findTile(targetedTile.id,TileDirections.UNDER_LEFT,_chessBoard.value, boardType)
                        enPassant1?.let {
                            _chessBoard.value[getTileIndex(enPassant1, boardType)].chessPiece?.let {
                                if (it.type == PAWN)
                                    it.enPassantLeftEnable = true
                            }
                        }
                        enPassant2?.let {
                            _chessBoard.value[getTileIndex(enPassant2, boardType)].chessPiece?.let {
                                if (it.type == PAWN)
                                    it.enPassantRightEnable = true
                            }
                        }
                        break
                    }
                }
        }
    }

    private fun checkAndPerformEnPassant(
        movingTileId: TileId,
        targetedTileId: TileId,
        enPassantLeftEnable: Boolean,
        enPassantRightEnable: Boolean,
        color: PieceColor,
        board: List<Tile>
    ) {
        when(color) {
            PieceColor.BLACK -> {
                val attackLeft = findTile(movingTileId,TileDirections.UNDER_LEFT,_chessBoard.value, boardType) == targetedTileId
                if (enPassantLeftEnable && attackLeft) {
                    findTile(targetedTileId,TileDirections.TOP,board, boardType)?.let {
                        capturePiece(board[getTileIndex(it, boardType)].chessPiece)
                        board[getTileIndex(it, boardType)].chessPiece = null
                        playSoundEffect(context,R.raw.capture)
                    }
                }
                val attackRight = findTile(movingTileId,TileDirections.UNDER_RIGHT,_chessBoard.value, boardType) == targetedTileId
                if (enPassantRightEnable && attackRight) {
                    findTile(targetedTileId,TileDirections.TOP,board, boardType)?.let {
                        capturePiece(board[getTileIndex(it, boardType)].chessPiece)
                        board[getTileIndex(it, boardType)].chessPiece = null
                        playSoundEffect(context,R.raw.capture)
                    }
                }
            }

            PieceColor.WHITE -> {
                val attackLeft = findTile(movingTileId,TileDirections.UPPER_LEFT,_chessBoard.value, boardType) == targetedTileId
                if (enPassantLeftEnable && attackLeft) {
                    findTile(targetedTileId,TileDirections.BOTTOM, board, boardType)?.let {
                        capturePiece(board[getTileIndex(it, boardType)].chessPiece)
                        board[getTileIndex(it, boardType)].chessPiece = null
                        playSoundEffect(context,R.raw.capture)
                    }
                }
                val attackRight = findTile(movingTileId,TileDirections.UPPER_RIGHT,_chessBoard.value, boardType) == targetedTileId
                if (enPassantRightEnable && attackRight) {
                    findTile(targetedTileId,TileDirections.BOTTOM, board, boardType)?.let {
                        capturePiece(board[getTileIndex(it, boardType)].chessPiece)
                        board[getTileIndex(it, boardType)].chessPiece = null
                        playSoundEffect(context,R.raw.capture)
                    }
                }
            }
        }
    }

    fun capturePiece(piece:ChessPiece?) {
        piece?.let {
            when(piece.color) {
                PieceColor.BLACK -> {
                    _whiteCaptured.value.add(piece)
                    _whiteCaptured.value.sortBy { it.materialValue }
                    whiteMaterial += piece.materialValue
                    if (piece.type == KING) gameOver(PieceColor.WHITE, method = GameEndMethod.KING_WAS_CAPTURED)
                }
                PieceColor.WHITE -> {
                    _blackCaptured.value.add(piece)
                    blackMaterial += piece.materialValue
                    _blackCaptured.value.sortBy { it.materialValue }
                    if (piece.type == KING) gameOver(PieceColor.BLACK, method = GameEndMethod.KING_WAS_CAPTURED)
                }
            }
            _whiteAdvantage.value = whiteMaterial - blackMaterial
            _blackAdvantage.value = blackMaterial - whiteMaterial
            playSoundEffect(context,R.raw.capture)
        }
    }

    private fun gameOver(winnerColor: PieceColor, method:GameEndMethod) {
        _gameState.value = ChessGameState.GAME_OVER
        val winnerColorInMessage = when(winnerColor) {
            PieceColor.BLACK -> "Black"
            PieceColor.WHITE -> "White"
        }

        val loserColorInMessage = when(winnerColor) {
            PieceColor.BLACK -> "White"
            PieceColor.WHITE -> "Black"
        }
        val gameEndMessage = when(method){
            GameEndMethod.KING_WAS_CAPTURED -> "$winnerColorInMessage Wins\n$loserColorInMessage King was captured"
            GameEndMethod.DRAW -> "$winnerColorInMessage accept the draw offer"
            GameEndMethod.RESIGN -> "$loserColorInMessage resign"
            GameEndMethod.CHECKMATE -> "$winnerColorInMessage Wins\nCheckmate"
        }
        _gameOverMessage.value = gameEndMessage
    }

    /*private fun checkForCheckmate(board: List<Tile>, kingColor: PieceColor) {
        val viableMove = mutableListOf<TileId?>()
        for (tile in board) {
            tile.chessPiece?.let { currentPiece ->
                if (currentPiece.color == kingColor) {
                    viableMove.addAll(
                        when(currentPiece.type) {
                            KNIGHT -> knightMove(tile, board)
                            PAWN -> pawnMove(tile, board)
                            BISHOP -> bishopMove(tile, board)
                            ROOK -> rookMove(tile, board)
                            QUEEN -> queenMove(tile, board)
                            KING -> kingMove(tile, board)
                        }
                    )
                }
            }
        }

        val iterator = viableMove.iterator()
        while (iterator.hasNext()){
            val currentMove = iterator.next()
            if (currentMove == null) {
                iterator.remove()
            }
        }

        val kingWasAttacked: Boolean = wasKingAttacked(board, kingColor)

        if (kingWasAttacked && viableMove.isEmpty()) {
            val opposingColor = when(kingColor){
                PieceColor.BLACK -> PieceColor.WHITE
                PieceColor.WHITE -> PieceColor.BLACK
            }
            gameOver(opposingColor, GameEndMethod.CHECKMATE)
        }
    }*/

    fun promotePawn(chosenPromotion : ChessPieceKeyWord) {
        _chessBoard.value[getTileIndex(selectingTile!!.id, boardType)].chessPiece = getChessPieceFromKeyWord(chosenPromotion)
        playSoundEffect(context, R.raw.promote)
        if (gameMode == GameMode.LOCAL) {
            if (_gameState.value == ChessGameState.PLAYER1_PROMOTE) {
                _gameState.value = ChessGameState.PLAYER2_TURN
            } else if (_gameState.value == ChessGameState.PLAYER2_PROMOTE) {
                _gameState.value = ChessGameState.PLAYER1_TURN
            }
        } else if (gameMode == GameMode.CPU) {
            _gameState.value = ChessGameState.CPU_TURN
            cpuMove(_chessBoard.value, context)
        }
    }

    private fun cpuMove(board: List<Tile>,context:Context) {
        val random = Random(System.currentTimeMillis())
        playSoundEffect(context, R.raw.move)
        val tileWithCpuPiece = mutableListOf<TileId>()
        val tileWithMovablePiece = mutableListOf<TileId>()
        for (tile in board) {
            tile.chessPiece?.let { tileChessPiece ->
                if (tileChessPiece.color == cpuColor) {
                    tileWithCpuPiece.add(tile.id)
                }
            }
        }

        for (cpuTile in tileWithCpuPiece) {
            val viableMove = mutableListOf<TileId?>()
            viewModelScope.launch {
                board[getTileIndex(cpuTile, boardType)].chessPiece?.let {
                    viableMove.addAll(when(it.type) {
                        KNIGHT -> knightMove(board[getTileIndex(cpuTile, boardType)],board, boardType)
                        PAWN -> pawnMove(board[getTileIndex(cpuTile, boardType)],board, boardType)
                        BISHOP -> bishopMove(board[getTileIndex(cpuTile, boardType)],board, boardType)
                        ROOK -> rookMove(board[getTileIndex(cpuTile, boardType)],board, boardType)
                        QUEEN -> queenMove(board[getTileIndex(cpuTile, boardType)],board, boardType)
                        KING -> kingMove(board[getTileIndex(cpuTile, boardType)],board, boardType)
                    }.toMutableList())
                }
            }

            val iterator = viableMove.iterator()
            while (iterator.hasNext()){
                val currentMove = iterator.next()
                if (currentMove == null) {
                    iterator.remove()
                }
            }
            if (viableMove.isNotEmpty()) {
                tileWithMovablePiece.add(cpuTile)
            }
        }
        val chosenTile = tileWithMovablePiece[random.nextInt(tileWithMovablePiece.size)]
        val movesInChosenTile = mutableListOf<TileId?>()
        viewModelScope.launch {
            board[getTileIndex(chosenTile, boardType)].chessPiece?.let {
                movesInChosenTile.addAll(
                    when(it.type) {
                        KNIGHT -> knightMove(board[getTileIndex(chosenTile, boardType)],board, boardType)
                        PAWN -> pawnMove(board[getTileIndex(chosenTile, boardType)],board, boardType)
                        BISHOP -> bishopMove(board[getTileIndex(chosenTile, boardType)],board, boardType)
                        ROOK -> rookMove(board[getTileIndex(chosenTile, boardType)],board, boardType)
                        QUEEN -> queenMove(board[getTileIndex(chosenTile, boardType)],board, boardType)
                        KING -> kingMove(board[getTileIndex(chosenTile, boardType)],board, boardType)
                    }
                )
            }
        }

        val iterator = movesInChosenTile.iterator()
        while (iterator.hasNext()){
            val currentMove = iterator.next()
            if (currentMove == null) {
                iterator.remove()
            }
        }
        val chosenMove = movesInChosenTile[random.nextInt(movesInChosenTile.size)]

        val targetedTile = board[getTileIndex(chosenMove!!, boardType)]
        val movingTile = board[getTileIndex(chosenTile, boardType)]
        selectingTile = targetedTile
        for (tile in _chessBoard.value) {
            tile.isAPossibleMove = false
        }
        val targetedIndex = getTileIndex(targetedTile.id, boardType)
        if (_chessBoard.value[targetedIndex].chessPiece != null ) {
            capturePiece(_chessBoard.value[targetedIndex].chessPiece)
        }
        _chessBoard.value[targetedIndex].chessPiece = movingTile.chessPiece
        val selectedTileIndex = getTileIndex(movingTile.id, boardType)
        _chessBoard.value[selectedTileIndex].chessPiece = null
        targetedTile.chessPiece?.let {
            if (it.type == PAWN && getListOfPromotionTile(boardType, it.color).contains(chosenMove)) {
                cpuPromote(it.color, targetedTile)
            }
        }
        val currentMovePath = TilePair(
            startingPoint = movingTile.id,
            endPoint = selectingTile!!.id
        )

        movingTile.chessPiece?.let {
            if (it.type == PAWN) {
                enPassantEnable(currentMovePath,targetedTile)
                checkAndPerformEnPassant(
                    movingTileId = movingTile.id,
                    targetedTileId = targetedTile.id,
                    enPassantLeftEnable = it.enPassantLeftEnable,
                    enPassantRightEnable = it.enPassantRightEnable,
                    color = it.color,
                    board = _chessBoard.value
                )
            }
        }
        println("CpuTiles:$tileWithCpuPiece")
        println("CpuTiles that can move:$tileWithMovablePiece")
        println("Chosen CpuTiles:$chosenTile")
        println("Chosen CpuTiles Moves:$movesInChosenTile")
        println("Chosen Move:$chosenMove")
        changeTurn()
        updateBoard()
    }

    private fun cpuPromote(color: PieceColor, targetedTile: Tile) {
        playSoundEffect(context, R.raw.promote)
        val possibleResult = when(color) {
            PieceColor.WHITE -> listOf(ChessPieceKeyWord.WHITE_KNIGHT,ChessPieceKeyWord.WHITE_BISHOP,ChessPieceKeyWord.WHITE_ROOK,ChessPieceKeyWord.WHITE_QUEEN)
            PieceColor.BLACK -> listOf(ChessPieceKeyWord.BLACK_KNIGHT,ChessPieceKeyWord.BLACK_BISHOP,ChessPieceKeyWord.BLACK_ROOK,ChessPieceKeyWord.BLACK_QUEEN)
        }
        val chosenPromotion = possibleResult.random()
        targetedTile.chessPiece = getChessPieceFromKeyWord(chosenPromotion)
        println("promote to $chosenPromotion")
    }

    fun turnOnBackMenu(input: Boolean) {
        _backMenu.value = input
    }

    fun cpuStart() {
        if (_gameState.value == ChessGameState.CPU_TURN) {
            cpuMove(_chessBoard.value, context)
        }
    }

    fun drawOffered(color: PieceColor) {
        when(color) {
            PieceColor.WHITE -> _whiteOfferedDraw.value = true
            PieceColor.BLACK -> _blackOfferedDraw.value = false
        }
    }

    fun drawAccepted(color: PieceColor) {
        gameOver(color, GameEndMethod.DRAW)
    }

    fun resign(color: PieceColor) {
        gameOver(color.opposite(), GameEndMethod.RESIGN)
    }
}


