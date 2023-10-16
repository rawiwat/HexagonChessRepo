package com.example.hexagonalchess.model.tile.E

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.model.tile.D.D5
import com.example.hexagonalchess.model.tile.D.D6
import com.example.hexagonalchess.model.tile.F.F5
import com.example.hexagonalchess.model.tile.F.F6
import com.example.hexagonalchess.model.tile.Tile

class E6: Tile(
    id = TileId.E6,
    color = TileColor.DARK,
    topTile = E7(),
    upperRightTile = F6(),
    underRightTile = F5(),
    bottomTile = E5(),
    underLeftTile = D5(),
    upperLeftTile = D6()
) {
}