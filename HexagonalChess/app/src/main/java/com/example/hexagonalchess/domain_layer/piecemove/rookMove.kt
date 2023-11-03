package com.example.hexagonalchess.domain_layer.piecemove

import com.example.hexagonalchess.data_layer.model.tile.Tile
import com.example.hexagonalchess.domain_layer.BoardType
import com.example.hexagonalchess.domain_layer.TileDirections
import com.example.hexagonalchess.domain_layer.TileId
import com.example.hexagonalchess.domain_layer.filterSameColor
import com.example.hexagonalchess.domain_layer.getAllTileInDirection
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

suspend fun rookMove(selectedTile: Tile, board: List<Tile>, boardType: BoardType):List<TileId?> = coroutineScope {
    val deferredResult = mutableListOf<Deferred<List<TileId?>>>()
    deferredResult.add(async { getAllTileInDirection(selectedTile, TileDirections.TOP, board, boardType) })
    deferredResult.add(async { getAllTileInDirection(selectedTile, TileDirections.UPPER_RIGHT, board, boardType) })
    deferredResult.add(async { getAllTileInDirection(selectedTile, TileDirections.UNDER_RIGHT, board, boardType) })
    deferredResult.add(async { getAllTileInDirection(selectedTile, TileDirections.BOTTOM, board, boardType) })
    deferredResult.add(async { getAllTileInDirection(selectedTile, TileDirections.UNDER_LEFT, board, boardType) })
    deferredResult.add(async { getAllTileInDirection(selectedTile, TileDirections.UPPER_LEFT, board, boardType) })

    val result = deferredResult.awaitAll().flatten().toMutableList()
    filterSameColor(selectedTile, result, board, boardType)

    return@coroutineScope result
}