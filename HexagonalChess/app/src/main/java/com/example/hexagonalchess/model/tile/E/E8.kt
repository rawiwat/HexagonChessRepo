package com.example.hexagonalchess.model.tile.E

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.model.tile.D.D7
import com.example.hexagonalchess.model.tile.D.D8
import com.example.hexagonalchess.model.tile.F.F7
import com.example.hexagonalchess.model.tile.F.F8
import com.example.hexagonalchess.model.tile.Tile

class E8: Tile(
    id = TileId.E8,
    color = TileColor.MID,
    topTile = E9(),
    upperRightTile = F8(),
    underRightTile = F7(),
    bottomTile = E7(),
    underLeftTile = D7(),
    upperLeftTile = D8()
)