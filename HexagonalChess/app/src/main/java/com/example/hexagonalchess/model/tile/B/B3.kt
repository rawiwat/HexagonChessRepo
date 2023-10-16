package com.example.hexagonalchess.model.tile.B

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.model.tile.A.A2
import com.example.hexagonalchess.model.tile.A.A3
import com.example.hexagonalchess.model.tile.C.C3
import com.example.hexagonalchess.model.tile.C.C4
import com.example.hexagonalchess.model.tile.Tile

class B3:Tile(
    id = TileId.B3,
    color = TileColor.DARK,
    topTile = B4(),
    upperRightTile = C4(),
    underRightTile = C3(),
    bottomTile = B2(),
    underLeftTile = A2(),
    upperLeftTile = A3()
) {
}