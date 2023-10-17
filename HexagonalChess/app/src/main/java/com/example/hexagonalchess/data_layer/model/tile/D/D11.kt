package com.example.hexagonalchess.data_layer.model.tile.D

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.data_layer.model.tile.C.C10

class D11: com.example.hexagonalchess.data_layer.model.tile.Tile(
    id = TileId.D11,
    color = TileColor.LIGHT,
    topTile = null,
    upperRightTile = com.example.hexagonalchess.data_layer.model.tile.E.E12(),
    underRightTile = com.example.hexagonalchess.data_layer.model.tile.E.E11(),
    bottomTile = com.example.hexagonalchess.data_layer.model.tile.D.D10(),
    underLeftTile = com.example.hexagonalchess.data_layer.model.tile.C.C10(),
    upperLeftTile = null
) {
}