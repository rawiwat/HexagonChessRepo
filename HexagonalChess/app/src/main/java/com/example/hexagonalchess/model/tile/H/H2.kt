package com.example.hexagonalchess.model.tile.H

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.model.tile.G.G2
import com.example.hexagonalchess.model.tile.G.G3
import com.example.hexagonalchess.model.tile.I.I1
import com.example.hexagonalchess.model.tile.I.I2
import com.example.hexagonalchess.model.tile.Tile

class H2: Tile(
    id = TileId.H2,
    color = TileColor.MID,
    topTile = H3(),
    upperRightTile = I2(),
    underRightTile = I1(),
    bottomTile = H1(),
    underLeftTile = G2(),
    upperLeftTile = G3()
) {
}