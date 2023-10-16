package com.example.hexagonalchess.data_layer.model.tile.A

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.data_layer.model.tile.B.B5
import com.example.hexagonalchess.data_layer.model.tile.B.B6
import com.example.hexagonalchess.data_layer.model.tile.Tile

class A5: com.example.hexagonalchess.data_layer.model.tile.Tile(
    id = TileId.A5,
    color = TileColor.LIGHT,
    topTile = com.example.hexagonalchess.data_layer.model.tile.A.A6(),
    upperRightTile = com.example.hexagonalchess.data_layer.model.tile.B.B6(),
    underRightTile = com.example.hexagonalchess.data_layer.model.tile.B.B5(),
    bottomTile = com.example.hexagonalchess.data_layer.model.tile.A.A4(),
    underLeftTile = null,
    upperLeftTile = null
)