package com.example.hexagonalchess.model.tile.I

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.model.tile.H.H6
import com.example.hexagonalchess.model.tile.H.H7
import com.example.hexagonalchess.model.tile.Tile

class I6: Tile(
    id = TileId.I6,
    color = TileColor.MID,
    topTile = I7(),
    upperRightTile = null,
    underRightTile = null,
    bottomTile = I6(),
    underLeftTile = H6(),
    upperLeftTile = H7()
) {
}