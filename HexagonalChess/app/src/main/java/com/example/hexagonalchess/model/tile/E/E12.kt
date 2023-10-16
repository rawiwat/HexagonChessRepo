package com.example.hexagonalchess.model.tile.E

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.model.tile.D.D11
import com.example.hexagonalchess.model.tile.F.F11
import com.example.hexagonalchess.model.tile.Tile

class E12: Tile(
    id = TileId.E12,
    color = TileColor.DARK,
    topTile = null,
    upperRightTile = F11(),
    underRightTile = null,
    bottomTile = E11(),
    underLeftTile = D11(),
    upperLeftTile = null
) {
}