package com.example.hexagonalchess.model.tile.F

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.model.tile.E.E3
import com.example.hexagonalchess.model.tile.E.E4
import com.example.hexagonalchess.model.tile.G.G2
import com.example.hexagonalchess.model.tile.G.G3
import com.example.hexagonalchess.model.tile.Tile

class F3: Tile(
    id = TileId.E3,
    color = TileColor.MID,
    topTile = F4(),
    upperRightTile = G3(),
    underRightTile = G2(),
    bottomTile = F2(),
    underLeftTile = E3(),
    upperLeftTile = E4()
) {
}