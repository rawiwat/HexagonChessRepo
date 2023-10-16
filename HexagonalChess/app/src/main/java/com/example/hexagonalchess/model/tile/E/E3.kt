package com.example.hexagonalchess.model.tile.E

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.model.tile.D.D2
import com.example.hexagonalchess.model.tile.D.D3
import com.example.hexagonalchess.model.tile.F.F2
import com.example.hexagonalchess.model.tile.F.F3
import com.example.hexagonalchess.model.tile.Tile

class E3: Tile(
    id = TileId.E3,
    color = TileColor.DARK,
    topTile = E4(),
    upperRightTile = F3(),
    underRightTile = F2(),
    bottomTile = E2(),
    underLeftTile = D2(),
    upperLeftTile = D3()
) {
}