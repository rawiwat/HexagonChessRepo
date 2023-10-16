package com.example.hexagonalchess.model.tile.B

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.model.tile.A.A7
import com.example.hexagonalchess.model.tile.A.A8
import com.example.hexagonalchess.model.tile.C.C8
import com.example.hexagonalchess.model.tile.C.C9
import com.example.hexagonalchess.model.tile.Tile

class B8: Tile(
    id = TileId.B8,
    color = TileColor.MID,
    topTile = B9(),
    upperRightTile = C9(),
    underRightTile = C8(),
    bottomTile = B7(),
    underLeftTile = A7(),
    upperLeftTile = A8()
) {
}