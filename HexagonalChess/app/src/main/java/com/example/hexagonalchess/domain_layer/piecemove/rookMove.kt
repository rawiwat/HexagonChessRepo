package com.example.hexagonalchess.domain_layer.piecemove

import com.example.hexagonalchess.data_layer.model.tile.Tile
import com.example.hexagonalchess.domain_layer.BoardType
import com.example.hexagonalchess.domain_layer.TileDirections
import com.example.hexagonalchess.domain_layer.TileId
import com.example.hexagonalchess.domain_layer.filterSameColor
import com.example.hexagonalchess.domain_layer.getAllTileInDirection

fun rookMove(selectedTile: Tile, board: List<Tile>, boardType: BoardType):List<TileId?> {
    val result = mutableListOf<TileId?>()
    result.addAll(getAllTileInDirection(selectedTile, TileDirections.TOP, board, boardType))
    result.addAll(getAllTileInDirection(selectedTile, TileDirections.UPPER_RIGHT, board, boardType))
    result.addAll(getAllTileInDirection(selectedTile, TileDirections.UNDER_RIGHT, board, boardType))
    result.addAll(getAllTileInDirection(selectedTile, TileDirections.BOTTOM, board, boardType))
    result.addAll(getAllTileInDirection(selectedTile, TileDirections.UNDER_LEFT, board, boardType))
    result.addAll(getAllTileInDirection(selectedTile, TileDirections.UPPER_LEFT, board, boardType))

    filterSameColor(selectedTile, result, board, boardType)

    return result
}