package com.hexchess.hexagonalchess.domain_layer.piecemove

import com.hexchess.hexagonalchess.data_layer.model.tile.Tile
import com.hexchess.hexagonalchess.domain_layer.BoardType
import com.hexchess.hexagonalchess.domain_layer.PieceColor
import com.hexchess.hexagonalchess.domain_layer.TileDirections
import com.hexchess.hexagonalchess.domain_layer.TileId
import com.hexchess.hexagonalchess.domain_layer.containPiece
import com.hexchess.hexagonalchess.domain_layer.filterSameColor
import com.hexchess.hexagonalchess.domain_layer.findTile
import com.hexchess.hexagonalchess.domain_layer.getPawnStartingPoint
import com.hexchess.hexagonalchess.domain_layer.getTileIndex

suspend fun pawnMove(selectedTile: Tile, board:List<Tile>, boardType: BoardType): List<TileId?> {
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

        val startingTiles = getPawnStartingPoint(boardType, PieceColor.WHITE)
        val forward1NotContainPiece = !containPiece(forward1, board, boardType)
        val forward2NotContainPiece = !containPiece(forward2, board, boardType)
        val selectedTileIsInStartPosition = startingTiles.contains(selectedTile.id)
        if( forward1NotContainPiece && forward2NotContainPiece && selectedTileIsInStartPosition) {
            result.add(forward2)
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

        val startingTiles = getPawnStartingPoint(boardType, PieceColor.BLACK)
        val forward1NotContainPiece = !containPiece(forward1, board, boardType)
        val forward2NotContainPiece = !containPiece(forward2, board, boardType)
        val selectedTileIsInStartPosition = startingTiles.contains(selectedTile.id)
        if( forward1NotContainPiece && forward2NotContainPiece && selectedTileIsInStartPosition) {
            result.add(forward2)
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