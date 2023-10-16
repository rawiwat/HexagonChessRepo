package com.example.hexagonalchess.model.tile.D

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.model.tile.C.C6
import com.example.hexagonalchess.model.tile.C.C7
import com.example.hexagonalchess.model.tile.E.E7
import com.example.hexagonalchess.model.tile.E.E8
import com.example.hexagonalchess.model.tile.Tile

class D7: Tile(
    id = TileId.D7,
    color = TileColor.DARK,
    topTile = D8(),
    upperRightTile = E8(),
    underRightTile = E7(),
    bottomTile = D6(),
    underLeftTile = C6(),
    upperLeftTile = C7()
) {
}