package com.example.hexagonalchess.model.tile.D

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.model.tile.C.C10
import com.example.hexagonalchess.model.tile.C.C9
import com.example.hexagonalchess.model.tile.E.E10
import com.example.hexagonalchess.model.tile.E.E11
import com.example.hexagonalchess.model.tile.Tile

class D10: Tile(
    id = TileId.D10,
    color = TileColor.DARK,
    topTile = D11(),
    upperRightTile = E11(),
    underRightTile = E10(),
    bottomTile = D9(),
    underLeftTile = C9(),
    upperLeftTile = C10()
) {
}