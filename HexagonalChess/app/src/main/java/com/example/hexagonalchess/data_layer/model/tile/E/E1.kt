package com.example.hexagonalchess.data_layer.model.tile.E

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.data_layer.model.tile.D.D1
import com.example.hexagonalchess.data_layer.model.tile.F.F1
import com.example.hexagonalchess.data_layer.model.tile.Tile

class E1: com.example.hexagonalchess.data_layer.model.tile.Tile(
    id = TileId.E1,
    color = TileColor.LIGHT,
    topTile = com.example.hexagonalchess.data_layer.model.tile.E.E2(),
    upperRightTile = com.example.hexagonalchess.data_layer.model.tile.F.F1(),
    underRightTile = null,
    bottomTile = null,
    underLeftTile = com.example.hexagonalchess.data_layer.model.tile.D.D1(),
    upperLeftTile = null
) {
}