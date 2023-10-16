package com.example.hexagonalchess.model.tile.G

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.model.tile.F.F10
import com.example.hexagonalchess.model.tile.F.F9
import com.example.hexagonalchess.model.tile.H.H8
import com.example.hexagonalchess.model.tile.H.H9
import com.example.hexagonalchess.model.tile.Tile

class G9: Tile(
    id = TileId.F1,
    color = TileColor.LIGHT,
    topTile = G10(),
    upperRightTile = H9(),
    underRightTile = H8(),
    bottomTile = G8(),
    underLeftTile = F9(),
    upperLeftTile = F10()
) {
}