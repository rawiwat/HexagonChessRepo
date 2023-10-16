package com.example.hexagonalchess.data_layer.model.tile.I

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.data_layer.model.tile.Tile

class I7: com.example.hexagonalchess.data_layer.model.tile.Tile(
    id = TileId.I7,
    color = TileColor.DARK,
    topTile = null,
    upperRightTile = null,
    underRightTile = null,
    bottomTile = null,
    underLeftTile = null,
    upperLeftTile = null
) {
}