package com.example.hexagonalchess.presentation_layer.viewmodel

import androidx.lifecycle.ViewModel
import com.example.hexagonalchess.data_layer.model.pieces.ChessPiece
import com.example.hexagonalchess.domain_layer.PieceColor
import com.example.hexagonalchess.domain_layer.TileDirections
import com.example.hexagonalchess.domain_layer.TileId
import com.example.hexagonalchess.data_layer.model.tile.Tile
import com.example.hexagonalchess.domain_layer.PieceType.*
import com.example.hexagonalchess.domain_layer.getTileIndex
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ChessBoardViewModel(
    allTiles:List<Tile>,
    //private val database: Database
) : ViewModel() {
    private val _chessBoard = MutableStateFlow(allTiles)
    val chessBoard: StateFlow<List<Tile>> = _chessBoard

    private val _whiteCaptured = MutableStateFlow(mutableListOf<ChessPiece>())
    val whiteCaptured:StateFlow<List<ChessPiece>> = _whiteCaptured

    private val _blackCaptured = MutableStateFlow(mutableListOf<ChessPiece>())
    val blackCaptured:StateFlow<List<ChessPiece>> = _blackCaptured

    private val _currentTurn = MutableStateFlow(PieceColor.WHITE)
    val currentTurn:StateFlow<PieceColor> = _currentTurn

    private var movingTile:Tile? = null

    private fun findTile(id: TileId, direction: TileDirections, board: List<Tile>): TileId? {
        val targetIndex = getTileIndex(id)
        val targetedTile = board[targetIndex]
        return when(direction) {
                    TileDirections.TOP -> targetedTile.topTile
                    TileDirections.UPPER_RIGHT -> targetedTile.upperRightTile
                    TileDirections.UNDER_RIGHT -> targetedTile.underRightTile
                    TileDirections.BOTTOM -> targetedTile.bottomTile
                    TileDirections.UNDER_LEFT -> targetedTile.underLeftTile
                    TileDirections.UPPER_LEFT -> targetedTile.upperLeftTile
        }
    }

    fun onClickPieces(tile: Tile) {
        for (tiles in _chessBoard.value) {
            tiles.isAPossibleMove = false
        }
        tile.chessPiece?.let {
            if (checkTurn(tile)) {
                var result:List<TileId?> = when(it.type) {
                    PAWN -> pawnMove(tile, _chessBoard.value)
                    KNIGHT -> knightMove(tile, _chessBoard.value)
                    BISHOP -> bishopMove(tile, _chessBoard.value)
                    ROOK -> rookMove(tile, _chessBoard.value)
                    QUEEN -> queenMove(tile, _chessBoard.value)
                    KING -> kingMove(tile, _chessBoard.value)
                }
                //result = filterIllegalMove(it, tile.id, result.toMutableList())
                result -= filterIllegalMove(tile,result)

                resolveMoveResult(result, tile)
                updateBoard()
            }
        }
    }

    fun onClickTargeted(targetedTile: Tile) {
        movingTile?.let {
            for (tile in _chessBoard.value) {
                tile.isAPossibleMove = false
            }
            val targetedIndex = getTileIndex(targetedTile.id)
            _chessBoard.value[targetedIndex].chessPiece = it.chessPiece
            val selectedTileIndex = getTileIndex(it.id)
            _chessBoard.value[selectedTileIndex].chessPiece = null
            changeTurn()
            updateBoard()
        }
    }

    private fun containPiece(tileId: TileId?): Boolean {
        val targetIndex = tileId?.let { getTileIndex(it) }
        targetIndex?.let {
            if (_chessBoard.value[targetIndex].chessPiece != null ) {
                return true
            }
        }
        /*for (tile in _chessBoard.value) {
            if (tileId == tile.id && tile.chessPiece != null) {
                return true
            }
        }*/
        return false
    }

    private fun pawnMove(selectedTile: Tile, board:List<Tile>):List<TileId?> {
        val result = mutableListOf<TileId?>()
        if (selectedTile.chessPiece!!.color == PieceColor.WHITE) {
            val forward1 = findTile(selectedTile.id, TileDirections.TOP, board)
            val forward2 = findTile(forward1!!, TileDirections.TOP, board)

            if (!containPiece(forward1)) {
                result.add(forward1)
            }

            if(!containPiece(forward1) && !containPiece(forward2)) {
                when(selectedTile.id) {
                    TileId.A1 -> result.add(forward2)
                    TileId.B2 -> result.add(forward2)
                    TileId.C3 -> result.add(forward2)
                    TileId.D4 -> result.add(forward2)
                    TileId.E5 -> result.add(forward2)
                    TileId.F4 -> result.add(forward2)
                    TileId.G3 -> result.add(forward2)
                    TileId.H2 -> result.add(forward2)
                    TileId.I1 -> result.add(forward2)
                    else -> { }
                }
            }

            val attack1 = findTile(selectedTile.id, TileDirections.UPPER_LEFT, board)
            val attack2 = findTile(selectedTile.id, TileDirections.UPPER_RIGHT, board)
            val attack1Index = attack1?.let { getTileIndex(it) }
            attack1Index?.let {
                if (board[it].chessPiece != null && board[it].chessPiece!!.color == PieceColor.BLACK) {
                    result.add(attack1)
                }
            }

            val attack2Index = attack2?.let { getTileIndex(it) }
            attack2Index?.let {
                if (board[it].chessPiece != null && board[it].chessPiece!!.color == PieceColor.BLACK) {
                    result.add(attack2)
                }
            }
            /*for (tileId in result) {
                val index = tileId?.let { getTileIndex(it) }
                index?.let {
                    _chessBoard.value[it].isAPossibleMove = true
                }
            }*/
        } else {
            val forward1 = findTile(selectedTile.id, TileDirections.BOTTOM, board)
            val forward2 = findTile(forward1!!, TileDirections.BOTTOM, board)

            if (!containPiece(forward1)) {
                result.add(forward1)
            }

            if(!containPiece(forward1) && !containPiece(forward2)) {
                when(selectedTile.id) {
                    TileId.A8 -> result.add(forward2)
                    TileId.B8 -> result.add(forward2)
                    TileId.C8 -> result.add(forward2)
                    TileId.D8 -> result.add(forward2)
                    TileId.E8 -> result.add(forward2)
                    TileId.F8 -> result.add(forward2)
                    TileId.G8 -> result.add(forward2)
                    TileId.H8 -> result.add(forward2)
                    TileId.I8 -> result.add(forward2)
                    else -> { }
                }
            }

            val attack1 = findTile(selectedTile.id, TileDirections.UNDER_LEFT, board)
            val attack2 = findTile(selectedTile.id, TileDirections.UNDER_RIGHT, board)
            val attack1Index = attack1?.let { getTileIndex(it) }
            attack1Index?.let {
                if (board[it].chessPiece != null && board[it].chessPiece!!.color == PieceColor.WHITE) {
                    result.add(attack1)
                }
            }

            val attack2Index = attack2?.let { getTileIndex(it) }
            attack2Index?.let {
                if (board[it].chessPiece != null && board[it].chessPiece!!.color == PieceColor.WHITE) {
                    result.add(attack2)
                }
            }

            /*for (tiles in _chessBoard.value) {
                if (tiles.id == attack2 && tiles.chessPiece != null && tiles.chessPiece!!.color == PieceColor.BLACK) {
                    result.add(attack2)
                }
            }
            for (tileId in result) {
                val index = tileId?.let { getTileIndex(it) }
                index?.let {
                    _chessBoard.value[it].isAPossibleMove = true
                }
            }*/
        }
        return result
    }

    private fun knightMove(selectedTile: Tile, board:List<Tile>):List<TileId?> {
        val result = mutableListOf<TileId?>()

        var move1 = findTile(selectedTile.id,TileDirections.TOP, board)
        move1?.let { move1 = findTile(it,TileDirections.TOP, board) }
        move1?.let { move1 = findTile(it,TileDirections.UPPER_LEFT, board) }
        result.add(move1)

        var move2 = findTile(selectedTile.id,TileDirections.TOP, board)
        move2?.let { move2 = findTile(it,TileDirections.TOP, board) }
        move2?.let { move2 = findTile(it,TileDirections.UPPER_RIGHT, board) }
        result.add(move2)

        var move3 = findTile(selectedTile.id,TileDirections.BOTTOM, board)
        move3?.let { move3 = findTile(it,TileDirections.BOTTOM, board) }
        move3?.let { move3 = findTile(it,TileDirections.UNDER_LEFT, board) }
        result.add(move3)

        var move4 = findTile(selectedTile.id,TileDirections.BOTTOM, board)
        move4?.let { move4 = findTile(it,TileDirections.BOTTOM, board) }
        move4?.let { move4 = findTile(it,TileDirections.UNDER_RIGHT, board) }
        result.add(move4)

        var move5 = findTile(selectedTile.id,TileDirections.TOP, board)
        move5?.let { move5 = findTile(it,TileDirections.UPPER_LEFT, board) }
        move5?.let { move5 = findTile(it,TileDirections.UPPER_LEFT, board) }
        result.add(move5)

        var move6 = findTile(selectedTile.id,TileDirections.TOP, board)
        move6?.let { move6 = findTile(it,TileDirections.UPPER_RIGHT, board) }
        move6?.let { move6 = findTile(it,TileDirections.UPPER_RIGHT, board) }
        result.add(move6)

        var move7= findTile(selectedTile.id,TileDirections.BOTTOM, board)
        move7?.let { move7= findTile(it,TileDirections.UNDER_LEFT, board) }
        move7?.let { move7= findTile(it,TileDirections.UNDER_LEFT, board) }
        result.add(move7)

        var move8 = findTile(selectedTile.id,TileDirections.BOTTOM, board)
        move8?.let { move8 = findTile(it,TileDirections.UNDER_RIGHT, board) }
        move8?.let { move8 = findTile(it,TileDirections.UNDER_RIGHT, board) }
        result.add(move8)

        var move9 = findTile(selectedTile.id,TileDirections.UPPER_LEFT, board)
        move9?.let { move9 = findTile(it,TileDirections.UPPER_LEFT, board) }
        move9?.let { move9 = findTile(it,TileDirections.UNDER_LEFT, board) }
        result.add(move9)

        var move10 = findTile(selectedTile.id,TileDirections.UNDER_LEFT, board)
        move10?.let { move10 = findTile(it,TileDirections.UNDER_LEFT, board) }
        move10?.let { move10 = findTile(it,TileDirections.UPPER_LEFT, board) }
        result.add(move10)

        var move11 = findTile(selectedTile.id,TileDirections.UPPER_RIGHT, board)
        move11?.let { move11 = findTile(it,TileDirections.UPPER_RIGHT, board) }
        move11?.let { move11 = findTile(it,TileDirections.UNDER_RIGHT, board) }
        result.add(move11)

        var move12 = findTile(selectedTile.id,TileDirections.UNDER_RIGHT, board)
        move12?.let { move12 = findTile(it,TileDirections.UNDER_RIGHT, board) }
        move12?.let { move12 = findTile(it,TileDirections.UPPER_RIGHT, board) }
        result.add(move12)

        return result
    }

    private fun rookMove(selectedTile: Tile, board: List<Tile>):List<TileId?> {
        val result = mutableListOf<TileId?>()
        result.addAll(getAllTileInDirection(selectedTile, TileDirections.TOP, board))
        result.addAll(getAllTileInDirection(selectedTile, TileDirections.UPPER_RIGHT, board))
        result.addAll(getAllTileInDirection(selectedTile, TileDirections.UNDER_RIGHT, board))
        result.addAll(getAllTileInDirection(selectedTile, TileDirections.BOTTOM, board))
        result.addAll(getAllTileInDirection(selectedTile, TileDirections.UNDER_LEFT, board))
        result.addAll(getAllTileInDirection(selectedTile, TileDirections.UPPER_LEFT, board))
        return result
    }

    private fun bishopMove(selectedTile: Tile, board: List<Tile>):List<TileId?> {
        val result = mutableListOf<TileId?>()
        result.addAll(
            getAllTileInMultiDirection(
                selectedTile,
                listOf(
                    TileDirections.TOP,
                    TileDirections.UPPER_RIGHT
                ),
                board
            )
        )
        result.addAll(
            getAllTileInMultiDirection(
                selectedTile,
                listOf(
                    TileDirections.TOP,
                    TileDirections.UPPER_LEFT
                ),
                board
            )
        )
        result.addAll(
            getAllTileInMultiDirection(
                selectedTile,
                listOf(
                    TileDirections.UPPER_RIGHT,
                    TileDirections.UNDER_RIGHT
                ),
                board
            )
        )

        result.addAll(
            getAllTileInMultiDirection(
                selectedTile,
                listOf(
                    TileDirections.BOTTOM,
                    TileDirections.UNDER_RIGHT
                ),
                board
            )
        )
        result.addAll(
            getAllTileInMultiDirection(
                selectedTile,
                listOf(
                    TileDirections.BOTTOM,
                    TileDirections.UNDER_LEFT
                ),
                board
            )
        )
        result.addAll(
            getAllTileInMultiDirection(
                selectedTile,
                listOf(
                    TileDirections.UNDER_LEFT,
                    TileDirections.UPPER_LEFT
                ),
                board
            )
        )
        return result
    }

    private fun queenMove(selectedTile: Tile, board: List<Tile>):List<TileId?> {
        return bishopMove(selectedTile, board) + rookMove(selectedTile, board)
    }

    private fun kingMove(selectedTile: Tile, board: List<Tile>):List<TileId?> {
        val result = mutableListOf<TileId>()
        val move1 = findTile(selectedTile.id,TileDirections.TOP, board)
        val move2 = findTile(selectedTile.id,TileDirections.UPPER_RIGHT, board)
        val move3 = findTile(selectedTile.id,TileDirections.UNDER_RIGHT, board)
        val move4 = findTile(selectedTile.id,TileDirections.BOTTOM, board)
        val move5 = findTile(selectedTile.id,TileDirections.UNDER_LEFT, board)
        val move6 = findTile(selectedTile.id,TileDirections.UPPER_LEFT, board)

        move1?.let { result.add(it) }
        move2?.let { result.add(it) }
        move3?.let { result.add(it) }
        move4?.let { result.add(it) }
        move5?.let { result.add(it) }
        move6?.let { result.add(it) }
        val opposingColor = if (selectedTile.chessPiece!!.color == PieceColor.BLACK) { PieceColor.WHITE } else { PieceColor.BLACK }

        val moveLeadToCheck = checkKingUnavailableMove(opposingColor, board)
        val iterator = result.iterator()
        while (iterator.hasNext()) {
            val item = iterator.next()
            if (moveLeadToCheck.contains(item)) {
                iterator.remove()
            }
        }   
        return result
    }

    private fun resolveMoveResult(result:List<TileId?>,selectedTile: Tile) {
        for (tileId in result) {
            val index = tileId?.let { getTileIndex(it) }
            index?.let { currentIndex ->
                if (_chessBoard.value[currentIndex].chessPiece == null) {
                    _chessBoard.value[currentIndex].isAPossibleMove = true
                }
                _chessBoard.value[currentIndex].chessPiece?.let { piece ->
                    selectedTile.chessPiece?.let {
                        if (piece.color != it.color) {
                            _chessBoard.value[currentIndex].isAPossibleMove = true
                        }
                    }
                }
            }
        }
        movingTile = selectedTile
    }

    private fun getAllTileInDirection(selectedTile: Tile, direction: TileDirections, board: List<Tile>):List<TileId?> {
        val result = mutableListOf<TileId?>()
        val firstTileId = findTile(selectedTile.id,direction, board)
        firstTileId?.let {
            var currentTile = board[getTileIndex(it)]
            currentTile.chessPiece?.let { adjacentTileWithPiece ->
                if (adjacentTileWithPiece.color != selectedTile.chessPiece!!.color) {
                    result.add(currentTile.id)
                }
                return result
            }
            result.add(currentTile.id)
            for (i in 1 until 12) {
                val nextTile = findTile(currentTile.id,direction, board)
                nextTile?.let { nextTileId ->
                    currentTile = board[getTileIndex(nextTileId)]
                    result.add(nextTile)
                }
                if (currentTile.chessPiece != null) {
                    break
                }
            }
        }
        return result
    }

    private fun getAllTileInMultiDirection(selectedTile: Tile, directions: List<TileDirections>, board: List<Tile>):List<TileId?> {
        val result = mutableListOf<TileId?>()
        var firstTileId:TileId? = selectedTile.id
        for (direction in directions) {
            firstTileId = firstTileId?.let { findTile(it, direction, board) }
        }
        firstTileId?.let {
            var currentTile = board[getTileIndex(it)]
            currentTile.chessPiece?.let { adjacentTileWithPiece ->
                if (adjacentTileWithPiece.color != selectedTile.chessPiece!!.color) {
                    result.add(currentTile.id)
                }
                return result
            }
            result.add(currentTile.id)
            for (i in 1 until 12) {
                var nextTile:TileId? = currentTile.id
                for (direction in directions) {
                    nextTile = nextTile?.let { nextTileId-> findTile(nextTileId, direction, board) }
                }
                nextTile?.let { nextTileId ->
                    currentTile = board[getTileIndex(nextTileId)]
                    result.add(nextTile)
                }
                if (currentTile.chessPiece != null) {
                    break
                }
            }
        }

        return result
    }

    private fun checkPawnAttack(tile: Tile, board:List<Tile>):List<TileId?> {
        return if(tile.chessPiece!!.color == PieceColor.BLACK) {
            listOf(findTile(tile.id,TileDirections.UNDER_LEFT, board),findTile(tile.id,TileDirections.UNDER_RIGHT, board))
        } else {
            listOf(findTile(tile.id,TileDirections.UPPER_LEFT, board),findTile(tile.id,TileDirections.UPPER_RIGHT, board))
        }
    }

    private fun updateBoard() {
        val updatedChessBoard = _chessBoard.value.map { tile ->
            tile.copy()
        }
        _chessBoard.value = updatedChessBoard
    }

    private fun changeTurn() {
        when(_currentTurn.value) {
            PieceColor.BLACK -> _currentTurn.value = PieceColor.WHITE
            PieceColor.WHITE -> _currentTurn.value = PieceColor.BLACK
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

    private fun checkKingUnavailableMove(pieceColor: PieceColor, board: List<Tile>):List<TileId?> {
        val result = mutableListOf<TileId?>()
        for (tile in _chessBoard.value) {
            tile.chessPiece?.let {
                if (it.color == pieceColor) {
                    result += when(it.type) {
                        KNIGHT -> knightMove(tile, board)
                        PAWN -> checkPawnAttack(tile, board)
                        BISHOP -> bishopMove(tile, board)
                        ROOK -> rookMove(tile, board)
                        QUEEN -> queenMove(tile, board)
                        KING ->
                            listOf(
                                findTile(tile.id,TileDirections.TOP, board),
                                findTile(tile.id,TileDirections.UPPER_RIGHT, board),
                                findTile(tile.id,TileDirections.UNDER_RIGHT, board),
                                findTile(tile.id,TileDirections.BOTTOM, board),
                                findTile(tile.id,TileDirections.UNDER_LEFT, board),
                                findTile(tile.id,TileDirections.UPPER_LEFT, board)
                            )
                    }
                }
            }
        }
        return result
    }

    private fun filterIllegalMove(selectedTile: Tile, moves: List<TileId?>):List<TileId?> {
        val illegalMoves = mutableListOf<TileId?>()
        val initialMockBoard = mutableListOf<Tile>()
        for (tile in _chessBoard.value) {
            initialMockBoard.add(tile.copy())
        }
        var mockBoard = initialMockBoard
        for (move in moves) {
            move?.let {
                mockBoard[getTileIndex(move)].chessPiece = selectedTile.chessPiece
                mockBoard[getTileIndex(selectedTile.id)].chessPiece = null
                if(checkIfKingIsAttacked(selectedTile.chessPiece!!.color,mockBoard)) {
                    illegalMoves.add(move)
                }
                mockBoard = initialMockBoard
            }
        }
        return illegalMoves
    }

    private fun checkIfKingIsAttacked(kingColor:PieceColor,board: List<Tile>):Boolean {
        for (tile in board) {
            val possibleMove = mutableListOf<TileId?>()
            tile.chessPiece?.let { currentPiece ->
                if (currentPiece.color != kingColor ) {
                    possibleMove.addAll(
                        when(currentPiece.type) {
                            KNIGHT -> knightMove(tile, board)
                            PAWN -> checkPawnAttack(tile, board)
                            BISHOP -> bishopMove(tile, board)
                            ROOK -> rookMove(tile, board)
                            QUEEN -> queenMove(tile, board)
                            KING ->
                                listOf(
                                    findTile(tile.id,TileDirections.TOP, board),
                                    findTile(tile.id,TileDirections.UPPER_RIGHT, board),
                                    findTile(tile.id,TileDirections.UNDER_RIGHT, board),
                                    findTile(tile.id,TileDirections.BOTTOM, board),
                                    findTile(tile.id,TileDirections.UNDER_LEFT, board),
                                    findTile(tile.id,TileDirections.UPPER_LEFT, board)
                                )
                        }
                    )
                    for (move in possibleMove){
                        move?.let {
                            board[getTileIndex(move)].chessPiece?.let { thisPiece ->
                                if (thisPiece.type == KING && thisPiece.color != kingColor) {
                                    return true
                                }
                            }
                        }
                    }
                }
            }
        }
        return false
    }
    private fun checkForCheckMate(color: PieceColor) {}

    private fun enPassantEnable() {

    }

    private fun gameOver() {

    }
}


