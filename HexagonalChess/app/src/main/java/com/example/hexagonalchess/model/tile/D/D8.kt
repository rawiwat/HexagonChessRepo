package com.example.hexagonalchess.model.tile.D

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.model.tile.C.C7
import com.example.hexagonalchess.model.tile.C.C8
import com.example.hexagonalchess.model.tile.E.E8
import com.example.hexagonalchess.model.tile.E.E9
import com.example.hexagonalchess.model.tile.Tile

class D8: Tile(
    id = TileId.D8,
    color = TileColor.LIGHT,
    topTile = D9(),
    upperRightTile = E9(),
    underRightTile = E8(),
    bottomTile = D7(),
    underLeftTile = C7(),
    upperLeftTile = C8()
) {
}