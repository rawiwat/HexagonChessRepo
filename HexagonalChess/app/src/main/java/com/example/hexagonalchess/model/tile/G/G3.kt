package com.example.hexagonalchess.model.tile.G

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.model.tile.F.F3
import com.example.hexagonalchess.model.tile.F.F4
import com.example.hexagonalchess.model.tile.H.H2
import com.example.hexagonalchess.model.tile.H.H3
import com.example.hexagonalchess.model.tile.Tile

class G3: Tile(
    id = TileId.G3,
    color = TileColor.LIGHT,
    topTile = G4(),
    upperRightTile = H3(),
    underRightTile = H2(),
    bottomTile = G2(),
    underLeftTile = F3(),
    upperLeftTile = F4()
) {
}