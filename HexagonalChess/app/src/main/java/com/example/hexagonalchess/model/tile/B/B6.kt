package com.example.hexagonalchess.model.tile.B

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.model.tile.A.A5
import com.example.hexagonalchess.model.tile.A.A6
import com.example.hexagonalchess.model.tile.C.C6
import com.example.hexagonalchess.model.tile.C.C7
import com.example.hexagonalchess.model.tile.Tile

class B6: Tile(
    id = TileId.B6,
    color = TileColor.DARK,
    topTile = B7(),
    upperRightTile = C7(),
    underRightTile = C6(),
    bottomTile = B5(),
    underLeftTile = A5(),
    upperLeftTile = A6()
)