package com.example.hexagonalchess.data_layer.model.tile.I

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId

class I5: com.example.hexagonalchess.data_layer.model.tile.Tile(
    id = TileId.I5,
    color = TileColor.LIGHT,
    topTile = com.example.hexagonalchess.data_layer.model.tile.I.I6(),
    upperRightTile = null,
    underRightTile = null,
    bottomTile = com.example.hexagonalchess.data_layer.model.tile.I.I4(),
    underLeftTile = com.example.hexagonalchess.data_layer.model.tile.H.H5(),
    upperLeftTile = com.example.hexagonalchess.data_layer.model.tile.H.H6()
) {
}