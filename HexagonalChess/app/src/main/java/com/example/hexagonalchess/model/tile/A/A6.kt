package com.example.hexagonalchess.model.tile.A

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.model.tile.B.B6
import com.example.hexagonalchess.model.tile.B.B7
import com.example.hexagonalchess.model.tile.Tile

class A6:Tile(
    id = TileId.A6,
    color = TileColor.MID,
    topTile = A7(),
    upperRightTile = B7(),
    underRightTile = B6(),
    bottomTile = A5(),
    underLeftTile = null,
    upperLeftTile = null
)