package com.example.hexagonalchess.model.tile.E

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.model.tile.D.D6
import com.example.hexagonalchess.model.tile.D.D7
import com.example.hexagonalchess.model.tile.F.F6
import com.example.hexagonalchess.model.tile.F.F7
import com.example.hexagonalchess.model.tile.Tile

class E7: Tile(
    id = TileId.E7,
    color = TileColor.LIGHT,
    topTile = E8(),
    upperRightTile = F7(),
    underRightTile = F6(),
    bottomTile = E6(),
    underLeftTile = D6(),
    upperLeftTile = D7()
) {
}