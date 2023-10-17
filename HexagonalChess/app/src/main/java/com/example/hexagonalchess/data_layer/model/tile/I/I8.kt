package com.example.hexagonalchess.data_layer.model.tile.I

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId

class I8: com.example.hexagonalchess.data_layer.model.tile.Tile(
    id = TileId.I8,
    color = TileColor.LIGHT,
    topTile = null,
    upperRightTile = null,
    underRightTile = null,
    bottomTile = com.example.hexagonalchess.data_layer.model.tile.I.I7(),
    underLeftTile = com.example.hexagonalchess.data_layer.model.tile.H.H8(),
    upperLeftTile = com.example.hexagonalchess.data_layer.model.tile.H.H9()
) {
}