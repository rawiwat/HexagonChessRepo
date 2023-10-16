package com.example.hexagonalchess.model.tile.D

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.model.tile.C.C3
import com.example.hexagonalchess.model.tile.C.C4
import com.example.hexagonalchess.model.tile.E.E4
import com.example.hexagonalchess.model.tile.E.E5
import com.example.hexagonalchess.model.tile.Tile

class D4: Tile(
    id = TileId.D4,
    color = TileColor.DARK,
    topTile = D5(),
    upperRightTile = E5(),
    underRightTile = E4(),
    bottomTile = D3(),
    underLeftTile = C3(),
    upperLeftTile = C4()
) {
}