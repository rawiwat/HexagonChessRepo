package com.example.hexagonalchess.data_layer.model.tile.F

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId

class F11: com.example.hexagonalchess.data_layer.model.tile.Tile(
    id = TileId.F11,
    color = TileColor.LIGHT,
    topTile = null,
    upperRightTile = null,
    underRightTile = com.example.hexagonalchess.data_layer.model.tile.G.G10(),
    bottomTile = com.example.hexagonalchess.data_layer.model.tile.F.F10(),
    underLeftTile = com.example.hexagonalchess.data_layer.model.tile.E.E11(),
    upperLeftTile = com.example.hexagonalchess.data_layer.model.tile.E.E12()
)