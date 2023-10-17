package com.example.hexagonalchess.data_layer.model.tile.H

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId

class H2: com.example.hexagonalchess.data_layer.model.tile.Tile(
    id = TileId.H2,
    color = TileColor.MID,
    topTile = TileId.H3,
    upperRightTile = TileId.I2,
    underRightTile = TileId.I1,
    bottomTile = TileId.H1,
    underLeftTile = TileId.G2,
    upperLeftTile = TileId.G3
)