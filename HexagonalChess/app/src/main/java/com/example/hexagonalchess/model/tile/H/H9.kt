package com.example.hexagonalchess.model.tile.H

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.model.tile.G.G10
import com.example.hexagonalchess.model.tile.G.G8
import com.example.hexagonalchess.model.tile.G.G9
import com.example.hexagonalchess.model.tile.I.I8
import com.example.hexagonalchess.model.tile.Tile

class H9: Tile(
    id = TileId.H9,
    color = TileColor.DARK,
    topTile = null,
    upperRightTile = null,
    underRightTile = I8(),
    bottomTile = G8(),
    underLeftTile = G9(),
    upperLeftTile = G10()
) {
}