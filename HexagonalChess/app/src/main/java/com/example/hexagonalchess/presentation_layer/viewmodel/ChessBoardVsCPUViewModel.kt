package com.example.hexagonalchess.presentation_layer.viewmodel

import android.content.Context
import android.media.MediaPlayer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hexagonalchess.R
import com.example.hexagonalchess.data_layer.model.TilePair
import com.example.hexagonalchess.data_layer.model.blackPawnForwardTwo
import com.example.hexagonalchess.data_layer.model.pieces.ChessPiece
import com.example.hexagonalchess.data_layer.model.tile.Tile
import com.example.hexagonalchess.data_layer.model.whitePawnForwardTwo
import com.example.hexagonalchess.domain_layer.BoardType
import com.example.hexagonalchess.domain_layer.ChessPieceKeyWord
import com.example.hexagonalchess.domain_layer.GameEndMethod
import com.example.hexagonalchess.domain_layer.GameStateVsCpu
import com.example.hexagonalchess.domain_layer.PieceColor
import com.example.hexagonalchess.domain_layer.PieceType
import com.example.hexagonalchess.domain_layer.TileDirections
import com.example.hexagonalchess.domain_layer.TileId
import com.example.hexagonalchess.domain_layer.findTile
import com.example.hexagonalchess.domain_layer.getChessPieceFromKeyWord
import com.example.hexagonalchess.domain_layer.getListOfPromotionTile
import com.example.hexagonalchess.domain_layer.getTileIndex
import com.example.hexagonalchess.domain_layer.piecemove.bishopMove
import com.example.hexagonalchess.domain_layer.piecemove.kingMove
import com.example.hexagonalchess.domain_layer.piecemove.knightMove
import com.example.hexagonalchess.domain_layer.piecemove.pawnMove
import com.example.hexagonalchess.domain_layer.piecemove.queenMove
import com.example.hexagonalchess.domain_layer.piecemove.rookMove
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.random.Random

