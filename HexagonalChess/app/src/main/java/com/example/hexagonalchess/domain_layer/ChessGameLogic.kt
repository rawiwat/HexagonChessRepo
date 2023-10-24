package com.example.hexagonalchess.domain_layer

import com.example.hexagonalchess.data_layer.model.tile.Tile

fun findTile(id: TileId, direction: TileDirections, board: List<Tile>): TileId? {
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
fun containPiece(tileId: TileId?, board: List<Tile>): Boolean {
    val targetIndex = tileId?.let { getTileIndex(it) }
    targetIndex?.let {
        if (board[targetIndex].chessPiece != null ) {
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
fun pawnMove(selectedTile: Tile, board:List<Tile>): List<TileId?> {
    val result = mutableListOf<TileId?>()
    if (selectedTile.chessPiece!!.color == PieceColor.WHITE) {
        val forward1 = findTile(selectedTile.id, TileDirections.TOP, board)
        var forward2 = forward1
        forward1?.let {
            forward2 = findTile(forward1, TileDirections.TOP, board)
        }
        if (!containPiece(forward1, board)) {
            result.add(forward1)
        }

        if(!containPiece(forward1, board) && !containPiece(forward2, board)) {
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
            if (selectedTile.chessPiece!!.enPassantLeftEnable) {
                result.add(attack1)
            }
        }

        val attack2Index = attack2?.let { getTileIndex(it) }
        attack2Index?.let {
            if (board[it].chessPiece != null && board[it].chessPiece!!.color == PieceColor.BLACK) {
                result.add(attack2)
            }
            if (selectedTile.chessPiece!!.enPassantRightEnable) {
                result.add(attack2)
            }
        }
    } else {
        val forward1 = findTile(selectedTile.id, TileDirections.BOTTOM, board)
        var forward2 = forward1
        forward1?.let {
            forward2 = findTile(forward1, TileDirections.BOTTOM, board)
        }

        if (!containPiece(forward1, board)) {
            result.add(forward1)
        }

        if(!containPiece(forward1, board) && !containPiece(forward2, board)) {
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
            if (selectedTile.chessPiece!!.enPassantLeftEnable) {
                result.add(attack1)
            }
        }

        val attack2Index = attack2?.let { getTileIndex(it) }
        attack2Index?.let {
            if (board[it].chessPiece != null && board[it].chessPiece!!.color == PieceColor.WHITE) {
                result.add(attack2)
            }
            if (selectedTile.chessPiece!!.enPassantRightEnable) {
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

fun knightMove(selectedTile: Tile, board:List<Tile>):List<TileId?> {
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