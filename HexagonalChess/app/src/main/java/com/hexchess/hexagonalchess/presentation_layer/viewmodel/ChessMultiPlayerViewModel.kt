package com.hexchess.hexagonalchess.presentation_layer.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hexchess.hexagonalchess.R
import com.hexchess.hexagonalchess.data_layer.database.DatabaseGame
import com.hexchess.hexagonalchess.data_layer.model.TilePair
import com.hexchess.hexagonalchess.data_layer.model.pieces.ChessPiece
import com.hexchess.hexagonalchess.data_layer.model.tile.Tile
import com.hexchess.hexagonalchess.domain_layer.BoardType
import com.hexchess.hexagonalchess.domain_layer.ChessGameStateOnline
import com.hexchess.hexagonalchess.domain_layer.ChessPieceKeyWord
import com.hexchess.hexagonalchess.domain_layer.GameEndMethod
import com.hexchess.hexagonalchess.domain_layer.PieceColor
import com.hexchess.hexagonalchess.domain_layer.PieceType
import com.hexchess.hexagonalchess.domain_layer.SimpleDirection
import com.hexchess.hexagonalchess.domain_layer.TileDirections
import com.hexchess.hexagonalchess.domain_layer.TileId
import com.hexchess.hexagonalchess.domain_layer.findTile
import com.hexchess.hexagonalchess.domain_layer.getTileIndex
import com.hexchess.hexagonalchess.domain_layer.piecemove.bishopMove
import com.hexchess.hexagonalchess.domain_layer.piecemove.getForwardTwoPath
import com.hexchess.hexagonalchess.domain_layer.piecemove.kingMove
import com.hexchess.hexagonalchess.domain_layer.piecemove.knightMove
import com.hexchess.hexagonalchess.domain_layer.piecemove.pawnMove
import com.hexchess.hexagonalchess.domain_layer.piecemove.queenMove
import com.hexchess.hexagonalchess.domain_layer.piecemove.rookMove
import com.hexchess.hexagonalchess.domain_layer.playSoundEffect
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class ChessMultiPlayerViewModel(
    private val board: List<Tile>,
    private val database: DatabaseGame,
    private val boardType: BoardType,
    private val context: Context,
): ViewModel() {
    private val _chessBoard = MutableStateFlow(board)
    val chessBoard:StateFlow<List<Tile>> = _chessBoard

    private val gameRoomName: String = database.gameRoomName

    private val _playerName = MutableStateFlow(database.playerName)
    val playerName:StateFlow<String> = _playerName

    private val _opponentName = MutableStateFlow(database.opponentName)
    val opponentName:StateFlow<String> = _opponentName

    private val _playerColor = MutableStateFlow(database.playerColor)
    val playerColor:StateFlow<PieceColor> = _playerColor

    private val _opponentColor = MutableStateFlow(database.opponentColor)
    val opponentColor:StateFlow<PieceColor> = _opponentColor

    private val _playerCaptured = MutableStateFlow(mutableListOf<ChessPiece>())
    val playerCaptured:StateFlow<List<ChessPiece>> = _playerCaptured

    private var playerMaterial = 0

    private val _playerAdvantage = MutableStateFlow(0)
    val playerAdvantage:StateFlow<Int> = _playerAdvantage

    private val _opponentCaptured = MutableStateFlow(mutableListOf<ChessPiece>())
    val opponentCaptured:StateFlow<List<ChessPiece>> = _opponentCaptured

    private var opponentMaterial = 0

    private val _opponentAdvantage = MutableStateFlow(0)
    val opponentAdvantage:StateFlow<Int> = _opponentAdvantage

    private val _currentTurn = MutableStateFlow(PieceColor.WHITE)
    val currentTurn:StateFlow<PieceColor> = _currentTurn

    private val _gameState = MutableStateFlow(ChessGameStateOnline.OPEN)
    val gameState:StateFlow<ChessGameStateOnline> = _gameState

    private val _gameOverMessage = MutableStateFlow("Game Over man Game Over")
    val gameOverMessage:StateFlow<String> = _gameOverMessage

    private var movingTile: Tile? = null

    var selectingTile: Tile? = null

    private val _backMenu = MutableStateFlow(false)
    val backMenu:StateFlow<Boolean> = _backMenu

    private val _opponentOfferedDraw = MutableStateFlow(false)
    val opponentOfferedDraw:StateFlow<Boolean> = _opponentOfferedDraw

    private val _playerConsiderResign = MutableStateFlow(false)
    val playerConsiderResign:StateFlow<Boolean> = _playerConsiderResign

    fun onClickPieces(tile: Tile) {
        var result = listOf<TileId?>()
        if (_currentTurn.value == _playerColor.value) {
            viewModelScope.launch {
                for (tiles in _chessBoard.value) {
                    tiles.isAPossibleMove = false
                }
                tile.chessPiece?.let {
                    if (checkTurn(tile)) {
                        result = when(it.type) {
                            PieceType.PAWN -> pawnMove(tile, _chessBoard.value, boardType)
                            PieceType.KNIGHT -> knightMove(tile, _chessBoard.value, boardType)
                            PieceType.BISHOP -> bishopMove(tile, _chessBoard.value, boardType)
                            PieceType.ROOK -> rookMove(tile, _chessBoard.value, boardType)
                            PieceType.QUEEN -> queenMove(tile, _chessBoard.value, boardType)
                            PieceType.KING -> kingMove(tile, _chessBoard.value, boardType)
                        }
                    }
                }
            }
            resolveMoveResult(result, tile)
            updateBoard()
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun resolveMoveResult(result:List<TileId?>, selectedTile: Tile) {
        movingTile = selectedTile
        val deferredResults = result.map { tileId ->
            GlobalScope.async {
                processTileAsync(tileId, boardType, _chessBoard, selectedTile)
            }
        }

        runBlocking {
            deferredResults.awaitAll()
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
    private fun updateBoard() {
        val updatedChessBoard = _chessBoard.value.map { tile ->
            tile.copy()
        }
        _chessBoard.value = updatedChessBoard.toMutableList()
    }

    private fun checkTurn(selectedTile: Tile):Boolean {
        selectedTile.chessPiece?.let {
            if (it.color == _currentTurn.value) {
                return true
            }
        }
        return false
    }

    fun turnOnBackMenu(input: Boolean) {
        _backMenu.value = input
    }

    fun onClickTargeted(targetedTile: Tile) {
        for (tiles in _chessBoard.value) {
            tiles.isAPossibleMove = false
        }
        selectingTile = targetedTile
        movingTile?.let {
            val currentMovePath = TilePair(
                startingPoint = it.id,
                endPoint = selectingTile!!.id
            )
            it.chessPiece?.let {thisPiece ->
                if (thisPiece.type == PieceType.PAWN) {
                    enPassantEnable(currentMovePath,targetedTile)
                    checkAndPerformEnPassant(
                        movingTileId = movingTile!!.id,
                        targetedTileId = targetedTile.id,
                        enPassantLeftEnable = thisPiece.enPassantLeftEnable,
                        enPassantRightEnable = thisPiece.enPassantRightEnable,
                        color = thisPiece.color,
                        board = _chessBoard.value
                    )
                }
            }
            database.movePieces(it, targetedTile, boardType)
            for (tiles in _chessBoard.value) {
                tiles.chessPiece?.let { thisPiece ->
                    if (thisPiece.color == it.chessPiece!!.color) {
                        thisPiece.enPassantLeftEnable = false
                        thisPiece.enPassantRightEnable = false
                    }
                }
            }

            updateBoard()
            targetedTile.chessPiece?.let { piece ->
                if (piece.type == PieceType.KING) {
                    gameOver(_playerName.value, _opponentName.value, GameEndMethod.KING_WAS_CAPTURED)
                }
            }
        }
    }

    private fun enPassantEnable(currentMovePath: TilePair, targetedTile: Tile) {
        when(movingTile?.chessPiece!!.color) {
            PieceColor.BLACK ->
                for (pair in getForwardTwoPath(boardType, PieceColor.BLACK)) {
                    if (pair == currentMovePath) {
                        val enPassant1 = findTile(targetedTile.id,
                            TileDirections.UPPER_RIGHT,_chessBoard.value, boardType)
                        val enPassant2 = findTile(targetedTile.id,
                            TileDirections.UPPER_LEFT,_chessBoard.value, boardType)
                        enPassant1?.let {
                            _chessBoard.value[getTileIndex(enPassant1, boardType)].chessPiece?.let {
                                if (it.type == PieceType.PAWN) {
                                    database.enableEnPassant(currentMovePath.endPoint, direction = SimpleDirection.LEFT)
                                    //it.enPassantLeftEnable = true
                                }
                            }
                        }
                        enPassant2?.let {
                            _chessBoard.value[getTileIndex(enPassant2, boardType)].chessPiece?.let {
                                if (it.type == PieceType.PAWN) {
                                    database.enableEnPassant(currentMovePath.endPoint, direction = SimpleDirection.RIGHT)
                                }
                            }
                        }
                        break
                    }
                }
            PieceColor.WHITE ->
                for (pair in getForwardTwoPath(boardType, PieceColor.WHITE)) {
                    if (pair == currentMovePath) {
                        val enPassant1 = findTile(targetedTile.id,
                            TileDirections.UNDER_RIGHT,_chessBoard.value, boardType)
                        val enPassant2 = findTile(targetedTile.id,
                            TileDirections.UNDER_LEFT,_chessBoard.value, boardType)
                        enPassant1?.let {
                            _chessBoard.value[getTileIndex(enPassant1, boardType)].chessPiece?.let {
                                if (it.type == PieceType.PAWN) {
                                    database.enableEnPassant(currentMovePath.endPoint, direction = SimpleDirection.LEFT)
                                }
                            }
                        }
                        enPassant2?.let {
                            _chessBoard.value[getTileIndex(enPassant2, boardType)].chessPiece?.let {
                                if (it.type == PieceType.PAWN) {
                                    database.enableEnPassant(currentMovePath.endPoint, direction = SimpleDirection.RIGHT)
                                }
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
                val attackLeft = findTile(movingTileId,
                    TileDirections.UNDER_LEFT,_chessBoard.value, boardType) == targetedTileId
                if (enPassantLeftEnable && attackLeft) {
                    findTile(targetedTileId, TileDirections.TOP,board, boardType)?.let {
                        board[getTileIndex(it, boardType)].chessPiece?.let { targetedEnPassant ->
                            database.capture(targetedEnPassant.keyWord)
                        }
                        database.removePieceByTileID(it)
                        playSoundEffect(context, R.raw.capture)
                    }
                }
                val attackRight = findTile(movingTileId,
                    TileDirections.UNDER_RIGHT,_chessBoard.value, boardType) == targetedTileId
                if (enPassantRightEnable && attackRight) {
                    findTile(targetedTileId, TileDirections.TOP,board, boardType)?.let {
                        board[getTileIndex(it, boardType)].chessPiece?.let { targetedEnPassant ->
                            database.capture(targetedEnPassant.keyWord)
                        }
                        database.removePieceByTileID(it)
                        playSoundEffect(context, R.raw.capture)
                    }
                }
            }

            PieceColor.WHITE -> {
                val attackLeft = findTile(movingTileId,
                    TileDirections.UPPER_LEFT,_chessBoard.value, boardType) == targetedTileId
                if (enPassantLeftEnable && attackLeft) {
                    findTile(targetedTileId, TileDirections.BOTTOM, board, boardType)?.let {
                        board[getTileIndex(it, boardType)].chessPiece?.let { targetedEnPassant ->
                            database.capture(targetedEnPassant.keyWord)
                        }
                        database.removePieceByTileID(it)
                        playSoundEffect(context, R.raw.capture)
                    }
                }
                val attackRight = findTile(movingTileId,
                    TileDirections.UPPER_RIGHT,_chessBoard.value, boardType) == targetedTileId
                if (enPassantRightEnable && attackRight) {
                    findTile(targetedTileId, TileDirections.BOTTOM, board, boardType)?.let {
                        board[getTileIndex(it, boardType)].chessPiece?.let { targetedEnPassant ->
                            database.capture(targetedEnPassant.keyWord)
                        }
                        database.removePieceByTileID(it)
                        playSoundEffect(context, R.raw.capture)
                    }
                }
            }
        }
    }


    fun resign() {
        gameOver(
            winnerName = database.opponentName,
            loserName = database.playerName,
            method = GameEndMethod.RESIGN
        )
    }

    fun drawAccepted() {
        gameOver(_playerName.value, _opponentName.value, method = GameEndMethod.DRAW)
    }

    fun drawRejected() {
        database.drawOffering(false)
    }

    fun drawOffered() {
        database.drawOffering(true)
    }

    fun turnOnResignMenu() {
        _playerConsiderResign.value = true
    }

    fun turnOffResignMenu() {
        _playerConsiderResign.value = false
    }

    fun promotePawn(keyWord: ChessPieceKeyWord, tileId: TileId) {
        database.promote(tileId, keyWord)
    }

    private fun gameOver(winnerName: String, loserName:String,method: GameEndMethod) {
        database.gameOver(winnerName, loserName, method)
    }

    fun updateNameAndColor() {
        _playerName.value = database.playerName
        _opponentName.value = database.opponentName
        _playerColor.value = database.playerColor
        _opponentColor.value = database.opponentColor
    }

    init {
        database.updateBoard = {
            _chessBoard.value = database.getCurrentBoard()
        }
        database.updateCaptured = {
            _playerCaptured.value = database.getPlayerCaptured()
            _opponentCaptured.value = database.getOpponentCaptured()
        }
        database.updateTurn = {
            _currentTurn.value = database.getCurrentTurn()
        }

        database.updateGameOverMessage = {
            _gameOverMessage.value = database.getGameOverMessage()
        }

        database.updateGameState = {
            _gameState.value = database.getCurrentGameState()
        }

        database.updateDrawOffering = {
            _opponentOfferedDraw.value = database.drawOffered
        }
    }
}