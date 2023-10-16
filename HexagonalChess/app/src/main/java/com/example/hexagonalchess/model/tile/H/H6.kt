package com.example.hexagonalchess.model.tile.H

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.model.tile.G.G5
import com.example.hexagonalchess.model.tile.G.G6
import com.example.hexagonalchess.model.tile.G.G7
import com.example.hexagonalchess.model.tile.Tile

class H6: Tile(
    id = TileId.H6,
    color = TileColor.DARK,
    topTile = H7(),
    upperRightTile = G6(),
    underRightTile = G5(),
    bottomTile = H5(),
    underLeftTile = G6(),
    upperLeftTile = G7()
) {
}