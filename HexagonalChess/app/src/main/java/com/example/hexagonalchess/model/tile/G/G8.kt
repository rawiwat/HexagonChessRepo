package com.example.hexagonalchess.model.tile.G

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.model.tile.F.F8
import com.example.hexagonalchess.model.tile.F.F9
import com.example.hexagonalchess.model.tile.H.H7
import com.example.hexagonalchess.model.tile.H.H8
import com.example.hexagonalchess.model.tile.Tile

class G8: Tile(
    id = TileId.G8,
    color = TileColor.DARK,
    topTile = G9(),
    upperRightTile = H8(),
    underRightTile = H7(),
    bottomTile = G7(),
    underLeftTile = F8(),
    upperLeftTile = F9()
) {
}