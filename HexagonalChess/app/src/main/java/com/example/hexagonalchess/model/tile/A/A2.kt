package com.example.hexagonalchess.model.tile.A

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.model.tile.B.B2
import com.example.hexagonalchess.model.tile.B.B3
import com.example.hexagonalchess.model.tile.Tile

class A2: Tile(
    id = TileId.A2,
    color = TileColor.MID,
    topTile = A3(),
    upperRightTile = B3(),
    underRightTile = B2(),
    bottomTile = A1(),
    underLeftTile = null,
    upperLeftTile = null
)