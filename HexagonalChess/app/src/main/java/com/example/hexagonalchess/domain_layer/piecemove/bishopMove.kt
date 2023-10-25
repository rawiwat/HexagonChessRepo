package com.example.hexagonalchess.domain_layer.piecemove

import com.example.hexagonalchess.data_layer.model.tile.Tile
import com.example.hexagonalchess.domain_layer.TileDirections
import com.example.hexagonalchess.domain_layer.TileId
import com.example.hexagonalchess.domain_layer.filterSameColor
import com.example.hexagonalchess.domain_layer.getAllTileInMultiDirection


fun bishopMove(selectedTile: Tile, board: List<Tile>):List<TileId?> {
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

    filterSameColor(selectedTile, result, board)

    return result
}