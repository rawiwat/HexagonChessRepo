package com.example.hexagonalchess.model.tile.H

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.model.tile.G.G7
import com.example.hexagonalchess.model.tile.G.G8
import com.example.hexagonalchess.model.tile.I.I6
import com.example.hexagonalchess.model.tile.I.I7
import com.example.hexagonalchess.model.tile.Tile

class H7: Tile(
    id = TileId.H7,
    color = TileColor.LIGHT,
    topTile = H8(),
    upperRightTile = I7(),
    underRightTile = I6(),
    bottomTile = H6(),
    underLeftTile = G7(),
    upperLeftTile = G8()
) {
}