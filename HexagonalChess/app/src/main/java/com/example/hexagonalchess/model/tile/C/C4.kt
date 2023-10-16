package com.example.hexagonalchess.model.tile.C

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.model.tile.B.B3
import com.example.hexagonalchess.model.tile.B.B4
import com.example.hexagonalchess.model.tile.D.D4
import com.example.hexagonalchess.model.tile.D.D5
import com.example.hexagonalchess.model.tile.Tile

class C4: Tile(
    id = TileId.C4,
    color = TileColor.MID,
    topTile = C5(),
    upperRightTile = D5(),
    underRightTile = D4(),
    bottomTile = C3(),
    underLeftTile = B3(),
    upperLeftTile = B4()
)