package com.example.hexagonalchess.presentation_layer.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hexagonalchess.data_layer.database.DatabaseGame
import com.example.hexagonalchess.data_layer.model.pieces.ChessPiece
import com.example.hexagonalchess.data_layer.model.tile.Tile
import com.example.hexagonalchess.domain_layer.BoardType
import com.example.hexagonalchess.domain_layer.ChessGameState
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
    private val gameRoomName: String,
    private val playerName: String,
    private val opponentName: String,
    private val playerColor: PieceColor,
    private val database: DatabaseGame,
    private val boardType: BoardType,
    private val context: Context,
): ViewModel() {
    private val _chessBoard = MutableStateFlow(board)
    val chessBoard:StateFlow<List<Tile>> = _chessBoard

    private val opponentColor = playerColor.opposite()

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
        if (_gameState.value == ChessGameState.PLAYER1_TURN || _gameState.value == ChessGameState.PLAYER2_TURN) {
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
        _chessBoard.value = updatedChessBoard
    }

    private fun checkTurn(selectedTile: Tile):Boolean {
        selectedTile.chessPiece?.let {
            if (it.color == _currentTurn.value) {
                return true
            }
        }
        return false
    }
    fun initializeGame() {
        database.initializeGame(
            board = board
        )
    }
}