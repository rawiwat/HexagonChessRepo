package com.example.hexagonalchess.data_layer.model.tile.I

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId

class I3: com.example.hexagonalchess.data_layer.model.tile.Tile(
    id = TileId.I3,
    color = TileColor.MID,
    topTile = com.example.hexagonalchess.data_layer.model.tile.I.I4(),
    upperRightTile = null,
    underRightTile = null,
    bottomTile = com.example.hexagonalchess.data_layer.model.tile.I.I2(),
    underLeftTile = com.example.hexagonalchess.data_layer.model.tile.H.H3(),
    upperLeftTile = com.example.hexagonalchess.data_layer.model.tile.H.H4()
) {
}