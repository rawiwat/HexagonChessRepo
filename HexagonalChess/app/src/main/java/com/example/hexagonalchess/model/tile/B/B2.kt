package com.example.hexagonalchess.model.tile.B

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.model.tile.A.A1
import com.example.hexagonalchess.model.tile.A.A2
import com.example.hexagonalchess.model.tile.C.C2
import com.example.hexagonalchess.model.tile.C.C3
import com.example.hexagonalchess.model.tile.Tile

class B2:Tile(
    id = TileId.B2,
    color = TileColor.MID,
    topTile = B3(),
    upperRightTile = C3(),
    underRightTile = C2(),
    bottomTile = B1(),
    underLeftTile = A1(),
    upperLeftTile = A2()
)