package com.example.hexagonalchess.domain_layer.piecemove

import com.example.hexagonalchess.data_layer.model.tile.Tile
import com.example.hexagonalchess.domain_layer.TileDirections
import com.example.hexagonalchess.domain_layer.TileId
import com.example.hexagonalchess.domain_layer.filterSameColor
import com.example.hexagonalchess.domain_layer.findTile
import com.example.hexagonalchess.domain_layer.getTileIndex

fun kingMove(selectedTile: Tile, board: List<Tile>):List<TileId?> {
    val result = mutableListOf<TileId?>()
    val move1 = findTile(selectedTile.id, TileDirections.TOP, board)
    val move2 = findTile(selectedTile.id, TileDirections.UPPER_RIGHT, board)
    val move3 = findTile(selectedTile.id, TileDirections.UNDER_RIGHT, board)
    val move4 = findTile(selectedTile.id, TileDirections.BOTTOM, board)
    val move5 = findTile(selectedTile.id, TileDirections.UNDER_LEFT, board)
    val move6 = findTile(selectedTile.id, TileDirections.UPPER_LEFT, board)

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

    filterSameColor(selectedTile, result, board)

    return result
}