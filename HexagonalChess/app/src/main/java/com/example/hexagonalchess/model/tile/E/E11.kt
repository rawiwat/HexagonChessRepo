package com.example.hexagonalchess.model.tile.E

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.model.tile.D.D10
import com.example.hexagonalchess.model.tile.D.D11
import com.example.hexagonalchess.model.tile.F.F10
import com.example.hexagonalchess.model.tile.F.F11
import com.example.hexagonalchess.model.tile.Tile

class E11: Tile(
    id = TileId.E11,
    color = TileColor.MID,
    topTile = E12(),
    upperRightTile = F11(),
    underRightTile = F10(),
    bottomTile = E10(),
    underLeftTile = D10(),
    upperLeftTile = D11()
)