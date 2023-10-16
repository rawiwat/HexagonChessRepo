package com.example.hexagonalchess.data_layer.model.tile.I

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.data_layer.model.tile.H.H2
import com.example.hexagonalchess.data_layer.model.tile.H.H3
import com.example.hexagonalchess.data_layer.model.tile.Tile

class I2: com.example.hexagonalchess.data_layer.model.tile.Tile(
    id = TileId.I2,
    color = TileColor.LIGHT,
    topTile = com.example.hexagonalchess.data_layer.model.tile.I.I3(),
    upperRightTile = null,
    underRightTile = null,
    bottomTile = com.example.hexagonalchess.data_layer.model.tile.I.I1(),
    underLeftTile = com.example.hexagonalchess.data_layer.model.tile.H.H2(),
    upperLeftTile = com.example.hexagonalchess.data_layer.model.tile.H.H3()
) {
}