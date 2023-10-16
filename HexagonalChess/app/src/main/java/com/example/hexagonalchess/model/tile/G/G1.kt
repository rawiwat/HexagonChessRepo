package com.example.hexagonalchess.model.tile.G

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.model.tile.F.F1
import com.example.hexagonalchess.model.tile.F.F2
import com.example.hexagonalchess.model.tile.H.H1
import com.example.hexagonalchess.model.tile.Tile

class G1: Tile(
    id = TileId.G1,
    color = TileColor.MID,
    topTile = G2(),
    upperRightTile = H1(),
    underRightTile = null,
    bottomTile = null,
    underLeftTile = F1(),
    upperLeftTile = F2()
) {
}