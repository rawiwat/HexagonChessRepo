package com.example.hexagonalchess.model.tile.E

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.model.tile.D.D10
import com.example.hexagonalchess.model.tile.D.D9
import com.example.hexagonalchess.model.tile.F.F10
import com.example.hexagonalchess.model.tile.F.F9
import com.example.hexagonalchess.model.tile.Tile

class E10: Tile(
    id = TileId.E10,
    color = TileColor.LIGHT,
    topTile = E11(),
    upperRightTile = F10(),
    underRightTile = F9(),
    bottomTile = E9(),
    underLeftTile = D9(),
    upperLeftTile = D10()
) {
}