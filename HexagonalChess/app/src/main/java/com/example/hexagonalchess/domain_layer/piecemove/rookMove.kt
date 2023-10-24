package com.example.hexagonalchess.domain_layer.piecemove

import com.example.hexagonalchess.data_layer.model.tile.Tile
import com.example.hexagonalchess.domain_layer.TileDirections
import com.example.hexagonalchess.domain_layer.TileId
import com.example.hexagonalchess.domain_layer.getAllTileInDirection

fun rookMove(selectedTile: Tile, board: List<Tile>):List<TileId?> {
    val result = mutableListOf<TileId?>()
    result.addAll(getAllTileInDirection(selectedTile, TileDirections.TOP, board))
    result.addAll(getAllTileInDirection(selectedTile, TileDirections.UPPER_RIGHT, board))
    result.addAll(getAllTileInDirection(selectedTile, TileDirections.UNDER_RIGHT, board))
    result.addAll(getAllTileInDirection(selectedTile, TileDirections.BOTTOM, board))
    result.addAll(getAllTileInDirection(selectedTile, TileDirections.UNDER_LEFT, board))
    result.addAll(getAllTileInDirection(selectedTile, TileDirections.UPPER_LEFT, board))
    return result
}