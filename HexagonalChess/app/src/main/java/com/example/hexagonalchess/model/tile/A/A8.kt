package com.example.hexagonalchess.model.tile.A

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.model.tile.B.B8
import com.example.hexagonalchess.model.tile.B.B9
import com.example.hexagonalchess.model.tile.Tile

class A8: Tile(
    id = TileId.A8,
    color = TileColor.LIGHT,
    topTile = null,
    upperRightTile = B9(),
    underRightTile = B8(),
    bottomTile = A7(),
    underLeftTile = null,
    upperLeftTile = null
) {
}