package com.example.hexagonalchess.data_layer.model.tile.C

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.data_layer.model.tile.B.B1
import com.example.hexagonalchess.data_layer.model.tile.D.D1
import com.example.hexagonalchess.data_layer.model.tile.D.D2
import com.example.hexagonalchess.data_layer.model.tile.Tile

class C1: com.example.hexagonalchess.data_layer.model.tile.Tile(
    id = TileId.C1,
    color = TileColor.MID,
    topTile = com.example.hexagonalchess.data_layer.model.tile.C.C2(),
    upperRightTile = com.example.hexagonalchess.data_layer.model.tile.D.D2(),
    underRightTile = com.example.hexagonalchess.data_layer.model.tile.D.D1(),
    bottomTile = null,
    underLeftTile = null,
    upperLeftTile = com.example.hexagonalchess.data_layer.model.tile.B.B1()
) {
}