package com.example.hexagonalchess.data_layer.model.tile.F

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId

class F9: com.example.hexagonalchess.data_layer.model.tile.Tile(
    id = TileId.F9,
    color = TileColor.MID,
    topTile = com.example.hexagonalchess.data_layer.model.tile.F.F10(),
    upperRightTile = com.example.hexagonalchess.data_layer.model.tile.G.G9(),
    underRightTile = com.example.hexagonalchess.data_layer.model.tile.G.G8(),
    bottomTile = com.example.hexagonalchess.data_layer.model.tile.F.F8(),
    underLeftTile = com.example.hexagonalchess.data_layer.model.tile.E.E9(),
    upperLeftTile = com.example.hexagonalchess.data_layer.model.tile.E.E10()
)
