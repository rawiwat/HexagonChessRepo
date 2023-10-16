package com.example.hexagonalchess.model.tile.A

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.model.tile.B.B5
import com.example.hexagonalchess.model.tile.B.B6
import com.example.hexagonalchess.model.tile.Tile

class A5:Tile(
    id = TileId.A5,
    color = TileColor.LIGHT,
    topTile = A6(),
    upperRightTile = B6(),
    underRightTile = B5(),
    bottomTile = A4(),
    underLeftTile = null,
    upperLeftTile = null
)