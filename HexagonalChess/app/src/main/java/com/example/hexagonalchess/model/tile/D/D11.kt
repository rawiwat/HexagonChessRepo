package com.example.hexagonalchess.model.tile.D

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.model.tile.C.C10
import com.example.hexagonalchess.model.tile.E.E11
import com.example.hexagonalchess.model.tile.E.E12
import com.example.hexagonalchess.model.tile.Tile

class D11: Tile(
    id = TileId.D11,
    color = TileColor.LIGHT,
    topTile = null,
    upperRightTile = E12(),
    underRightTile = E11(),
    bottomTile = D10(),
    underLeftTile = C10(),
    upperLeftTile = null
) {
}