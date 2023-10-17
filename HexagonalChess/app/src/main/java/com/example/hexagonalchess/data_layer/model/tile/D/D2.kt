package com.example.hexagonalchess.data_layer.model.tile.D

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.data_layer.model.tile.C.C1
import com.example.hexagonalchess.data_layer.model.tile.C.C2

class D2: com.example.hexagonalchess.data_layer.model.tile.Tile(
    id = TileId.D2,
    color = TileColor.LIGHT,
    topTile = com.example.hexagonalchess.data_layer.model.tile.D.D3(),
    upperRightTile = com.example.hexagonalchess.data_layer.model.tile.E.E3(),
    underRightTile = com.example.hexagonalchess.data_layer.model.tile.E.E2(),
    bottomTile = com.example.hexagonalchess.data_layer.model.tile.D.D1(),
    underLeftTile = com.example.hexagonalchess.data_layer.model.tile.C.C1(),
    upperLeftTile = com.example.hexagonalchess.data_layer.model.tile.C.C2()
) {
}