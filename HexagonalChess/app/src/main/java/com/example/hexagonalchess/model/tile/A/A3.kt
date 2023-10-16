package com.example.hexagonalchess.model.tile.A

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.model.tile.B.B3
import com.example.hexagonalchess.model.tile.B.B4
import com.example.hexagonalchess.model.tile.Tile

class A3:Tile(
    id = TileId.A3,
    color = TileColor.LIGHT,
    topTile = A4(),
    upperRightTile = B4(),
    underRightTile = B3(),
    bottomTile = A2(),
    underLeftTile = null,
    upperLeftTile = null
)