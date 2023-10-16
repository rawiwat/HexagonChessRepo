package com.example.hexagonalchess.model.tile.F

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.model.tile.E.E5
import com.example.hexagonalchess.model.tile.E.E6
import com.example.hexagonalchess.model.tile.G.G4
import com.example.hexagonalchess.model.tile.G.G5
import com.example.hexagonalchess.model.tile.Tile

class F5: Tile(
    id = TileId.F5,
    color = TileColor.LIGHT,
    topTile = F6(),
    upperRightTile = G5(),
    underRightTile = G4(),
    bottomTile = F4(),
    underLeftTile = E5(),
    upperLeftTile = E6()
) {
}