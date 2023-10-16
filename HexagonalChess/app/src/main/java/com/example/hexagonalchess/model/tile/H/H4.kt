package com.example.hexagonalchess.model.tile.H

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.model.tile.G.G2
import com.example.hexagonalchess.model.tile.G.G4
import com.example.hexagonalchess.model.tile.G.G5
import com.example.hexagonalchess.model.tile.I.I3
import com.example.hexagonalchess.model.tile.I.I4
import com.example.hexagonalchess.model.tile.Tile

class H4: Tile(
    id = TileId.H4,
    color = TileColor.LIGHT,
    topTile = H5(),
    upperRightTile = I4(),
    underRightTile = I3(),
    bottomTile = H3(),
    underLeftTile = G4(),
    upperLeftTile = G5()
) {
}