package com.example.hexagonalchess.model.tile.C

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.model.tile.B.B1
import com.example.hexagonalchess.model.tile.B.B2
import com.example.hexagonalchess.model.tile.D.D2
import com.example.hexagonalchess.model.tile.D.D3
import com.example.hexagonalchess.model.tile.Tile

class C2: Tile(
    id = TileId.C2,
    color = TileColor.DARK,
    topTile = C3(),
    upperRightTile = D3(),
    underRightTile = D2(),
    bottomTile = C1(),
    underLeftTile = B1(),
    upperLeftTile = B2()
)