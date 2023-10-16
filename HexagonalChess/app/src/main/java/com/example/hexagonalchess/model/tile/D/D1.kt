package com.example.hexagonalchess.model.tile.D

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.model.tile.C.C1
import com.example.hexagonalchess.model.tile.E.E1
import com.example.hexagonalchess.model.tile.E.E2
import com.example.hexagonalchess.model.tile.Tile

class D1: Tile(
    id = TileId.D1,
    color = TileColor.DARK,
    topTile = D2(),
    upperRightTile = E2(),
    underRightTile = E1(),
    bottomTile = null,
    underLeftTile = null,
    upperLeftTile = C1()
) {
}