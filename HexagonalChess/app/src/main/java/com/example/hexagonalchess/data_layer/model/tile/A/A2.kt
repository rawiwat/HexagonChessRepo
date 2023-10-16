package com.example.hexagonalchess.data_layer.model.tile.A

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.data_layer.model.tile.B.B2
import com.example.hexagonalchess.data_layer.model.tile.B.B3
import com.example.hexagonalchess.data_layer.model.tile.Tile

class A2: com.example.hexagonalchess.data_layer.model.tile.Tile(
    id = TileId.A2,
    color = TileColor.MID,
    topTile = com.example.hexagonalchess.data_layer.model.tile.A.A3(),
    upperRightTile = com.example.hexagonalchess.data_layer.model.tile.B.B3(),
    underRightTile = com.example.hexagonalchess.data_layer.model.tile.B.B2(),
    bottomTile = com.example.hexagonalchess.data_layer.model.tile.A.A1(),
    underLeftTile = null,
    upperLeftTile = null
)