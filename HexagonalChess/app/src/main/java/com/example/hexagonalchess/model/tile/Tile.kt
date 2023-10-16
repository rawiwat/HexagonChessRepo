package com.example.hexagonalchess.model.tile

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId

open class Tile(
    val id: TileId,
    val color: TileColor,
    val topTile: Tile?,
    val upperRightTile: Tile?,
    val underRightTile: Tile?,
    val bottomTile: Tile?,
    val underLeftTile: Tile?,
    val upperLeftTile: Tile?
)