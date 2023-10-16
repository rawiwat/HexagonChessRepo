package com.example.hexagonalchess.model.tile.H

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.model.tile.G.G5
import com.example.hexagonalchess.model.tile.G.G6
import com.example.hexagonalchess.model.tile.I.I3
import com.example.hexagonalchess.model.tile.I.I4
import com.example.hexagonalchess.model.tile.I.I5
import com.example.hexagonalchess.model.tile.Tile

class H5: Tile(
    id = TileId.H5,
    color = TileColor.MID,
    topTile = H6(),
    upperRightTile = I5(),
    underRightTile = I4(),
    bottomTile = H4(),
    underLeftTile = G5(),
    upperLeftTile = G6()
) {
}