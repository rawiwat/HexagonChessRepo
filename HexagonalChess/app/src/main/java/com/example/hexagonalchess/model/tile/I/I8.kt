package com.example.hexagonalchess.model.tile.I

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.model.tile.H.H8
import com.example.hexagonalchess.model.tile.H.H9
import com.example.hexagonalchess.model.tile.Tile

class I8: Tile(
    id = TileId.I8,
    color = TileColor.LIGHT,
    topTile = null,
    upperRightTile = null,
    underRightTile = null,
    bottomTile = I7(),
    underLeftTile = H8(),
    upperLeftTile = H9()
) {
}