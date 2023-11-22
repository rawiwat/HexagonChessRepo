package com.hexchess.hexagonalchess.data_layer.model

import com.hexchess.hexagonalchess.domain_layer.TileId
import com.google.errorprone.annotations.Immutable

@Immutable
data class TilePair(
    val startingPoint: TileId,
    val endPoint: TileId
)
