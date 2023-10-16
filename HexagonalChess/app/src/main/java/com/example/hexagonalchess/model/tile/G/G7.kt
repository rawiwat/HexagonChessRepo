package com.example.hexagonalchess.model.tile.G

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.model.tile.F.F7
import com.example.hexagonalchess.model.tile.F.F8
import com.example.hexagonalchess.model.tile.H.H6
import com.example.hexagonalchess.model.tile.H.H7
import com.example.hexagonalchess.model.tile.Tile

class G7: Tile(
    id = TileId.G7,
    color = TileColor.MID,
    topTile = G8(),
    upperRightTile = H7(),
    underRightTile = H6(),
    bottomTile = G6(),
    underLeftTile = F7(),
    upperLeftTile = F8()
) {
}