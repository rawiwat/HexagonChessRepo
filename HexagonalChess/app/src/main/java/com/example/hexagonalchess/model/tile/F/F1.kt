package com.example.hexagonalchess.model.tile.F

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.model.tile.E.E1
import com.example.hexagonalchess.model.tile.E.E2
import com.example.hexagonalchess.model.tile.G.G1
import com.example.hexagonalchess.model.tile.Tile

class F1: Tile(
    id = TileId.F1,
    color = TileColor.DARK,
    topTile = F2(),
    upperRightTile = G1(),
    underRightTile = null,
    bottomTile = null,
    underLeftTile = E1(),
    upperLeftTile = E2()
) {
}