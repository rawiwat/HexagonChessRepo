package com.example.hexagonalchess.model.tile.A

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.model.tile.B.B1
import com.example.hexagonalchess.model.tile.B.B2
import com.example.hexagonalchess.model.tile.Tile

class A1: Tile(
    id = TileId.A1,
    color = TileColor.DARK,
    topTile = A2(),
    upperRightTile = B2(),
    underRightTile = B1(),
    bottomTile = null,
    underLeftTile = null,
    upperLeftTile = null
)