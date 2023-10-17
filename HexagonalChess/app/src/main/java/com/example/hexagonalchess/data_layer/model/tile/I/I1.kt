package com.example.hexagonalchess.data_layer.model.tile.I

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId

class I1: com.example.hexagonalchess.data_layer.model.tile.Tile(
    id = TileId.I1,
    color = TileColor.DARK,
    topTile = com.example.hexagonalchess.data_layer.model.tile.I.I2(),
    upperRightTile = null,
    underRightTile = null,
    bottomTile = null,
    underLeftTile = com.example.hexagonalchess.data_layer.model.tile.H.H1(),
    upperLeftTile = com.example.hexagonalchess.data_layer.model.tile.H.H2()
) {
}