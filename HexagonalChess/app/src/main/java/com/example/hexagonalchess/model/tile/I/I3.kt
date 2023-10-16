package com.example.hexagonalchess.model.tile.I

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.model.tile.H.H3
import com.example.hexagonalchess.model.tile.H.H4
import com.example.hexagonalchess.model.tile.Tile

class I3: Tile(
    id = TileId.I3,
    color = TileColor.MID,
    topTile = I4(),
    upperRightTile = null,
    underRightTile = null,
    bottomTile = I2(),
    underLeftTile = H3(),
    upperLeftTile = H4()
) {
}