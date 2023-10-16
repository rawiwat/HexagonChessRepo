package com.example.hexagonalchess.data_layer.model.tile.A

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.data_layer.model.tile.B.B4
import com.example.hexagonalchess.data_layer.model.tile.B.B5
import com.example.hexagonalchess.data_layer.model.tile.Tile

class A4: com.example.hexagonalchess.data_layer.model.tile.Tile(
    id = TileId.A4,
    color = TileColor.DARK,
    topTile = com.example.hexagonalchess.data_layer.model.tile.A.A5(),
    upperRightTile = com.example.hexagonalchess.data_layer.model.tile.B.B5(),
    underRightTile = com.example.hexagonalchess.data_layer.model.tile.B.B4(),
    bottomTile = com.example.hexagonalchess.data_layer.model.tile.A.A6(),
    underLeftTile = null,
    upperLeftTile = null
)