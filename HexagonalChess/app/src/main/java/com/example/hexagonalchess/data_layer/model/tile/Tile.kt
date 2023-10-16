package com.example.hexagonalchess.data_layer.model.tile

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId

open class Tile(
    val id: TileId,
    val color: TileColor,
    val topTile: com.example.hexagonalchess.data_layer.model.tile.Tile?,
    val upperRightTile: com.example.hexagonalchess.data_layer.model.tile.Tile?,
    val underRightTile: com.example.hexagonalchess.data_layer.model.tile.Tile?,
    val bottomTile: com.example.hexagonalchess.data_layer.model.tile.Tile?,
    val underLeftTile: com.example.hexagonalchess.data_layer.model.tile.Tile?,
    val upperLeftTile: com.example.hexagonalchess.data_layer.model.tile.Tile?
)