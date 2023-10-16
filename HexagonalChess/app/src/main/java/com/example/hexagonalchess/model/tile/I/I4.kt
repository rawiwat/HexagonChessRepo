package com.example.hexagonalchess.model.tile.I

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.model.tile.H.H4
import com.example.hexagonalchess.model.tile.H.H5
import com.example.hexagonalchess.model.tile.Tile

class I4: Tile(
    id = TileId.I4,
    color = TileColor.DARK,
    topTile = I5(),
    upperRightTile = null,
    underRightTile = null,
    bottomTile = I3(),
    underLeftTile = H4(),
    upperLeftTile = H5()
) {
}