package com.example.hexagonalchess.data_layer.model

import com.example.hexagonalchess.domain_layer.TileId

val whiteEnPassantAttack = listOf(
    TilePair(
        startingPoint = TileId.A6,
        endPoint = TileId.B7
    ),
    TilePair(
        startingPoint = TileId.B7,
        endPoint = TileId.A7
    ),
    TilePair(
        startingPoint = TileId.B7,
        endPoint = TileId.A7
    )
)