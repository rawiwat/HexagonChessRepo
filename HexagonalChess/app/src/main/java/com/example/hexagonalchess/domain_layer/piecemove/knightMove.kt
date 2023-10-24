package com.example.hexagonalchess.domain_layer.piecemove

import com.example.hexagonalchess.data_layer.model.tile.Tile
import com.example.hexagonalchess.domain_layer.TileDirections
import com.example.hexagonalchess.domain_layer.TileId
import com.example.hexagonalchess.domain_layer.findTile


fun knightMove(selectedTile: Tile, board:List<Tile>):List<TileId?> {
    val result = mutableListOf<TileId?>()

    var move1 = findTile(selectedTile.id, TileDirections.TOP, board)
    move1?.let { move1 = findTile(it, TileDirections.TOP, board) }
    move1?.let { move1 = findTile(it, TileDirections.UPPER_LEFT, board) }
    result.add(move1)

    var move2 = findTile(selectedTile.id, TileDirections.TOP, board)
    move2?.let { move2 = findTile(it, TileDirections.TOP, board) }
    move2?.let { move2 = findTile(it, TileDirections.UPPER_RIGHT, board) }
    result.add(move2)

    var move3 = findTile(selectedTile.id, TileDirections.BOTTOM, board)
    move3?.let { move3 = findTile(it, TileDirections.BOTTOM, board) }
    move3?.let { move3 = findTile(it, TileDirections.UNDER_LEFT, board) }
    result.add(move3)

    var move4 = findTile(selectedTile.id, TileDirections.BOTTOM, board)
    move4?.let { move4 = findTile(it, TileDirections.BOTTOM, board) }
    move4?.let { move4 = findTile(it, TileDirections.UNDER_RIGHT, board) }
    result.add(move4)

    var move5 = findTile(selectedTile.id, TileDirections.TOP, board)
    move5?.let { move5 = findTile(it, TileDirections.UPPER_LEFT, board) }
    move5?.let { move5 = findTile(it, TileDirections.UPPER_LEFT, board) }
    result.add(move5)

    var move6 = findTile(selectedTile.id, TileDirections.TOP, board)
    move6?.let { move6 = findTile(it, TileDirections.UPPER_RIGHT, board) }
    move6?.let { move6 = findTile(it, TileDirections.UPPER_RIGHT, board) }
    result.add(move6)

    var move7= findTile(selectedTile.id, TileDirections.BOTTOM, board)
    move7?.let { move7= findTile(it, TileDirections.UNDER_LEFT, board) }
    move7?.let { move7= findTile(it, TileDirections.UNDER_LEFT, board) }
    result.add(move7)

    var move8 = findTile(selectedTile.id, TileDirections.BOTTOM, board)
    move8?.let { move8 = findTile(it, TileDirections.UNDER_RIGHT, board) }
    move8?.let { move8 = findTile(it, TileDirections.UNDER_RIGHT, board) }
    result.add(move8)

    var move9 = findTile(selectedTile.id, TileDirections.UPPER_LEFT, board)
    move9?.let { move9 = findTile(it, TileDirections.UPPER_LEFT, board) }
    move9?.let { move9 = findTile(it, TileDirections.UNDER_LEFT, board) }
    result.add(move9)

    var move10 = findTile(selectedTile.id, TileDirections.UNDER_LEFT, board)
    move10?.let { move10 = findTile(it, TileDirections.UNDER_LEFT, board) }
    move10?.let { move10 = findTile(it, TileDirections.UPPER_LEFT, board) }
    result.add(move10)

    var move11 = findTile(selectedTile.id, TileDirections.UPPER_RIGHT, board)
    move11?.let { move11 = findTile(it, TileDirections.UPPER_RIGHT, board) }
    move11?.let { move11 = findTile(it, TileDirections.UNDER_RIGHT, board) }
    result.add(move11)

    var move12 = findTile(selectedTile.id, TileDirections.UNDER_RIGHT, board)
    move12?.let { move12 = findTile(it, TileDirections.UNDER_RIGHT, board) }
    move12?.let { move12 = findTile(it, TileDirections.UPPER_RIGHT, board) }
    result.add(move12)

    return result
}