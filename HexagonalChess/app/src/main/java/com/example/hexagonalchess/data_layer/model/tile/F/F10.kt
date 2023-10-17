package com.example.hexagonalchess.data_layer.model.tile.F

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId

class F10: com.example.hexagonalchess.data_layer.model.tile.Tile(
    id = TileId.F10,
    color = TileColor.DARK,
    topTile = com.example.hexagonalchess.data_layer.model.tile.F.F11(),
    upperRightTile = com.example.hexagonalchess.data_layer.model.tile.G.G10(),
    underRightTile = com.example.hexagonalchess.data_layer.model.tile.G.G9(),
    bottomTile = com.example.hexagonalchess.data_layer.model.tile.F.F9(),
    underLeftTile = com.example.hexagonalchess.data_layer.model.tile.E.E10(),
    upperLeftTile = com.example.hexagonalchess.data_layer.model.tile.E.E11()
) {
}