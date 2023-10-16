package com.example.hexagonalchess.model.tile.B

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.model.tile.A.A1
import com.example.hexagonalchess.model.tile.C.C1
import com.example.hexagonalchess.model.tile.C.C2
import com.example.hexagonalchess.model.tile.Tile

class B1:Tile(
    id = TileId.B1,
    color = TileColor.LIGHT,
    topTile = B2(),
    upperRightTile = C2(),
    underRightTile = C1(),
    bottomTile = null,
    underLeftTile = null,
    upperLeftTile = A1()
)