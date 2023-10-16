package com.example.hexagonalchess.model.tile.C

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.model.tile.B.B4
import com.example.hexagonalchess.model.tile.B.B5
import com.example.hexagonalchess.model.tile.D.D5
import com.example.hexagonalchess.model.tile.D.D6
import com.example.hexagonalchess.model.tile.Tile

class C5: Tile(
    id = TileId.C5,
    color = TileColor.DARK,
    topTile = C6(),
    upperRightTile = D6(),
    underRightTile = D5(),
    bottomTile = C4(),
    underLeftTile = B4(),
    upperLeftTile = B5()
) {
}