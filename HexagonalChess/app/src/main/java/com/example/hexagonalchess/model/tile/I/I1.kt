package com.example.hexagonalchess.model.tile.I

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.model.tile.H.H1
import com.example.hexagonalchess.model.tile.H.H2
import com.example.hexagonalchess.model.tile.Tile

class I1: Tile(
    id = TileId.I1,
    color = TileColor.DARK,
    topTile = I2(),
    upperRightTile = null,
    underRightTile = null,
    bottomTile = null,
    underLeftTile = H1(),
    upperLeftTile = H2()
) {
}