package com.example.hexagonalchess.data_layer.model.tile

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId

open class Tile(
    val id: TileId,
    val color: TileColor,
    var topTile: Tile? = null,
    var upperRightTile: Tile? = null,
    var underRightTile: Tile? = null,
    var bottomTile: Tile? = null,
    var underLeftTile: Tile? = null,
    var upperLeftTile: Tile? = null,
    var isAPossibleMove: Boolean = false
)