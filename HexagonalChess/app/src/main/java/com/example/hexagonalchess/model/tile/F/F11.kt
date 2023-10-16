package com.example.hexagonalchess.model.tile.F

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.model.tile.E.E11
import com.example.hexagonalchess.model.tile.E.E12
import com.example.hexagonalchess.model.tile.G.G10
import com.example.hexagonalchess.model.tile.Tile

class F11: Tile(
    id = TileId.F11,
    color = TileColor.LIGHT,
    topTile = null,
    upperRightTile = null,
    underRightTile = G10(),
    bottomTile = F10(),
    underLeftTile = E11(),
    upperLeftTile = E12()
)