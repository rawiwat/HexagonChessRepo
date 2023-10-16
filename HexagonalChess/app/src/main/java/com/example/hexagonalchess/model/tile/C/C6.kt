package com.example.hexagonalchess.model.tile.C

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.model.tile.D.D6
import com.example.hexagonalchess.model.tile.D.D7
import com.example.hexagonalchess.model.tile.Tile

class C6: Tile(
    id = TileId.C6,
    color = TileColor.LIGHT,
    topTile = C7(),
    upperRightTile = D7(),
    underRightTile = D6(),
    bottomTile = C5(),
    underLeftTile = C5(),
    upperLeftTile = C6()
) {
}