package com.example.hexagonalchess.model.tile.C

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.model.tile.B.B7
import com.example.hexagonalchess.model.tile.B.B8
import com.example.hexagonalchess.model.tile.D.D8
import com.example.hexagonalchess.model.tile.D.D9
import com.example.hexagonalchess.model.tile.Tile

class C8: Tile(
    id = TileId.C8,
    color = TileColor.DARK,
    topTile = C9(),
    upperRightTile = D9(),
    underRightTile = D8(),
    bottomTile = C7(),
    underLeftTile = B7(),
    upperLeftTile = B8()
)