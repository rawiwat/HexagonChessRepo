package com.example.hexagonalchess.model.tile.H

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.model.tile.G.G1
import com.example.hexagonalchess.model.tile.G.G2
import com.example.hexagonalchess.model.tile.I.I1
import com.example.hexagonalchess.model.tile.Tile

class H1: Tile(
    id = TileId.H1,
    color = TileColor.LIGHT,
    topTile = H2(),
    upperRightTile = I1(),
    underRightTile = null,
    bottomTile = null,
    underLeftTile = G1(),
    upperLeftTile = G2()
) {
}