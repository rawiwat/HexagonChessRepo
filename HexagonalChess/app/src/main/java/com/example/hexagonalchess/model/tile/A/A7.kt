package com.example.hexagonalchess.model.tile.A

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.model.tile.B.B7
import com.example.hexagonalchess.model.tile.B.B8
import com.example.hexagonalchess.model.tile.Tile

class A7: Tile(
    id = TileId.A7,
    color = TileColor.DARK,
    topTile = A8(),
    upperRightTile = B8(),
    underRightTile = B7(),
    bottomTile = A6(),
    underLeftTile = null,
    upperLeftTile = null
) {
}