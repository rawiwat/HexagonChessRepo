package com.example.hexagonalchess.model.tile.B

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.model.tile.A.A8
import com.example.hexagonalchess.model.tile.C.C10
import com.example.hexagonalchess.model.tile.C.C9
import com.example.hexagonalchess.model.tile.Tile

class B9: Tile(
    id = TileId.B9,
    color = TileColor.DARK,
    topTile = null,
    upperRightTile = C10(),
    underRightTile = C9(),
    bottomTile = B8(),
    underLeftTile = A8(),
    upperLeftTile = null
) {
}