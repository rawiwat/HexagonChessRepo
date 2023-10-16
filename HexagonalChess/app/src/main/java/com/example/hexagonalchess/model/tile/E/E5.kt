package com.example.hexagonalchess.model.tile.E

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.model.tile.D.D4
import com.example.hexagonalchess.model.tile.D.D5
import com.example.hexagonalchess.model.tile.F.F4
import com.example.hexagonalchess.model.tile.F.F5
import com.example.hexagonalchess.model.tile.Tile

class E5: Tile(
    id = TileId.E5,
    color = TileColor.MID,
    topTile = E6(),
    upperRightTile = F5(),
    underRightTile = F4(),
    bottomTile = E4(),
    underLeftTile = D4(),
    upperLeftTile = D5()
) {
}