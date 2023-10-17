package com.example.hexagonalchess.data_layer.model.tile.F

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId

class F1: com.example.hexagonalchess.data_layer.model.tile.Tile(
    id = TileId.F1,
    color = TileColor.DARK,
    topTile = com.example.hexagonalchess.data_layer.model.tile.F.F2(),
    upperRightTile = com.example.hexagonalchess.data_layer.model.tile.G.G1(),
    underRightTile = null,
    bottomTile = null,
    underLeftTile = com.example.hexagonalchess.data_layer.model.tile.E.E1(),
    upperLeftTile = com.example.hexagonalchess.data_layer.model.tile.E.E2()
) {
}