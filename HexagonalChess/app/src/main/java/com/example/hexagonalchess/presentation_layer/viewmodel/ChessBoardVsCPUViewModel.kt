package com.example.hexagonalchess.presentation_layer.viewmodel

import androidx.lifecycle.ViewModel
import com.example.hexagonalchess.data_layer.model.TilePair
import com.example.hexagonalchess.data_layer.model.blackPawnForwardTwo
import com.example.hexagonalchess.data_layer.model.pieces.ChessPiece
import com.example.hexagonalchess.data_layer.model.tile.Tile
import com.example.hexagonalchess.data_layer.model.whitePawnForwardTwo
import com.example.hexagonalchess.domain_layer.ChessPieceKeyWord
import com.example.hexagonalchess.domain_layer.GameEndMethod
import com.example.hexagonalchess.domain_layer.GameStateVsCpu
import com.example.hexagonalchess.domain_layer.PieceColor
import com.example.hexagonalchess.domain_layer.PieceType
import com.example.hexagonalchess.domain_layer.TileDirections
import com.example.hexagonalchess.domain_layer.TileId
import com.example.hexagonalchess.domain_layer.findTile
import com.example.hexagonalchess.domain_layer.getChessPieceFromKeyWord
import com.example.hexagonalchess.domain_layer.getTileIndex
import com.example.hexagonalchess.domain_layer.piecemove.bishopMove
import com.example.hexagonalchess.domain_layer.piecemove.kingMove
import com.example.hexagonalchess.domain_layer.piecemove.knightMove
import com.example.hexagonalchess.domain_layer.piecemove.pawnMove
import com.example.hexagonalchess.domain_layer.piecemove.queenMove
import com.example.hexagonalchess.domain_layer.piecemove.rookMove
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ChessBoardVsCPUViewModel(
    playerColor: PieceColor,
    board: List<Tile>,

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

    private val listOfPromotionTile = listOf(
        TileId.A8, TileId.B9, TileId.C10, TileId.D11, TileId.E12,
        TileId.F11, TileId.G10, TileId.H9, TileId.I1, TileId.A1,
        TileId.B1, TileId.C1, TileId.D1, TileId.E1, TileId.F1,
        TileId.G1, TileId.H1, TileId.I8
    )

    fun onClickPieces(tile: Tile) {
        if (_gameState.value == GameStateVsCpu.PLAYER_TURN) {
            for (tiles in _chessBoard.value) {
                tiles.isAPossibleMove = false
            }
            tile.chessPiece?.let {
                if (_currentTurn.value == playerTurn) {
                    val result: MutableList<TileId?> = when(it.type) {
                        PieceType.PAWN -> pawnMove(tile, _chessBoard.value)
                        PieceType.KNIGHT -> knightMove(tile, _chessBoard.value)
                        PieceType.BISHOP -> bishopMove(tile, _chessBoard.value)
                        PieceType.ROOK -> rookMove(tile, _chessBoard.value)
                        PieceType.QUEEN -> queenMove(tile, _chessBoard.value)
                        PieceType.KING -> kingMove(tile, _chessBoard.value)
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
            val targetedIndex = getTileIndex(targetedTile.id)
            if (_chessBoard.value[targetedIndex].chessPiece != null ) {
                capturePiece(_chessBoard.value[targetedIndex].chessPiece)
            }
            _chessBoard.value[targetedIndex].chessPiece = movingTile.chessPiece
            val selectedTileIndex = getTileIndex(movingTile.id)
            _chessBoard.value[selectedTileIndex].chessPiece = null
            if (movingTile.chessPiece!!.type == PieceType.PAWN && listOfPromotionTile.contains(targetedTile.id)) {
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
            //checkForCheckmate(_chessBoard.value, _currentTurn.value)
            updateBoard()
        }
    }


    private fun resolveMoveResult(result:List<TileId?>,selectedTile: Tile) {
        for (tileId in result) {
            val index = tileId?.let { getTileIndex(it) }
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
        } else {
            _gameState.value = GameStateVsCpu.CPU_TURN
        }
    }

    private fun enPassantEnable(currentMovePath: TilePair, targetedTile: Tile) {
        when(movingTile?.chessPiece!!.color) {
            PieceColor.BLACK ->
                for (pair in blackPawnForwardTwo) {
                    if (pair == currentMovePath) {
                        val enPassant1 = findTile(targetedTile.id,
                            TileDirections.UPPER_RIGHT,_chessBoard.value)
                        val enPassant2 = findTile(targetedTile.id,
                            TileDirections.UPPER_LEFT,_chessBoard.value)
                        enPassant1?.let {
                            _chessBoard.value[getTileIndex(enPassant1)].chessPiece?.let {
                                if (it.type == PieceType.PAWN)
                                    it.enPassantLeftEnable = true
                            }
                        }
                        enPassant2?.let {
                            _chessBoard.value[getTileIndex(enPassant2)].chessPiece?.let {
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
                            TileDirections.UNDER_RIGHT,_chessBoard.value)
                        val enPassant2 = findTile(targetedTile.id,
                            TileDirections.UNDER_LEFT,_chessBoard.value)
                        enPassant1?.let {
                            _chessBoard.value[getTileIndex(enPassant1)].chessPiece?.let {
                                if (it.type == PieceType.PAWN)
                                    it.enPassantLeftEnable = true
                            }
                        }
                        enPassant2?.let {
                            _chessBoard.value[getTileIndex(enPassant2)].chessPiece?.let {
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
                val attackLeft = findTile(movingTileId, TileDirections.UNDER_LEFT,_chessBoard.value) == targetedTileId
                if (enPassantLeftEnable && attackLeft) {
                    findTile(targetedTileId, TileDirections.TOP,board)?.let {
                        capturePiece(board[getTileIndex(it)].chessPiece)
                        board[getTileIndex(it)].chessPiece = null
                    }
                }
                val attackRight = findTile(movingTileId, TileDirections.UNDER_RIGHT,_chessBoard.value) == targetedTileId
                if (enPassantRightEnable && attackRight) {
                    findTile(targetedTileId, TileDirections.TOP,board)?.let {
                        capturePiece(board[getTileIndex(it)].chessPiece)
                        board[getTileIndex(it)].chessPiece = null
                    }
                }
            }

            PieceColor.WHITE -> {
                val attackLeft = findTile(movingTileId, TileDirections.UPPER_LEFT,_chessBoard.value) == targetedTileId
                if (enPassantLeftEnable && attackLeft) {
                    findTile(targetedTileId, TileDirections.BOTTOM, board)?.let {
                        capturePiece(board[getTileIndex(it)].chessPiece)
                        board[getTileIndex(it)].chessPiece = null
                    }
                }
                val attackRight = findTile(movingTileId, TileDirections.UPPER_RIGHT,_chessBoard.value) == targetedTileId
                if (enPassantRightEnable && attackRight) {
                    findTile(targetedTileId, TileDirections.BOTTOM, board)?.let {
                        capturePiece(board[getTileIndex(it)].chessPiece)
                        board[getTileIndex(it)].chessPiece = null
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
                    _whiteCaptured.value.sortBy { it.materialValue }
                    whiteMaterial += piece.materialValue
                    if (piece.type == PieceType.KING) gameOver(PieceColor.WHITE, method = GameEndMethod.KING_WAS_CAPTURED)
                }
                PieceColor.WHITE -> {
                    _blackCaptured.value.add(piece)
                    blackMaterial += piece.materialValue
                    _blackCaptured.value.sortBy { it.materialValue }
                    if (piece.type == PieceType.KING) gameOver(PieceColor.BLACK, method = GameEndMethod.KING_WAS_CAPTURED)
                }
            }
            _whiteAdvantage.value = whiteMaterial - blackMaterial
            _blackAdvantage.value = blackMaterial - whiteMaterial
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
        _chessBoard.value[getTileIndex(selectingTile!!.id)].chessPiece = getChessPieceFromKeyWord(chosenPromotion)
        _gameState.value = GameStateVsCpu.CPU_TURN
    }

    fun cpuMove(board: List<Tile>) {
        val tileWithCpuPiece = mutableListOf<TileId>()
        for (tile in board) {
            tile.chessPiece?.let { tileChessPiece ->
                if (tileChessPiece.color == cpuColor) {
                    tileWithCpuPiece.add(tile.id)
                }
            }
        }
        for (cpuTile in tileWithCpuPiece) {
            val viableMove = mutableListOf<TileId>()
            board[getTileIndex(cpuTile)].chessPiece?.let {
                when(it.type) {
                    PieceType.KNIGHT -> TODO()
                    PieceType.PAWN -> TODO()
                    PieceType.BISHOP -> TODO()
                    PieceType.ROOK -> TODO()
                    PieceType.QUEEN -> TODO()
                    PieceType.KING -> TODO()
                }
            }
        }
    }
}