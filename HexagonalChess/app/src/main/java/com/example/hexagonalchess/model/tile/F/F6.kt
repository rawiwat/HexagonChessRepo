package com.example.hexagonalchess.model.tile.F

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.model.tile.E.E6
import com.example.hexagonalchess.model.tile.E.E7
import com.example.hexagonalchess.model.tile.G.G5
import com.example.hexagonalchess.model.tile.G.G6
import com.example.hexagonalchess.model.tile.Tile

class F6: Tile(
    id = TileId.F6,
    color = TileColor.MID,
    topTile = F7(),
    upperRightTile = G6(),
    underRightTile = G5(),
    bottomTile = F5(),
    underLeftTile = E6(),
    upperLeftTile = E7()
) {
}