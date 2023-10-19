package com.example.hexagonalchess.presentation_layer.viewmodel

import androidx.lifecycle.ViewModel
import com.example.hexagonalchess.data_layer.model.pieces.ChessPiece
import com.example.hexagonalchess.domain_layer.PieceColor
import com.example.hexagonalchess.domain_layer.PieceType
import com.example.hexagonalchess.domain_layer.TileDirections
import com.example.hexagonalchess.domain_layer.TileId
import com.example.hexagonalchess.data_layer.model.tile.Tile
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

    private var selectedTile:Tile? = null

    private fun findTile(id: TileId, direction: TileDirections): TileId? {
        val targetIndex = getTileIndex(id)
        val targetedTile = _chessBoard.value[targetIndex]
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
        if (checkTurn(tile)) {
            for (tiles in _chessBoard.value) {
                tiles.isAPossibleMove = false
            }
            selectedTile = tile
            if (wasPinned(tile)) { return }
            val result:List<TileId?> = when (tile.chessPiece!!.type) {
                PieceType.PAWN -> pawnMove(tile)
                PieceType.KNIGHT -> knightMove(tile)
                PieceType.BISHOP -> bishopMove(tile)
                PieceType.ROOK -> rookMove(tile)
                PieceType.QUEEN -> queenMove(tile)
                PieceType.KING -> kingMove(tile)
            }
            resolveMoveResult(result)
        }
        updateBoard()
    }

    fun onClickTargeted(targetedTile: Tile) {
        for (tile in _chessBoard.value) {
            tile.isAPossibleMove = false
        }
        val targetedIndex = getTileIndex(targetedTile.id)
        _chessBoard.value[targetedIndex].chessPiece = selectedTile!!.chessPiece
        val selectedTileIndex = getTileIndex(selectedTile!!.id)
        _chessBoard.value[selectedTileIndex].chessPiece = null
        changeTurn()
        updateBoard()
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

    private fun pawnMove(selectedTile: Tile):List<TileId?> {
        val result = mutableListOf<TileId?>()
        if (selectedTile.chessPiece!!.color == PieceColor.WHITE) {
            val forward1 = findTile(selectedTile.id, TileDirections.TOP)
            val forward2 = findTile(forward1!!, TileDirections.TOP)

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

            val attack1 = findTile(selectedTile.id, TileDirections.UPPER_LEFT)
            val attack2 = findTile(selectedTile.id, TileDirections.UPPER_RIGHT)
            val attack1Index = attack1?.let { getTileIndex(it) }
            attack1Index?.let {
                if (_chessBoard.value[it].chessPiece != null && _chessBoard.value[it].chessPiece!!.color == PieceColor.BLACK) {
                    result.add(attack1)
                }
            }

            val attack2Index = attack2?.let { getTileIndex(it) }
            attack2Index?.let {
                if (_chessBoard.value[it].chessPiece != null && _chessBoard.value[it].chessPiece!!.color == PieceColor.BLACK) {
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
            val forward1 = findTile(selectedTile.id, TileDirections.BOTTOM)
            val forward2 = findTile(forward1!!, TileDirections.BOTTOM)

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

            val attack1 = findTile(selectedTile.id, TileDirections.UNDER_LEFT)
            val attack2 = findTile(selectedTile.id, TileDirections.UNDER_RIGHT)
            val attack1Index = attack1?.let { getTileIndex(it) }
            attack1Index?.let {
                if (_chessBoard.value[it].chessPiece != null && _chessBoard.value[it].chessPiece!!.color == PieceColor.WHITE) {
                    result.add(attack1)
                }
            }

            val attack2Index = attack2?.let { getTileIndex(it) }
            attack2Index?.let {
                if (_chessBoard.value[it].chessPiece != null && _chessBoard.value[it].chessPiece!!.color == PieceColor.WHITE) {
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

    private fun knightMove(selectedTile: Tile):List<TileId?> {
        val result = mutableListOf<TileId?>()

        var move1 = findTile(selectedTile.id,TileDirections.TOP)
        move1?.let { move1 = findTile(it,TileDirections.TOP) }
        move1?.let { move1 = findTile(it,TileDirections.UPPER_LEFT) }
        result.add(move1)

        var move2 = findTile(selectedTile.id,TileDirections.TOP)
        move2?.let { move2 = findTile(it,TileDirections.TOP) }
        move2?.let { move2 = findTile(it,TileDirections.UPPER_RIGHT) }
        result.add(move2)

        var move3 = findTile(selectedTile.id,TileDirections.BOTTOM)
        move3?.let { move3 = findTile(it,TileDirections.BOTTOM) }
        move3?.let { move3 = findTile(it,TileDirections.UNDER_LEFT) }
        result.add(move3)

        var move4 = findTile(selectedTile.id,TileDirections.BOTTOM)
        move4?.let { move4 = findTile(it,TileDirections.BOTTOM) }
        move4?.let { move4 = findTile(it,TileDirections.UNDER_RIGHT) }
        result.add(move4)

        var move5 = findTile(selectedTile.id,TileDirections.TOP)
        move5?.let { move5 = findTile(it,TileDirections.UPPER_LEFT) }
        move5?.let { move5 = findTile(it,TileDirections.UPPER_LEFT) }
        result.add(move5)

        var move6 = findTile(selectedTile.id,TileDirections.TOP)
        move6?.let { move6 = findTile(it,TileDirections.UPPER_RIGHT) }
        move6?.let { move6 = findTile(it,TileDirections.UPPER_RIGHT) }
        result.add(move6)

        var move7= findTile(selectedTile.id,TileDirections.BOTTOM)
        move7?.let { move7= findTile(it,TileDirections.UNDER_LEFT) }
        move7?.let { move7= findTile(it,TileDirections.UNDER_LEFT) }
        result.add(move7)

        var move8 = findTile(selectedTile.id,TileDirections.BOTTOM)
        move8?.let { move8 = findTile(it,TileDirections.UNDER_RIGHT) }
        move8?.let { move8 = findTile(it,TileDirections.UNDER_RIGHT) }
        result.add(move8)

        var move9 = findTile(selectedTile.id,TileDirections.UPPER_LEFT)
        move9?.let { move9 = findTile(it,TileDirections.UPPER_LEFT) }
        move9?.let { move9 = findTile(it,TileDirections.UNDER_LEFT) }
        result.add(move9)

        var move10 = findTile(selectedTile.id,TileDirections.UNDER_LEFT)
        move10?.let { move10 = findTile(it,TileDirections.UNDER_LEFT) }
        move10?.let { move10 = findTile(it,TileDirections.UPPER_LEFT) }
        result.add(move10)

        var move11 = findTile(selectedTile.id,TileDirections.UPPER_RIGHT)
        move11?.let { move11 = findTile(it,TileDirections.UPPER_RIGHT) }
        move11?.let { move11 = findTile(it,TileDirections.UNDER_RIGHT) }
        result.add(move11)

        var move12 = findTile(selectedTile.id,TileDirections.UNDER_RIGHT)
        move12?.let { move12 = findTile(it,TileDirections.UNDER_RIGHT) }
        move12?.let { move12 = findTile(it,TileDirections.UPPER_RIGHT) }
        result.add(move12)

        return result
    }

    private fun rookMove(selectedTile: Tile):List<TileId?> {
        val result = mutableListOf<TileId?>()
        result.addAll(getAllTileInDirection(selectedTile, TileDirections.TOP))
        result.addAll(getAllTileInDirection(selectedTile, TileDirections.UPPER_RIGHT))
        result.addAll(getAllTileInDirection(selectedTile, TileDirections.UNDER_RIGHT))
        result.addAll(getAllTileInDirection(selectedTile, TileDirections.BOTTOM))
        result.addAll(getAllTileInDirection(selectedTile, TileDirections.UNDER_LEFT))
        result.addAll(getAllTileInDirection(selectedTile, TileDirections.UPPER_LEFT))
        return result
    }

    private fun bishopMove(selectedTile: Tile):List<TileId?> {
        val result = mutableListOf<TileId?>()
        result.addAll(
            getAllTileInMultiDirection(
                selectedTile,
                listOf(
                    TileDirections.TOP,
                    TileDirections.UPPER_RIGHT
                )
            )
        )
        result.addAll(
            getAllTileInMultiDirection(
                selectedTile,
                listOf(
                    TileDirections.TOP,
                    TileDirections.UPPER_LEFT
                )
            )
        )
        result.addAll(
            getAllTileInMultiDirection(
                selectedTile,
                listOf(
                    TileDirections.UPPER_RIGHT,
                    TileDirections.UNDER_RIGHT
                )
            )
        )

        result.addAll(
            getAllTileInMultiDirection(
                selectedTile,
                listOf(
                    TileDirections.BOTTOM,
                    TileDirections.UNDER_RIGHT
                )
            )
        )
        result.addAll(
            getAllTileInMultiDirection(
                selectedTile,
                listOf(
                    TileDirections.BOTTOM,
                    TileDirections.UNDER_LEFT
                )
            )
        )
        result.addAll(
            getAllTileInMultiDirection(
                selectedTile,
                listOf(
                    TileDirections.UNDER_LEFT,
                    TileDirections.UPPER_LEFT
                )
            )
        )
        return result
    }

    private fun queenMove(selectedTile: Tile):List<TileId?> {
        return  bishopMove(selectedTile) + rookMove(selectedTile)
    }

    private fun kingMove(selectedTile: Tile):List<TileId?> {
        val result = mutableListOf<TileId>()
        val move1 = findTile(selectedTile.id,TileDirections.TOP)
        val move2 = findTile(selectedTile.id,TileDirections.UPPER_RIGHT)
        val move3 = findTile(selectedTile.id,TileDirections.UNDER_RIGHT)
        val move4 = findTile(selectedTile.id,TileDirections.BOTTOM)
        val move5 = findTile(selectedTile.id,TileDirections.UNDER_LEFT)
        val move6 = findTile(selectedTile.id,TileDirections.UPPER_LEFT)

        move1?.let { result.add(it) }
        move2?.let { result.add(it) }
        move3?.let { result.add(it) }
        move4?.let { result.add(it) }
        move5?.let { result.add(it) }
        move6?.let { result.add(it) }
        val opposingColor = if (selectedTile.chessPiece!!.color == PieceColor.BLACK) { PieceColor.WHITE } else { PieceColor.BLACK }

        val moveLeadToCheck = checkKingUnavailableMove(opposingColor)
        val iterator = result.iterator()
        while (iterator.hasNext()) {
            val item = iterator.next()
            if (moveLeadToCheck.contains(item)) {
                iterator.remove()
            }
        }   
        return result
    }

    private fun resolveMoveResult(result:List<TileId?>) {
        for (tileId in result) {
            val index = tileId?.let { getTileIndex(it) }
            index?.let { currentIndex ->
                if (_chessBoard.value[currentIndex].chessPiece == null) {
                    _chessBoard.value[currentIndex].isAPossibleMove = true
                }
                if (_chessBoard.value[currentIndex].chessPiece != null && _chessBoard.value[currentIndex].chessPiece!!.color != selectedTile!!.chessPiece!!.color) {
                    _chessBoard.value[currentIndex].isAPossibleMove = true
                }
            }
        }
    }

    private fun getAllTileInDirection(selectedTile: Tile, direction: TileDirections):List<TileId?> {
        val result = mutableListOf<TileId?>()
        val firstTileId = findTile(selectedTile.id,direction)
        firstTileId?.let {
            var currentTile = _chessBoard.value[getTileIndex(it)]
            currentTile.chessPiece?.let { adjacentTileWithPiece ->
                if (adjacentTileWithPiece.color != selectedTile.chessPiece!!.color) {
                    result.add(currentTile.id)
                }
                return result
            }
            result.add(currentTile.id)
            for (i in 1 until 12) {
                val nextTile = findTile(currentTile.id,direction)
                nextTile?.let { nextTileId ->
                    currentTile = _chessBoard.value[getTileIndex(nextTileId)]
                    result.add(nextTile)
                }
                if (currentTile.chessPiece != null) {
                    break
                }
            }
        }
        return result
    }

    private fun getAllTileInMultiDirection(selectedTile: Tile, directions: List<TileDirections>):List<TileId?> {
        val result = mutableListOf<TileId?>()
        var firstTileId:TileId? = selectedTile.id
        for (direction in directions) {
            firstTileId = firstTileId?.let { findTile(it, direction) }
        }
        firstTileId?.let {
            var currentTile = _chessBoard.value[getTileIndex(it)]
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
                    nextTile = nextTile?.let { nextTileId-> findTile(nextTileId, direction) }
                }
                nextTile?.let { nextTileId ->
                    currentTile = _chessBoard.value[getTileIndex(nextTileId)]
                    result.add(nextTile)
                }
                if (currentTile.chessPiece != null) {
                    break
                }
            }
        }

        return result
    }

    private fun checkPawnAttack(tile: Tile):List<TileId?> {
        return if(tile.chessPiece!!.color == PieceColor.BLACK) {
            listOf(findTile(tile.id,TileDirections.UNDER_LEFT),findTile(tile.id,TileDirections.UNDER_RIGHT))
        } else {
            listOf(findTile(tile.id,TileDirections.UPPER_LEFT),findTile(tile.id,TileDirections.UPPER_RIGHT))
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

    private fun checkKingUnavailableMove(pieceColor: PieceColor):List<TileId?> {
        val result = mutableListOf<TileId?>()
        for (tile in _chessBoard.value) {
            tile.chessPiece?.let {
                if (it.color == pieceColor) {
                    result += when(it.type) {
                        PieceType.KNIGHT -> knightMove(tile)
                        PieceType.PAWN -> checkPawnAttack(tile)
                        PieceType.BISHOP -> bishopMove(tile)
                        PieceType.ROOK -> rookMove(tile)
                        PieceType.QUEEN -> queenMove(tile)
                        PieceType.KING ->
                            listOf(
                                findTile(tile.id,TileDirections.TOP),
                                findTile(tile.id,TileDirections.UPPER_RIGHT),
                                findTile(tile.id,TileDirections.UNDER_RIGHT),
                                findTile(tile.id,TileDirections.BOTTOM),
                                findTile(tile.id,TileDirections.UNDER_LEFT),
                                findTile(tile.id,TileDirections.UPPER_LEFT)
                            )
                    }
                }
            }
        }
        return result
    }

    private fun wasPinned(selectedTile: Tile):Boolean {
        val selectedPiece = selectedTile.chessPiece
        _chessBoard.value[getTileIndex(selectedTile.id)].chessPiece = null
        for (simulatedTile in _chessBoard.value) {
            simulatedTile.chessPiece?.let {
                if (it.color != selectedTile.chessPiece?.color) {
                    when(it.type) {
                        PieceType.BISHOP -> {
                            for (move in bishopMove(simulatedTile)) {
                                move?.let { moveId ->
                                    _chessBoard.value[getTileIndex(moveId)].chessPiece?.let { mockChessPiece ->
                                        if (mockChessPiece.type == PieceType.KING) {
                                            _chessBoard.value[getTileIndex(selectedTile.id)].chessPiece = selectedPiece
                                            return true
                                        }
                                    }
                                }
                            }
                        }

                        PieceType.ROOK -> {
                            for (move in rookMove(simulatedTile)) {
                                move?.let { moveId ->
                                    _chessBoard.value[getTileIndex(moveId)].chessPiece?.let { mockChessPiece ->
                                        if (mockChessPiece.type == PieceType.KING) {
                                            _chessBoard.value[getTileIndex(selectedTile.id)].chessPiece = selectedPiece
                                            return true
                                        }
                                    }
                                }
                            }
                        }

                        PieceType.QUEEN -> {
                            for (move in queenMove(simulatedTile)) {
                                move?.let { moveId ->
                                    _chessBoard.value[getTileIndex(moveId)].chessPiece?.let { mockChessPiece ->
                                        if (mockChessPiece.type == PieceType.KING) {
                                            _chessBoard.value[getTileIndex(selectedTile.id)].chessPiece = selectedPiece
                                            return true
                                        }
                                    }
                                }
                            }
                        }
                        else -> {  }
                    }
                }
            }
        }
        _chessBoard.value[getTileIndex(selectedTile.id)].chessPiece = selectedPiece
        return false
    }

    private fun checkForCheckMate(color: PieceColor) {
        for (tile in _chessBoard.value) {
        }
    }

    private fun enPassantEnable() {

    }

    private fun gameOver() {

    }
}


