package com.example.hexagonalchess.model.tile.D

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.model.tile.C.C1
import com.example.hexagonalchess.model.tile.C.C2
import com.example.hexagonalchess.model.tile.E.E2
import com.example.hexagonalchess.model.tile.E.E3
import com.example.hexagonalchess.model.tile.Tile

class D2: Tile(
    id = TileId.D2,
    color = TileColor.LIGHT,
    topTile = D3(),
    upperRightTile = E3(),
    underRightTile = E2(),
    bottomTile = D1(),
    underLeftTile = C1(),
    upperLeftTile = C2()
) {
}