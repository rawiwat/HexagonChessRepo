package com.example.hexagonalchess.model.tile.E

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.model.tile.D.D1
import com.example.hexagonalchess.model.tile.F.F1
import com.example.hexagonalchess.model.tile.Tile

class E1: Tile(
    id = TileId.E1,
    color = TileColor.LIGHT,
    topTile = E2(),
    upperRightTile = F1(),
    underRightTile = null,
    bottomTile = null,
    underLeftTile = D1(),
    upperLeftTile = null
) {
}