package com.example.hexagonalchess.model.tile.F

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.model.tile.E.E10
import com.example.hexagonalchess.model.tile.E.E11
import com.example.hexagonalchess.model.tile.G.G10
import com.example.hexagonalchess.model.tile.G.G9
import com.example.hexagonalchess.model.tile.Tile

class F10: Tile(
    id = TileId.F10,
    color = TileColor.DARK,
    topTile = F11(),
    upperRightTile = G10(),
    underRightTile = G9(),
    bottomTile = F9(),
    underLeftTile = E10(),
    upperLeftTile = E11()
) {
}