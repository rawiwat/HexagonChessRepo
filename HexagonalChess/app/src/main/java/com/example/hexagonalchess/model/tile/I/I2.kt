package com.example.hexagonalchess.model.tile.I

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.model.tile.H.H2
import com.example.hexagonalchess.model.tile.H.H3
import com.example.hexagonalchess.model.tile.Tile

class I2: Tile(
    id = TileId.I2,
    color = TileColor.LIGHT,
    topTile = I3(),
    upperRightTile = null,
    underRightTile = null,
    bottomTile = I1(),
    underLeftTile = H2(),
    upperLeftTile = H3()
) {
}