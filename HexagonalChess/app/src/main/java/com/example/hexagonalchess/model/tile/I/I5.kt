package com.example.hexagonalchess.model.tile.I

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.model.tile.H.H5
import com.example.hexagonalchess.model.tile.H.H6
import com.example.hexagonalchess.model.tile.Tile

class I5: Tile(
    id = TileId.I5,
    color = TileColor.LIGHT,
    topTile = I6(),
    upperRightTile = null,
    underRightTile = null,
    bottomTile = I4(),
    underLeftTile = H5(),
    upperLeftTile = H6()
) {
}