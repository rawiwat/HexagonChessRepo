package com.example.hexagonalchess.model.tile.H

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.model.tile.G.G3
import com.example.hexagonalchess.model.tile.G.G4
import com.example.hexagonalchess.model.tile.I.I2
import com.example.hexagonalchess.model.tile.I.I3
import com.example.hexagonalchess.model.tile.Tile

class H3: Tile(
    id = TileId.H3,
    color = TileColor.DARK,
    topTile = H4(),
    upperRightTile = I3(),
    underRightTile = I2(),
    bottomTile = H2(),
    underLeftTile = G3(),
    upperLeftTile = G4()
) {
}