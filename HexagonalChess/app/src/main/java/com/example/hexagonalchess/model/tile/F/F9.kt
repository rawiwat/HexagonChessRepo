package com.example.hexagonalchess.model.tile.F

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.model.tile.E.E10
import com.example.hexagonalchess.model.tile.E.E9
import com.example.hexagonalchess.model.tile.G.G8
import com.example.hexagonalchess.model.tile.G.G9
import com.example.hexagonalchess.model.tile.Tile

class F9: Tile(
    id = TileId.F9,
    color = TileColor.MID,
    topTile = F10(),
    upperRightTile = G9(),
    underRightTile = G8(),
    bottomTile = F8(),
    underLeftTile = E9(),
    upperLeftTile = E10()
)
