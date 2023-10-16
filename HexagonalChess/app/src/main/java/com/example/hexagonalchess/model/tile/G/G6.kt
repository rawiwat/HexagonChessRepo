package com.example.hexagonalchess.model.tile.G

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.model.tile.F.F6
import com.example.hexagonalchess.model.tile.F.F7
import com.example.hexagonalchess.model.tile.H.H5
import com.example.hexagonalchess.model.tile.H.H6
import com.example.hexagonalchess.model.tile.Tile

class G6: Tile(
    id = TileId.G6,
    color = TileColor.LIGHT,
    topTile = G7(),
    upperRightTile = H6(),
    underRightTile = H5(),
    bottomTile = G5(),
    underLeftTile = F6(),
    upperLeftTile = F7()
) {
}