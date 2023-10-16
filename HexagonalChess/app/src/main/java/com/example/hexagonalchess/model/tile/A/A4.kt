package com.example.hexagonalchess.model.tile.A

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.model.tile.B.B4
import com.example.hexagonalchess.model.tile.B.B5
import com.example.hexagonalchess.model.tile.Tile

class A4:Tile(
    id = TileId.A4,
    color = TileColor.DARK,
    topTile = A5(),
    upperRightTile = B5(),
    underRightTile = B4(),
    bottomTile = A6(),
    underLeftTile = null,
    upperLeftTile = null
)