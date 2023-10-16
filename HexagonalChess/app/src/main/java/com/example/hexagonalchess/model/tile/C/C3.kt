package com.example.hexagonalchess.model.tile.C

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.model.tile.B.B2
import com.example.hexagonalchess.model.tile.B.B3
import com.example.hexagonalchess.model.tile.D.D3
import com.example.hexagonalchess.model.tile.D.D4
import com.example.hexagonalchess.model.tile.Tile

class C3: Tile(
    id = TileId.C3,
    color = TileColor.LIGHT,
    topTile = C4(),
    upperRightTile = D4(),
    underRightTile = D3(),
    bottomTile = C2(),
    underLeftTile = B2(),
    upperLeftTile = B3()
) {
}