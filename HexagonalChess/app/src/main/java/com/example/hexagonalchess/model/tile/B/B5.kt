package com.example.hexagonalchess.model.tile.B

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.model.tile.A.A4
import com.example.hexagonalchess.model.tile.A.A5
import com.example.hexagonalchess.model.tile.C.C5
import com.example.hexagonalchess.model.tile.C.C6
import com.example.hexagonalchess.model.tile.Tile

class B5: Tile(
    id = TileId.B5,
    color = TileColor.MID,
    topTile = B6(),
    upperRightTile = C6(),
    underRightTile = C5(),
    bottomTile = B4(),
    underLeftTile = A4(),
    upperLeftTile = A5()
) {
}