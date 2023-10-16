package com.example.hexagonalchess.model.tile.G

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.model.tile.F.F10
import com.example.hexagonalchess.model.tile.F.F11
import com.example.hexagonalchess.model.tile.H.H9
import com.example.hexagonalchess.model.tile.Tile

class G10: Tile(
    id = TileId.G10,
    color = TileColor.MID,
    topTile = null,
    upperRightTile = null,
    underRightTile = H9(),
    bottomTile = G9(),
    underLeftTile = F10(),
    upperLeftTile = F11()
) {
}