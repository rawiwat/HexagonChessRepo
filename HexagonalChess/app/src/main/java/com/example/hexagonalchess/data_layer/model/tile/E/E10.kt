package com.example.hexagonalchess.data_layer.model.tile.E

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId

class E10: com.example.hexagonalchess.data_layer.model.tile.Tile(
    id = TileId.E10,
    color = TileColor.LIGHT,
    topTile = com.example.hexagonalchess.data_layer.model.tile.E.E11(),
    upperRightTile = com.example.hexagonalchess.data_layer.model.tile.F.F10(),
    underRightTile = com.example.hexagonalchess.data_layer.model.tile.F.F9(),
    bottomTile = com.example.hexagonalchess.data_layer.model.tile.E.E9(),
    underLeftTile = com.example.hexagonalchess.data_layer.model.tile.D.D9(),
    upperLeftTile = com.example.hexagonalchess.data_layer.model.tile.D.D10()
) {
}