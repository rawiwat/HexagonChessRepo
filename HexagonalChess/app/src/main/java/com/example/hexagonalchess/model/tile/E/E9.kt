package com.example.hexagonalchess.model.tile.E

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.model.tile.D.D8
import com.example.hexagonalchess.model.tile.D.D9
import com.example.hexagonalchess.model.tile.F.F8
import com.example.hexagonalchess.model.tile.F.F9
import com.example.hexagonalchess.model.tile.Tile

class E9: Tile(
    id = TileId.E9,
    color = TileColor.DARK,
    topTile = E10(),
    upperRightTile = F9(),
    underRightTile = F8(),
    bottomTile = E8(),
    underLeftTile = D8(),
    upperLeftTile = D9()
) {
}