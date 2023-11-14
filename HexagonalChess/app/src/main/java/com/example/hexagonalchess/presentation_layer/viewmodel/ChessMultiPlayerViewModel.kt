package com.example.hexagonalchess.presentation_layer.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hexagonalchess.data_layer.database.DatabaseGame
import com.example.hexagonalchess.data_layer.model.pieces.ChessPiece
import com.example.hexagonalchess.data_layer.model.tile.Tile
import com.example.hexagonalchess.domain_layer.BoardType
import com.example.hexagonalchess.domain_layer.ChessGameState
import com.example.hexagonalchess.domain_layer.ChessPieceKeyWord
import com.example.hexagonalchess.domain_layer.GameEndMethod
import com.example.hexagonalchess.domain_layer.PieceColor
import com.example.hexagonalchess.domain_layer.PieceType
import com.example.hexagonalchess.domain_layer.TileId
import com.example.hexagonalchess.domain_layer.getTileIndex
import com.example.hexagonalchess.domain_layer.opposite
import com.example.hexagonalchess.domain_layer.piecemove.bishopMove
import com.example.hexagonalchess.domain_layer.piecemove.kingMove
import com.example.hexagonalchess.domain_layer.piecemove.knightMove
import com.example.hexagonalchess.domain_layer.piecemove.pawnMove
import com.example.hexagonalchess.domain_layer.piecemove.queenMove
import com.example.hexagonalchess.domain_layer.piecemove.rookMove
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

    private val _gameState = MutableStateFlow(ChessGameState.PLAYER1_TURN)

    val gameState:StateFlow<ChessGameState> = _gameState

    private val _gameOverMessage = MutableStateFlow("Game Over man Game Over")
    val gameOverMessage:StateFlow<String> = _gameOverMessage

    private var movingTile: Tile? = null

    private var selectingTile: Tile? = null

    private val _backMenu = MutableStateFlow(false)
    val backMenu:StateFlow<Boolean> = _backMenu

    private val _playerOfferedDraw = MutableStateFlow(false)
    val playerOfferedDraw:StateFlow<Boolean> = _playerOfferedDraw

    private val _opponentOfferedDraw = MutableStateFlow(false)
    val opponentOfferedDraw:StateFlow<Boolean> = _opponentOfferedDraw

    private val _playerConsiderResign = MutableStateFlow(false)
    val playerConsiderResign:StateFlow<Boolean> = _playerConsiderResign

    private val _opponentConsiderResign = MutableStateFlow(false)
    val opponentConsiderResign:StateFlow<Boolean> = _opponentConsiderResign

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

    private fun changeTurn() {
        /*_currentTurn.value = _currentTurn.value.opposite()
        database.observeBoardState {
            _chessBoard.value = database.getCurrentBoard()
        }*/
    }

    fun turnOnBackMenu(input: Boolean) {
        _backMenu.value = input
    }

    fun onClickTargeted(targetTile: Tile) {
        for (tiles in _chessBoard.value) {
            tiles.isAPossibleMove = false
        }
        movingTile?.let {
            database.movePieces(it, targetTile)
            changeTurn()
            updateBoard()
        }
    }


    fun resign(color: PieceColor) {
        gameOver(color.opposite(), GameEndMethod.RESIGN)
    }

    fun drawAccepted(color: PieceColor) {

    }

    fun drawRejected(color: PieceColor) {

    }

    fun drawOffered(color: PieceColor) {

    }

    fun turnOnResignMenu() {
        _playerConsiderResign.value = true
    }

    fun turnOffResignMenu() {
        _playerConsiderResign.value = false
    }
    fun promotePawn(keyWord: ChessPieceKeyWord) {

    }

    private fun gameOver(winnerColor: PieceColor, method: GameEndMethod) {
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
    }
}