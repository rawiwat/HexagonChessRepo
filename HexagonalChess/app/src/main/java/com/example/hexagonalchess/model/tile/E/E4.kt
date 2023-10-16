package com.example.hexagonalchess.model.tile.E

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.model.tile.D.D3
import com.example.hexagonalchess.model.tile.D.D4
import com.example.hexagonalchess.model.tile.F.F3
import com.example.hexagonalchess.model.tile.F.F4
import com.example.hexagonalchess.model.tile.Tile

class E4: Tile(
    id = TileId.E4,
    color = TileColor.LIGHT,
    topTile = E5(),
    upperRightTile = F4(),
    underRightTile = F3(),
    bottomTile = E3(),
    underLeftTile = D3(),
    upperLeftTile = D4()
) {
}