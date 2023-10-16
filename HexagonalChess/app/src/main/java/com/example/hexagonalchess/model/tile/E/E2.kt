package com.example.hexagonalchess.model.tile.E

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.model.tile.D.D1
import com.example.hexagonalchess.model.tile.D.D2
import com.example.hexagonalchess.model.tile.F.F1
import com.example.hexagonalchess.model.tile.F.F2
import com.example.hexagonalchess.model.tile.Tile

class E2: Tile(
    id = TileId.E2,
    color = TileColor.MID,
    topTile = E3(),
    upperRightTile = F2(),
    underRightTile = F1(),
    bottomTile = E1(),
    underLeftTile = D1(),
    upperLeftTile = D2()
) {
}