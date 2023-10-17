package com.example.hexagonalchess.data_layer.model.tile.E

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId

class E11: com.example.hexagonalchess.data_layer.model.tile.Tile(
    id = TileId.E11,
    color = TileColor.MID,
    topTile = com.example.hexagonalchess.data_layer.model.tile.E.E12(),
    upperRightTile = com.example.hexagonalchess.data_layer.model.tile.F.F11(),
    underRightTile = com.example.hexagonalchess.data_layer.model.tile.F.F10(),
    bottomTile = com.example.hexagonalchess.data_layer.model.tile.E.E10(),
    underLeftTile = com.example.hexagonalchess.data_layer.model.tile.D.D10(),
    upperLeftTile = com.example.hexagonalchess.data_layer.model.tile.D.D11()
)