class ChessBoardVsCPUViewModel(
    playerColor: PieceColor,
    board: List<Tile>,
    val boardType: BoardType,
    val context: Context
): ViewModel() {
    private val _chessBoard = MutableStateFlow(board)
    val chessBoard: StateFlow<List<Tile>> = _chessBoard

    private val playerTurn = playerColor
    private val cpuColor = if (playerColor == PieceColor.WHITE) PieceColor.BLACK else PieceColor.WHITE
    private val initGameState = if (playerColor == PieceColor.WHITE) GameStateVsCpu.PLAYER_TURN else GameStateVsCpu.CPU_TURN

    private val _currentTurn = MutableStateFlow(playerColor)
    val currentTurn: StateFlow<PieceColor> = _currentTurn

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

    private val _gameState = MutableStateFlow(initGameState)
    val gameState:StateFlow<GameStateVsCpu> = _gameState

    private val _gameOverMessage = MutableStateFlow("Game Over man Game Over")
    val gameOverMessage:StateFlow<String> = _gameOverMessage

    private var movingTile: Tile? = null

    private var selectingTile: Tile? = null

    fun onClickPieces(tile: Tile) {
        for (tiles in _chessBoard.value) {
            tiles.isAPossibleMove = false
        }
        if (_gameState.value == GameStateVsCpu.PLAYER_TURN ) {
            tile.chessPiece?.let {
                if (_currentTurn.value == playerTurn && it.color == playerTurn) {
                    val result: MutableList<TileId?> = when(it.type) {
                        PieceType.PAWN -> pawnMove(tile, _chessBoard.value, boardType)
                        PieceType.KNIGHT -> knightMove(tile, _chessBoard.value, boardType)
                        PieceType.BISHOP -> bishopMove(tile, _chessBoard.value, boardType)
                        PieceType.ROOK -> rookMove(tile, _chessBoard.value, boardType)
                        PieceType.QUEEN -> queenMove(tile, _chessBoard.value, boardType)
                        PieceType.KING -> kingMove(tile, _chessBoard.value, boardType)
                    }.toMutableList()

                    resolveMoveResult(result, tile)
                    updateBoard()
                }
            }
        }
    }

    fun onClickTargeted(targetedTile: Tile) {
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
            if (movingTile.chessPiece!!.type == PieceType.PAWN && getListOfPromotionTile(boardType).contains(targetedTile.id)) {
                _gameState.value = GameStateVsCpu.PROMOTE
            }
            val currentMovePath = TilePair(
                startingPoint = movingTile.id,
                endPoint = selectingTile!!.id
            )
            movingTile.chessPiece?.let {
                if (it.type == PieceType.PAWN) {
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
            updateBoard()
            if (_gameState.value == GameStateVsCpu.CPU_TURN) {
                cpuMove(_chessBoard.value,context)
            }
        }
    }


    private fun resolveMoveResult(result:List<TileId?>,selectedTile: Tile) {
        for (tileId in result) {
            val index = tileId?.let { getTileIndex(it, boardType) }
            index?.let { currentIndex ->
                if (_chessBoard.value[currentIndex].chessPiece == null) {
                    _chessBoard.value[currentIndex].isAPossibleMove = true
                }
                _chessBoard.value[currentIndex].chessPiece?.let {
                    selectedTile.chessPiece?.let {
                        _chessBoard.value[currentIndex].isAPossibleMove = true
                    }
                }
            }
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
        if (_gameState.value == GameStateVsCpu.CPU_TURN) {
            _gameState.value = GameStateVsCpu.PLAYER_TURN
        } else if (_gameState.value == GameStateVsCpu.PLAYER_TURN) {
            _gameState.value = GameStateVsCpu.CPU_TURN
        }
    }

    private fun enPassantEnable(currentMovePath: TilePair, targetedTile: Tile) {
        when(movingTile?.chessPiece!!.color) {
            PieceColor.BLACK ->
                for (pair in blackPawnForwardTwo) {
                    if (pair == currentMovePath) {
                        val enPassant1 = findTile(targetedTile.id,
                            TileDirections.UPPER_RIGHT,_chessBoard.value, boardType)
                        val enPassant2 = findTile(targetedTile.id,
                            TileDirections.UPPER_LEFT,_chessBoard.value, boardType)
                        enPassant1?.let {
                            _chessBoard.value[getTileIndex(enPassant1, boardType)].chessPiece?.let {
                                if (it.type == PieceType.PAWN)
                                    it.enPassantLeftEnable = true
                            }
                        }
                        enPassant2?.let {
                            _chessBoard.value[getTileIndex(enPassant2, boardType)].chessPiece?.let {
                                if (it.type == PieceType.PAWN)
                                    it.enPassantRightEnable = true
                            }
                        }
                        break
                    }
                }
            PieceColor.WHITE ->
                for (pair in whitePawnForwardTwo) {
                    if (pair == currentMovePath) {
                        val enPassant1 = findTile(targetedTile.id,
                            TileDirections.UNDER_RIGHT,_chessBoard.value, boardType)
                        val enPassant2 = findTile(targetedTile.id,
                            TileDirections.UNDER_LEFT,_chessBoard.value, boardType)
                        enPassant1?.let {
                            _chessBoard.value[getTileIndex(enPassant1, boardType)].chessPiece?.let {
                                if (it.type == PieceType.PAWN)
                                    it.enPassantLeftEnable = true
                            }
                        }
                        enPassant2?.let {
                            _chessBoard.value[getTileIndex(enPassant2, boardType)].chessPiece?.let {
                                if (it.type == PieceType.PAWN)
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
                val attackLeft = findTile(movingTileId, TileDirections.UNDER_LEFT,_chessBoard.value, boardType) == targetedTileId
                if (enPassantLeftEnable && attackLeft) {
                    findTile(targetedTileId, TileDirections.TOP,board, boardType)?.let {
                        capturePiece(board[getTileIndex(it, boardType)].chessPiece)
                        board[getTileIndex(it, boardType)].chessPiece = null
                    }
                }
                val attackRight = findTile(movingTileId, TileDirections.UNDER_RIGHT,_chessBoard.value, boardType) == targetedTileId
                if (enPassantRightEnable && attackRight) {
                    findTile(targetedTileId, TileDirections.TOP,board, boardType)?.let {
                        capturePiece(board[getTileIndex(it, boardType)].chessPiece)
                        board[getTileIndex(it, boardType)].chessPiece = null
                    }
                }
            }

            PieceColor.WHITE -> {
                val attackLeft = findTile(movingTileId, TileDirections.UPPER_LEFT,_chessBoard.value, boardType) == targetedTileId
                if (enPassantLeftEnable && attackLeft) {
                    findTile(targetedTileId, TileDirections.BOTTOM, board, boardType)?.let {
                        capturePiece(board[getTileIndex(it, boardType)].chessPiece)
                        board[getTileIndex(it, boardType)].chessPiece = null
                    }
                }
                val attackRight = findTile(movingTileId, TileDirections.UPPER_RIGHT,_chessBoard.value, boardType) == targetedTileId
                if (enPassantRightEnable && attackRight) {
                    findTile(targetedTileId, TileDirections.BOTTOM, board, boardType)?.let {
                        capturePiece(board[getTileIndex(it, boardType)].chessPiece)
                        board[getTileIndex(it, boardType)].chessPiece = null
                    }
                }
            }
        }
    }

    private fun capturePiece(piece:ChessPiece?) {
        piece?.let {
            when(piece.color) {
                PieceColor.BLACK -> {
                    _whiteCaptured.value.add(piece)
                    whiteMaterial += piece.materialValue
                    }
                PieceColor.WHITE -> {
                    _blackCaptured.value.add(piece)
                    blackMaterial += piece.materialValue
                    }
            }
            if (piece.type == PieceType.KING) gameOver(piece.color, method = GameEndMethod.KING_WAS_CAPTURED)
            _whiteAdvantage.value = whiteMaterial - blackMaterial
            _blackAdvantage.value = blackMaterial - whiteMaterial
            println("captured ${piece.type}")
            println(_gameState.value)
        }

    }

    private fun gameOver(winnerColor: PieceColor, method: GameEndMethod) {
        _gameState.value = GameStateVsCpu.GAME_OVER
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

    fun playerPromotePawn(chosenPromotion : ChessPieceKeyWord) {
        _chessBoard.value[getTileIndex(selectingTile!!.id, boardType)].chessPiece = getChessPieceFromKeyWord(chosenPromotion)
        _gameState.value = GameStateVsCpu.CPU_TURN
    }

    private fun cpuMove(board: List<Tile>,context:Context) {
        val random = Random(System.currentTimeMillis())
        val soundEffect = MediaPlayer.create(context, R.raw.move)
        soundEffect.setOnCompletionListener {
            soundEffect.release()
        }
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
            board[getTileIndex(cpuTile, boardType)].chessPiece?.let {
                 viableMove.addAll(when(it.type) {
                     PieceType.KNIGHT -> knightMove(board[getTileIndex(cpuTile, boardType)],board, boardType)
                     PieceType.PAWN -> pawnMove(board[getTileIndex(cpuTile, boardType)],board, boardType)
                     PieceType.BISHOP -> bishopMove(board[getTileIndex(cpuTile, boardType)],board, boardType)
                     PieceType.ROOK -> rookMove(board[getTileIndex(cpuTile, boardType)],board, boardType)
                     PieceType.QUEEN -> queenMove(board[getTileIndex(cpuTile, boardType)],board, boardType)
                     PieceType.KING -> kingMove(board[getTileIndex(cpuTile, boardType)],board, boardType)
                 }.toMutableList())
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
        board[getTileIndex(chosenTile, boardType)].chessPiece?.let {
            movesInChosenTile.addAll(
                when(it.type) {
                    PieceType.KNIGHT -> knightMove(board[getTileIndex(chosenTile, boardType)],board, boardType)
                    PieceType.PAWN -> pawnMove(board[getTileIndex(chosenTile, boardType)],board, boardType)
                    PieceType.BISHOP -> bishopMove(board[getTileIndex(chosenTile, boardType)],board, boardType)
                    PieceType.ROOK -> rookMove(board[getTileIndex(chosenTile, boardType)],board, boardType)
                    PieceType.QUEEN -> queenMove(board[getTileIndex(chosenTile, boardType)],board, boardType)
                    PieceType.KING -> kingMove(board[getTileIndex(chosenTile, boardType)],board, boardType)
                }
            )
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
        movingTile.chessPiece?.let {
            if (it.type == PieceType.PAWN && getListOfPromotionTile(boardType).contains(targetedTile.id)) {
                val possibleResult = when(it.color){
                    PieceColor.WHITE -> listOf(ChessPieceKeyWord.WHITE_KNIGHT,ChessPieceKeyWord.WHITE_BISHOP,ChessPieceKeyWord.WHITE_ROOK,ChessPieceKeyWord.WHITE_QUEEN)
                    PieceColor.BLACK -> listOf(ChessPieceKeyWord.BLACK_KNIGHT,ChessPieceKeyWord.BLACK_BISHOP,ChessPieceKeyWord.BLACK_ROOK,ChessPieceKeyWord.BLACK_QUEEN)
                }
                _chessBoard.value[targetedIndex].chessPiece = getChessPieceFromKeyWord(possibleResult.random())
            }
        }
        val currentMovePath = TilePair(
            startingPoint = movingTile.id,
            endPoint = selectingTile!!.id
        )

        movingTile.chessPiece?.let {
            if (it.type == PieceType.PAWN) {
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

    init {
        if (playerColor == PieceColor.BLACK) {
            cpuMove(_chessBoard.value,context)
            _gameState.value = GameStateVsCpu.PLAYER_TURN
            _currentTurn.value = playerTurn
        }
    }
}