package com.example.hexagonalchess.data_layer.model.tile.I

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId

class I6: com.example.hexagonalchess.data_layer.model.tile.Tile(
    id = TileId.I6,
    color = TileColor.MID,
    topTile = com.example.hexagonalchess.data_layer.model.tile.I.I7(),
    upperRightTile = null,
    underRightTile = null,
    bottomTile = com.example.hexagonalchess.data_layer.model.tile.I.I6(),
    underLeftTile = com.example.hexagonalchess.data_layer.model.tile.H.H6(),
    upperLeftTile = com.example.hexagonalchess.data_layer.model.tile.H.H7()
) {
}