package com.example.hexagonalchess.model.tile.C

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.model.tile.B.B8
import com.example.hexagonalchess.model.tile.B.B9
import com.example.hexagonalchess.model.tile.D.D10
import com.example.hexagonalchess.model.tile.D.D9
import com.example.hexagonalchess.model.tile.Tile

class C9: Tile(
    id = TileId.C9,
    color = TileColor.LIGHT,
    topTile = C10(),
    upperRightTile = D10(),
    underRightTile = D9(),
    bottomTile = C8(),
    underLeftTile = B8(),
    upperLeftTile = B9()
) {
}