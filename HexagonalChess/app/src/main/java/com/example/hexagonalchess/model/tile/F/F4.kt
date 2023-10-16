package com.example.hexagonalchess.model.tile.F

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.model.tile.E.E4
import com.example.hexagonalchess.model.tile.E.E5
import com.example.hexagonalchess.model.tile.G.G3
import com.example.hexagonalchess.model.tile.G.G4
import com.example.hexagonalchess.model.tile.Tile

class F4: Tile(
    id = TileId.F4,
    color = TileColor.DARK,
    topTile = F5(),
    upperRightTile = G4(),
    underRightTile = G3(),
    bottomTile = F3(),
    underLeftTile = E4(),
    upperLeftTile = E5()
) {
}