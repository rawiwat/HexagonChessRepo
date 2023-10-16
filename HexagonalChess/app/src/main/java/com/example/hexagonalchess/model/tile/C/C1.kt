package com.example.hexagonalchess.model.tile.C

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.model.tile.B.B1
import com.example.hexagonalchess.model.tile.D.D1
import com.example.hexagonalchess.model.tile.D.D2
import com.example.hexagonalchess.model.tile.Tile

class C1: Tile(
    id = TileId.C1,
    color = TileColor.MID,
    topTile = C2(),
    upperRightTile = D2(),
    underRightTile = D1(),
    bottomTile = null,
    underLeftTile = null,
    upperLeftTile = B1()
) {
}