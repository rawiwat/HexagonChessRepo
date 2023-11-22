package com.hexchess.hexagonalchess.domain_layer.piecemove

import com.hexchess.hexagonalchess.data_layer.model.tile.Tile
import com.hexchess.hexagonalchess.domain_layer.BoardType
import com.hexchess.hexagonalchess.domain_layer.TileId
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

suspend fun queenMove(selectedTile: Tile, board: List<Tile>, boardType: BoardType):List<TileId?> = coroutineScope{
    val deferredResult = mutableListOf<Deferred<List<TileId?>>>()
    deferredResult.add(async { bishopMove(selectedTile, board, boardType) })
    deferredResult.add(async { rookMove(selectedTile, board, boardType) })
    return@coroutineScope deferredResult.awaitAll().flatten().toMutableList()
}