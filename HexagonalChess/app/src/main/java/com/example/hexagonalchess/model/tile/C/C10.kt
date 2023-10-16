package com.example.hexagonalchess.model.tile.C

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.model.tile.B.B9
import com.example.hexagonalchess.model.tile.D.D10
import com.example.hexagonalchess.model.tile.D.D11
import com.example.hexagonalchess.model.tile.Tile

class C10: Tile(
    id = TileId.C10,
    color = TileColor.MID,
    topTile = null,
    upperRightTile = D11(),
    underRightTile = D10(),
    bottomTile = C9(),
    underLeftTile = B9(),
    upperLeftTile = null
) {
}