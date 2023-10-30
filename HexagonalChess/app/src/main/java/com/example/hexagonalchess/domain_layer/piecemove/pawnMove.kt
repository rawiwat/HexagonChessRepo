package com.example.hexagonalchess.domain_layer.piecemove

import com.example.hexagonalchess.data_layer.model.tile.Tile
import com.example.hexagonalchess.domain_layer.BoardType
import com.example.hexagonalchess.domain_layer.PieceColor
import com.example.hexagonalchess.domain_layer.TileDirections
import com.example.hexagonalchess.domain_layer.TileId
import com.example.hexagonalchess.domain_layer.containPiece
import com.example.hexagonalchess.domain_layer.filterSameColor
import com.example.hexagonalchess.domain_layer.findTile
import com.example.hexagonalchess.domain_layer.getTileIndex

fun pawnMove(selectedTile: Tile, board:List<Tile>, boardType: BoardType): List<TileId?> {
    val result = mutableListOf<TileId?>()
    if (selectedTile.chessPiece!!.color == PieceColor.WHITE) {
        val forward1 = findTile(selectedTile.id, TileDirections.TOP, board, boardType)
        var forward2 = forward1
        forward1?.let {
            forward2 = findTile(forward1, TileDirections.TOP, board, boardType)
        }
        if (!containPiece(forward1, board, boardType)) {
            result.add(forward1)
        }

        if(!containPiece(forward1, board, boardType) && !containPiece(forward2, board, boardType) && boardType == BoardType.DEFAULT) {
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
                else -> {  }
            }
        }

        val attack1 = findTile(selectedTile.id, TileDirections.UPPER_LEFT, board, boardType)
        val attack2 = findTile(selectedTile.id, TileDirections.UPPER_RIGHT, board, boardType)
        val attack1Index = attack1?.let { getTileIndex(it, boardType) }
        attack1Index?.let {
            if (board[it].chessPiece != null && board[it].chessPiece!!.color == PieceColor.BLACK) {
                result.add(attack1)
            }
            if (selectedTile.chessPiece!!.enPassantLeftEnable) {
                result.add(attack1)
            }
        }

        val attack2Index = attack2?.let { getTileIndex(it, boardType) }
        attack2Index?.let {
            if (board[it].chessPiece != null && board[it].chessPiece!!.color == PieceColor.BLACK) {
                result.add(attack2)
            }
            if (selectedTile.chessPiece!!.enPassantRightEnable) {
                result.add(attack2)
            }
        }
    } else {
        val forward1 = findTile(selectedTile.id, TileDirections.BOTTOM, board, boardType)
        var forward2 = forward1
        forward1?.let {
            forward2 = findTile(forward1, TileDirections.BOTTOM, board, boardType)
        }

        if (!containPiece(forward1, board, boardType)) {
            result.add(forward1)
        }

        if(!containPiece(forward1, board, boardType) && !containPiece(forward2, board, boardType) && boardType == BoardType.DEFAULT) {
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

        val attack1 = findTile(selectedTile.id, TileDirections.UNDER_LEFT, board, boardType)
        val attack2 = findTile(selectedTile.id, TileDirections.UNDER_RIGHT, board, boardType)
        val attack1Index = attack1?.let { getTileIndex(it, boardType) }
        attack1Index?.let {
            if (board[it].chessPiece != null && board[it].chessPiece!!.color == PieceColor.WHITE) {
                result.add(attack1)
            }
            if (selectedTile.chessPiece!!.enPassantLeftEnable) {
                result.add(attack1)
            }
        }

        val attack2Index = attack2?.let { getTileIndex(it, boardType) }
        attack2Index?.let {
            if (board[it].chessPiece != null && board[it].chessPiece!!.color == PieceColor.WHITE) {
                result.add(attack2)
            }
            if (selectedTile.chessPiece!!.enPassantRightEnable) {
                result.add(attack2)
            }
        }
    }

    filterSameColor(selectedTile, result, board, boardType)

    return result
}