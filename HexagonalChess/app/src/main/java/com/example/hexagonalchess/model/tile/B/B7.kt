package com.example.hexagonalchess.model.tile.B

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.model.tile.A.A6
import com.example.hexagonalchess.model.tile.Tile

class B7: Tile(
    id = TileId.B7,
    color = TileColor.MID,
    topTile = null,
    upperRightTile = null,
    underRightTile = null,
    bottomTile = B6(),
    underLeftTile = A6(),
    upperLeftTile = null
) {
}