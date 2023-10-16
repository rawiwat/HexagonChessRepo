package com.example.hexagonalchess.model.tile.F

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.model.tile.E.E2
import com.example.hexagonalchess.model.tile.E.E3
import com.example.hexagonalchess.model.tile.G.G1
import com.example.hexagonalchess.model.tile.G.G2
import com.example.hexagonalchess.model.tile.Tile

class F2: Tile(
    id = TileId.F2,
    color = TileColor.LIGHT,
    topTile = F3(),
    upperRightTile = G2(),
    underRightTile = G1(),
    bottomTile = F1(),
    underLeftTile = E2(),
    upperLeftTile = E3()
) {
}