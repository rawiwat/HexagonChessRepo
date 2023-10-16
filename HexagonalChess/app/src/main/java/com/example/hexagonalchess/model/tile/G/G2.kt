package com.example.hexagonalchess.model.tile.G

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.model.tile.F.F2
import com.example.hexagonalchess.model.tile.F.F3
import com.example.hexagonalchess.model.tile.H.H1
import com.example.hexagonalchess.model.tile.H.H2
import com.example.hexagonalchess.model.tile.Tile

class G2: Tile(
    id = TileId.G2,
    color = TileColor.DARK,
    topTile = G3(),
    upperRightTile = H2(),
    underRightTile = H1(),
    bottomTile = G1(),
    underLeftTile = F2(),
    upperLeftTile = F3()
) {
}