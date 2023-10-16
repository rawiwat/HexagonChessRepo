package com.example.hexagonalchess.model.tile.G

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.model.tile.F.F3
import com.example.hexagonalchess.model.tile.F.F5
import com.example.hexagonalchess.model.tile.F.F6
import com.example.hexagonalchess.model.tile.H.H4
import com.example.hexagonalchess.model.tile.H.H5
import com.example.hexagonalchess.model.tile.Tile

class G5: Tile(
    id = TileId.G5,
    color = TileColor.DARK,
    topTile = G6(),
    upperRightTile = H5(),
    underRightTile = H4(),
    bottomTile = G4(),
    underLeftTile = F5(),
    upperLeftTile = F6()
) {
}