package com.example.hexagonalchess.presentation_layer.viewmodel

import androidx.lifecycle.ViewModel
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

    //private val _whiteCaptured = MutableStateFlow(mutableListOf<ChessPiece>())
    //val whiteCaptured:StateFlow<List<ChessPiece>> = _whiteCaptured

    //private val _blackCaptured = MutableStateFlow(mutableListOf<ChessPiece>())
    //val blackCaptured:StateFlow<List<ChessPiece>> = _blackCaptured

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
        if (tile.chessPiece != null) {
            for (tiles in _chessBoard.value) {
                tiles.isAPossibleMove = false
            }
            selectedTile = tile
            when (tile.chessPiece!!.type) {
                PieceType.PAWN -> pawnMove(tile)
                PieceType.KNIGHT -> knightMove(tile)
                PieceType.ROOK -> rookMove(tile)
                PieceType.KING -> kingMove(tile)
                else -> {  }
            }
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

    private fun pawnMove(selectedTile: Tile) {
        if (selectedTile.chessPiece!!.color == PieceColor.WHITE) {
            val result = mutableListOf<TileId?>()
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

            /*for (tiles in _chessBoard.value) {
                if (tiles.id == attack2 && tiles.chessPiece != null && tiles.chessPiece!!.color == PieceColor.BLACK) {
                    result.add(attack2)
                }
            }*/
            for (tileId in result) {
                val index = tileId?.let { getTileIndex(it) }
                index?.let {
                    _chessBoard.value[it].isAPossibleMove = true
                }
            }
            /*for (tiles in _chessBoard.value) {
                if (result.contains(tiles.id)) {
                    tiles.isAPossibleMove = true
                }
            }*/
        } else {
            val result = mutableListOf<TileId?>()
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
            }*/
            for (tileId in result) {
                val index = tileId?.let { getTileIndex(it) }
                index?.let {
                    _chessBoard.value[it].isAPossibleMove = true
                }
            }
        }
    }

    private fun knightMove(selectedTile: Tile) {
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

        for (tileId in result) {
            val index = tileId?.let { getTileIndex(it) }
            index?.let { currentIndex ->
                if (_chessBoard.value[currentIndex].chessPiece == null) {
                    _chessBoard.value[currentIndex].isAPossibleMove = true
                }
                if (_chessBoard.value[currentIndex].chessPiece != null && _chessBoard.value[currentIndex].chessPiece!!.color != selectedTile.chessPiece!!.color) {
                    _chessBoard.value[currentIndex].isAPossibleMove = true
                }
            }
        }
    }

    private fun rookMove(selectedTile: Tile) {
        val result = mutableListOf<TileId?>()
        result.addAll(getAllTileInDirection(selectedTile, TileDirections.TOP))
        result.addAll(getAllTileInDirection(selectedTile, TileDirections.UPPER_RIGHT))
        result.addAll(getAllTileInDirection(selectedTile, TileDirections.UNDER_RIGHT))
        result.addAll(getAllTileInDirection(selectedTile, TileDirections.BOTTOM))
        result.addAll(getAllTileInDirection(selectedTile, TileDirections.UNDER_LEFT))
        result.addAll(getAllTileInDirection(selectedTile, TileDirections.UPPER_LEFT))
        resolveMoveResult(result)
    }

    private fun kingMove(selectedTile: Tile) {
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

        resolveMoveResult(result)
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
            result.add(currentTile.id)
            while (currentTile.chessPiece == null) {
                val nextTile = findTile(currentTile.id,direction)
                nextTile?.let { nextTileId ->
                    currentTile = _chessBoard.value[getTileIndex(nextTileId)]
                    result.add(nextTile)
                }
            }
        }
        return result
    }

    private fun updateBoard() {
        val updatedChessBoard = _chessBoard.value.map { tile ->
            tile.copy()
        }
        _chessBoard.value = updatedChessBoard
    }

    fun recomposeTest() {
        for (tile in _chessBoard.value) {
            tile.isAPossibleMove = !tile.isAPossibleMove
        }
        updateBoard()
    }
}


