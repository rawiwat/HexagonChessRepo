package com.example.hexagonalchess.model.tile.B

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.model.tile.A.A3
import com.example.hexagonalchess.model.tile.A.A4
import com.example.hexagonalchess.model.tile.C.C4
import com.example.hexagonalchess.model.tile.C.C5
import com.example.hexagonalchess.model.tile.Tile

class B4: Tile(
    id = TileId.B4,
    color = TileColor.LIGHT,
    topTile = B5(),
    upperRightTile = C5(),
    underRightTile = C4(),
    bottomTile = B3(),
    underLeftTile = A3(),
    upperLeftTile = A4()
) {
}