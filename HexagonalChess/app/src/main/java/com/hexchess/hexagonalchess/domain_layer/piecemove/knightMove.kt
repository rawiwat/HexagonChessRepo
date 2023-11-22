package com.hexchess.hexagonalchess.domain_layer.piecemove

import com.hexchess.hexagonalchess.data_layer.model.tile.Tile
import com.hexchess.hexagonalchess.domain_layer.BoardType
import com.hexchess.hexagonalchess.domain_layer.TileDirections
import com.hexchess.hexagonalchess.domain_layer.TileId
import com.hexchess.hexagonalchess.domain_layer.filterSameColor
import com.hexchess.hexagonalchess.domain_layer.findTile
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope


suspend fun knightMove(selectedTile: Tile, board:List<Tile>, boardType: BoardType): List<TileId?> = coroutineScope {
    val deferredResult = mutableListOf<Deferred<TileId?>>()

    deferredResult.add(async {
        getAKnightMove(
            TileDirections.TOP,
            TileDirections.TOP,
            TileDirections.UPPER_LEFT,
            board,boardType,selectedTile.id
        )
    })

    deferredResult.add(async {
        getAKnightMove(
            TileDirections.UPPER_LEFT,
            TileDirections.TOP,
            TileDirections.TOP,
            board,boardType,selectedTile.id
        )
    })

    deferredResult.add(async {
        getAKnightMove(
            TileDirections.TOP,
            TileDirections.TOP,
            TileDirections.UPPER_RIGHT,
            board,boardType,selectedTile.id
        )
    })

    deferredResult.add(async {
        getAKnightMove(
            TileDirections.UPPER_RIGHT,
            TileDirections.TOP,
            TileDirections.TOP,
            board,boardType,selectedTile.id
        )
    })

    deferredResult.add(async {
        getAKnightMove(
            TileDirections.BOTTOM,
            TileDirections.BOTTOM,
            TileDirections.UNDER_LEFT,
            board,boardType,selectedTile.id
        )
    })

    deferredResult.add(async {
        getAKnightMove(
            TileDirections.UNDER_LEFT,
            TileDirections.BOTTOM,
            TileDirections.BOTTOM,
            board,boardType,selectedTile.id
        )
    })

    deferredResult.add(async {
        getAKnightMove(
            TileDirections.BOTTOM,
            TileDirections.BOTTOM,
            TileDirections.UNDER_RIGHT,
            board,boardType,selectedTile.id
        )
    })

    deferredResult.add(async {
        getAKnightMove(
            TileDirections.UNDER_RIGHT,
            TileDirections.BOTTOM,
            TileDirections.BOTTOM,
            board,boardType,selectedTile.id
        )
    })

    deferredResult.add(async {
        getAKnightMove(
            TileDirections.UNDER_RIGHT,
            TileDirections.BOTTOM,
            TileDirections.BOTTOM,
            board,boardType,selectedTile.id
        )
    })

    deferredResult.add(async {
        getAKnightMove(
            TileDirections.TOP,
            TileDirections.UPPER_LEFT,
            TileDirections.UPPER_LEFT,
            board,boardType,selectedTile.id
        )
    })

    deferredResult.add(async {
        getAKnightMove(
            TileDirections.UPPER_LEFT,
            TileDirections.UPPER_LEFT,
            TileDirections.TOP,
            board,boardType,selectedTile.id
        )
    })

    deferredResult.add(async {
        getAKnightMove(
            TileDirections.TOP,
            TileDirections.UPPER_RIGHT,
            TileDirections.UPPER_RIGHT,
            board,boardType,selectedTile.id
        )
    })

    deferredResult.add(async {
        getAKnightMove(
            TileDirections.UPPER_RIGHT,
            TileDirections.UPPER_RIGHT,
            TileDirections.TOP,
            board,boardType,selectedTile.id
        )
    })

    deferredResult.add(async {
        getAKnightMove(
            TileDirections.BOTTOM,
            TileDirections.UNDER_LEFT,
            TileDirections.UNDER_LEFT,
            board,boardType,selectedTile.id
        )
    })

    deferredResult.add(async {
        getAKnightMove(
            TileDirections.UNDER_LEFT,
            TileDirections.UNDER_LEFT,
            TileDirections.BOTTOM,
            board,boardType,selectedTile.id
        )
    })

    deferredResult.add(async {
        getAKnightMove(
            TileDirections.UNDER_RIGHT,
            TileDirections.UNDER_RIGHT,
            TileDirections.BOTTOM,
            board,boardType,selectedTile.id
        )
    })

    deferredResult.add(async {
        getAKnightMove(
            TileDirections.BOTTOM,
            TileDirections.UNDER_RIGHT,
            TileDirections.UNDER_RIGHT,
            board,boardType,selectedTile.id
        )
    })

    deferredResult.add(async {
        getAKnightMove(
            TileDirections.UPPER_LEFT,
            TileDirections.UPPER_LEFT,
            TileDirections.UNDER_LEFT,
            board,boardType,selectedTile.id
        )
    })

    deferredResult.add(async {
        getAKnightMove(
            TileDirections.UNDER_LEFT,
            TileDirections.UPPER_LEFT,
            TileDirections.UPPER_LEFT,
            board,boardType,selectedTile.id
        )
    })

    deferredResult.add(async {
        getAKnightMove(
            TileDirections.UNDER_LEFT,
            TileDirections.UNDER_LEFT,
            TileDirections.UPPER_LEFT,
            board,boardType,selectedTile.id
        )
    })

    deferredResult.add(async {
        getAKnightMove(
            TileDirections.UPPER_LEFT,
            TileDirections.UNDER_LEFT,
            TileDirections.UNDER_LEFT,
            board,boardType,selectedTile.id
        )
    })

    deferredResult.add(async {
        getAKnightMove(
            TileDirections.UPPER_RIGHT,
            TileDirections.UPPER_RIGHT,
            TileDirections.UNDER_RIGHT,
            board,boardType,selectedTile.id
        )
    })

    deferredResult.add(async {
        getAKnightMove(
            TileDirections.UNDER_RIGHT,
            TileDirections.UPPER_RIGHT,
            TileDirections.UPPER_RIGHT,
            board,boardType,selectedTile.id
        )
    })

    deferredResult.add(async {
        getAKnightMove(
            TileDirections.UNDER_RIGHT,
            TileDirections.UNDER_RIGHT,
            TileDirections.UPPER_RIGHT,
            board,boardType,selectedTile.id
        )
    })

    deferredResult.add(async {
        getAKnightMove(
            TileDirections.UPPER_RIGHT,
            TileDirections.UNDER_RIGHT,
            TileDirections.UNDER_RIGHT,
            board,boardType,selectedTile.id
        )
    })

    val result = deferredResult.awaitAll().toMutableList()

    filterSameColor(selectedTile, result, board, boardType)

    return@coroutineScope result
}

fun getAKnightMove(
    direction1:TileDirections,
    direction2:TileDirections,
    direction3:TileDirections,
    board: List<Tile>,
    boardType: BoardType,
    selectedTileId: TileId
): TileId? {
    var move = findTile(selectedTileId, direction1, board, boardType)
    move?.let { move = findTile(it, direction2, board, boardType) }
    move?.let { move = findTile(it, direction3, board, boardType) }
    return move
}