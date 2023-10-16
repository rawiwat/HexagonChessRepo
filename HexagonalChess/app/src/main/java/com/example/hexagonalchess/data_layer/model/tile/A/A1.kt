package com.example.hexagonalchess.data_layer.model.tile.A

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.data_layer.model.tile.B.B1
import com.example.hexagonalchess.data_layer.model.tile.B.B2
import com.example.hexagonalchess.data_layer.model.tile.Tile

class A1: com.example.hexagonalchess.data_layer.model.tile.Tile(
    id = TileId.A1,
    color = TileColor.DARK,
    topTile = com.example.hexagonalchess.data_layer.model.tile.A.A2(),
    upperRightTile = com.example.hexagonalchess.data_layer.model.tile.B.B2(),
    underRightTile = com.example.hexagonalchess.data_layer.model.tile.B.B1(),
    bottomTile = null,
    underLeftTile = null,
    upperLeftTile = null
)