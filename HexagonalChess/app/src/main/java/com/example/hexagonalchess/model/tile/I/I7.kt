package com.example.hexagonalchess.model.tile.I

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.model.tile.Tile

class I7: Tile(
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