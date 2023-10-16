package com.example.hexagonalchess.model.tile.F

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.model.tile.E.E7
import com.example.hexagonalchess.model.tile.E.E8
import com.example.hexagonalchess.model.tile.G.G6
import com.example.hexagonalchess.model.tile.G.G7
import com.example.hexagonalchess.model.tile.Tile

class F7: Tile(
    id = TileId.F7,
    color = TileColor.DARK,
    topTile = F8(),
    upperRightTile = G7(),
    underRightTile = G6(),
    bottomTile = F6(),
    underLeftTile = E7(),
    upperLeftTile = E8()
) {
}