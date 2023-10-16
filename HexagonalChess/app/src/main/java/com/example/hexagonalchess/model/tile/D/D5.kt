package com.example.hexagonalchess.model.tile.D

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.model.tile.C.C4
import com.example.hexagonalchess.model.tile.C.C5
import com.example.hexagonalchess.model.tile.E.E5
import com.example.hexagonalchess.model.tile.E.E6
import com.example.hexagonalchess.model.tile.Tile

class D5: Tile(
    id = TileId.D5,
    color = TileColor.LIGHT,
    topTile = D6(),
    upperRightTile = E6(),
    underRightTile = E5(),
    bottomTile = D4(),
    underLeftTile = C4(),
    upperLeftTile = C5()
) {
}