package com.example.hexagonalchess.model.tile.D

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.model.tile.C.C8
import com.example.hexagonalchess.model.tile.C.C9
import com.example.hexagonalchess.model.tile.E.E10
import com.example.hexagonalchess.model.tile.E.E9
import com.example.hexagonalchess.model.tile.Tile

class D9: Tile(
    id = TileId.D9,
    color = TileColor.MID,
    topTile = D10(),
    upperRightTile = E10(),
    underRightTile = E9(),
    bottomTile = D8(),
    underLeftTile = C8(),
    upperLeftTile = C9()
)