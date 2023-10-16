package com.example.hexagonalchess.data_layer.model.tile.A

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.data_layer.model.tile.B.B3
import com.example.hexagonalchess.data_layer.model.tile.B.B4
import com.example.hexagonalchess.data_layer.model.tile.Tile

class A3: com.example.hexagonalchess.data_layer.model.tile.Tile(
    id = TileId.A3,
    color = TileColor.LIGHT,
    topTile = com.example.hexagonalchess.data_layer.model.tile.A.A4(),
    upperRightTile = com.example.hexagonalchess.data_layer.model.tile.B.B4(),
    underRightTile = com.example.hexagonalchess.data_layer.model.tile.B.B3(),
    bottomTile = com.example.hexagonalchess.data_layer.model.tile.A.A2(),
    underLeftTile = null,
    upperLeftTile = null
)