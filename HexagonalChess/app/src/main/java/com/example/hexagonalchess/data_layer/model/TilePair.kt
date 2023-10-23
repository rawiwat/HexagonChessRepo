package com.example.hexagonalchess.data_layer.model

import com.example.hexagonalchess.domain_layer.TileId
import com.google.errorprone.annotations.Immutable

@Immutable
data class TilePair(
    val startingPoint: TileId,
    val endPoint: TileId
)

val blackPawnForwardTwo = listOf(
    TilePair(
        startingPoint = TileId.A8,
        endPoint = TileId.A6
    ),
    TilePair(
        startingPoint = TileId.B8,
        endPoint = TileId.B6
    ),
    TilePair(
        startingPoint = TileId.C8,
        endPoint = TileId.C6
    ),
    TilePair(
        startingPoint = TileId.D8,
        endPoint = TileId.D6
    ),
    TilePair(
        startingPoint = TileId.E8,
        endPoint = TileId.E6
    ),
    TilePair(
        startingPoint = TileId.F8,
        endPoint = TileId.F6
    ),
    TilePair(
        startingPoint = TileId.G8,
        endPoint = TileId.G6
    ),
    TilePair(
        startingPoint = TileId.H8,
        endPoint = TileId.H6
    ),
    TilePair(
        startingPoint = TileId.I8,
        endPoint = TileId.I6
    )
)


val whitePawnForwardTwo = listOf(
    TilePair(
        startingPoint = TileId.A1,
        endPoint = TileId.A3
    ),
    TilePair(
        startingPoint = TileId.B2,
        endPoint = TileId.B4
    ),
    TilePair(
        startingPoint = TileId.C3,
        endPoint = TileId.C5
    ),
    TilePair(
        startingPoint = TileId.D4,
        endPoint = TileId.D6
    ),
    TilePair(
        startingPoint = TileId.E5,
        endPoint = TileId.E7
    ),
    TilePair(
        startingPoint = TileId.F4,
        endPoint = TileId.F6
    ),
    TilePair(
        startingPoint = TileId.G3,
        endPoint = TileId.G5
    ),
    TilePair(
        startingPoint = TileId.H2,
        endPoint = TileId.H4
    ),
    TilePair(
        startingPoint = TileId.I1,
        endPoint = TileId.I3
    )
)

