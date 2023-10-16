package com.example.hexagonalchess.model.tile.G

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.model.tile.F.F4
import com.example.hexagonalchess.model.tile.F.F5
import com.example.hexagonalchess.model.tile.H.H3
import com.example.hexagonalchess.model.tile.H.H4
import com.example.hexagonalchess.model.tile.Tile

class G4: Tile(
    id = TileId.G4,
    color = TileColor.MID,
    topTile = G5(),
    upperRightTile = H4(),
    underRightTile = H3(),
    bottomTile = G3(),
    underLeftTile = F4(),
    upperLeftTile = F5()
)