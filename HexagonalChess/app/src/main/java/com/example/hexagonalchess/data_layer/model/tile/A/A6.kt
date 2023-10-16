package com.example.hexagonalchess.data_layer.model.tile.A

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.data_layer.model.tile.B.B6
import com.example.hexagonalchess.data_layer.model.tile.B.B7
import com.example.hexagonalchess.data_layer.model.tile.Tile

class A6: com.example.hexagonalchess.data_layer.model.tile.Tile(
    id = TileId.A6,
    color = TileColor.MID,
    topTile = com.example.hexagonalchess.data_layer.model.tile.A.A7(),
    upperRightTile = com.example.hexagonalchess.data_layer.model.tile.B.B7(),
    underRightTile = com.example.hexagonalchess.data_layer.model.tile.B.B6(),
    bottomTile = com.example.hexagonalchess.data_layer.model.tile.A.A5(),
    underLeftTile = null,
    upperLeftTile = null
)