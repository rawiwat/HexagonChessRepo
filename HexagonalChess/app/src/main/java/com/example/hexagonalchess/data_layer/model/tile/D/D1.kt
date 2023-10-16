package com.example.hexagonalchess.data_layer.model.tile.D

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.data_layer.model.tile.C.C1
import com.example.hexagonalchess.data_layer.model.tile.E.E1
import com.example.hexagonalchess.data_layer.model.tile.E.E2
import com.example.hexagonalchess.data_layer.model.tile.Tile

class D1: com.example.hexagonalchess.data_layer.model.tile.Tile(
    id = TileId.D1,
    color = TileColor.DARK,
    topTile = com.example.hexagonalchess.data_layer.model.tile.D.D2(),
    upperRightTile = com.example.hexagonalchess.data_layer.model.tile.E.E2(),
    underRightTile = com.example.hexagonalchess.data_layer.model.tile.E.E1(),
    bottomTile = null,
    underLeftTile = null,
    upperLeftTile = com.example.hexagonalchess.data_layer.model.tile.C.C1()
) {
}