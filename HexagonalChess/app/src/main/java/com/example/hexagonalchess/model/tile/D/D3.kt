package com.example.hexagonalchess.model.tile.D

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.model.tile.C.C2
import com.example.hexagonalchess.model.tile.C.C3
import com.example.hexagonalchess.model.tile.E.E3
import com.example.hexagonalchess.model.tile.E.E4
import com.example.hexagonalchess.model.tile.Tile

class D3: Tile(
    id = TileId.D3,
    color = TileColor.MID,
    topTile = D4(),
    upperRightTile = E4(),
    underRightTile = E3(),
    bottomTile = D2(),
    underLeftTile = C2(),
    upperLeftTile = C3()
) {
}