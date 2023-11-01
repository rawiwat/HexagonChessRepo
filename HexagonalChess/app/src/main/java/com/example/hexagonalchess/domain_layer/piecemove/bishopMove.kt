package com.example.hexagonalchess.domain_layer.piecemove

import com.example.hexagonalchess.data_layer.model.tile.Tile
import com.example.hexagonalchess.domain_layer.BoardType
import com.example.hexagonalchess.domain_layer.TileDirections
import com.example.hexagonalchess.domain_layer.TileId
import com.example.hexagonalchess.domain_layer.filterSameColor
import com.example.hexagonalchess.domain_layer.getAllTileInMultiDirection
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope


suspend fun bishopMove(selectedTile: Tile, board: List<Tile>, boardType: BoardType):List<TileId?> = coroutineScope {
    val deferredResults = mutableListOf<Deferred<List<TileId?>>>()

    deferredResults.add(async {
        getAllTileInMultiDirection(selectedTile, listOf(TileDirections.TOP, TileDirections.UPPER_RIGHT), board, boardType)
    })
    deferredResults.add(async {
        getAllTileInMultiDirection(selectedTile, listOf(TileDirections.TOP, TileDirections.UPPER_LEFT), board, boardType)
    })
    deferredResults.add(async {
        getAllTileInMultiDirection(selectedTile, listOf(TileDirections.UPPER_RIGHT, TileDirections.UNDER_RIGHT), board, boardType)
    })
    deferredResults.add(async {
        getAllTileInMultiDirection(selectedTile, listOf(TileDirections.BOTTOM, TileDirections.UNDER_RIGHT), board, boardType)
    })
    deferredResults.add(async {
        getAllTileInMultiDirection(selectedTile, listOf(TileDirections.BOTTOM, TileDirections.UNDER_LEFT), board, boardType)
    })
    deferredResults.add(async {
        getAllTileInMultiDirection(selectedTile, listOf(TileDirections.UNDER_LEFT, TileDirections.UPPER_LEFT), board, boardType)
    })

    val result = deferredResults.awaitAll().flatten().toMutableList()

    filterSameColor(selectedTile, result, board, boardType)

    return@coroutineScope result
}