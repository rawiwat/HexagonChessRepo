package com.example.hexagonalchess.model.tile.D

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.model.tile.C.C5
import com.example.hexagonalchess.model.tile.C.C6
import com.example.hexagonalchess.model.tile.E.E6
import com.example.hexagonalchess.model.tile.E.E7
import com.example.hexagonalchess.model.tile.Tile

class D6: Tile(
    id = TileId.D6,
    color = TileColor.MID,
    topTile = D7(),
    upperRightTile = E7(),
    underRightTile = E6(),
    bottomTile = D5(),
    underLeftTile = C5(),
    upperLeftTile = C6()
) {
}