package com.example.hexagonalchess.model.tile.F

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.model.tile.E.E8
import com.example.hexagonalchess.model.tile.E.E9
import com.example.hexagonalchess.model.tile.G.G7
import com.example.hexagonalchess.model.tile.G.G8
import com.example.hexagonalchess.model.tile.Tile

class F8: Tile(
    id = TileId.F8,
    color = TileColor.LIGHT,
    topTile = F9(),
    upperRightTile = G8(),
    underRightTile = G7(),
    bottomTile = F7(),
    underLeftTile = E8(),
    upperLeftTile = E9()
) {
}