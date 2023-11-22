package com.hexchess.hexagonalchess.domain_layer.piecemove

import com.hexchess.hexagonalchess.data_layer.model.tile.Tile
import com.hexchess.hexagonalchess.domain_layer.BoardType
import com.hexchess.hexagonalchess.domain_layer.TileDirections
import com.hexchess.hexagonalchess.domain_layer.TileId
import com.hexchess.hexagonalchess.domain_layer.filterSameColor
import com.hexchess.hexagonalchess.domain_layer.findTile

fun kingMove(selectedTile: Tile, board: List<Tile>, boardType: BoardType):List<TileId?> {
    val result = mutableListOf<TileId?>()
    val move1 = findTile(selectedTile.id, TileDirections.TOP, board, boardType)
    val move2 = findTile(selectedTile.id, TileDirections.UPPER_RIGHT, board, boardType)
    val move3 = findTile(selectedTile.id, TileDirections.UNDER_RIGHT, board, boardType)
    val move4 = findTile(selectedTile.id, TileDirections.BOTTOM, board, boardType)
    val move5 = findTile(selectedTile.id, TileDirections.UNDER_LEFT, board, boardType)
    val move6 = findTile(selectedTile.id, TileDirections.UPPER_LEFT, board, boardType)

    move1?.let { result.add(it) }
    move2?.let { result.add(it) }
    move3?.let { result.add(it) }
    move4?.let { result.add(it) }
    move5?.let { result.add(it) }
    move6?.let { result.add(it) }
    /*val opposingColor = if (selectedTile.chessPiece!!.color == PieceColor.BLACK) { PieceColor.WHITE } else { PieceColor.BLACK }

    val moveLeadToCheck = checkKingUnavailableMove(opposingColor, board)
    val iterator = result.iterator()
    while (iterator.hasNext()) {
        val item = iterator.next()
        if (moveLeadToCheck.contains(item)) {
            iterator.remove()
        }
    }*/

    filterSameColor(selectedTile, result, board, boardType)

    return result
}