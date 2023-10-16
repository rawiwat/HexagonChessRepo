package com.example.hexagonalchess.data_layer.model.tile.B

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.data_layer.model.tile.A.A1
import com.example.hexagonalchess.data_layer.model.tile.C.C1
import com.example.hexagonalchess.data_layer.model.tile.C.C2
import com.example.hexagonalchess.data_layer.model.tile.Tile

class B1: com.example.hexagonalchess.data_layer.model.tile.Tile(
    id = TileId.B1,
    color = TileColor.LIGHT,
    topTile = com.example.hexagonalchess.data_layer.model.tile.B.B2(),
    upperRightTile = com.example.hexagonalchess.data_layer.model.tile.C.C2(),
    underRightTile = com.example.hexagonalchess.data_layer.model.tile.C.C1(),
    bottomTile = null,
    underLeftTile = null,
    upperLeftTile = com.example.hexagonalchess.data_layer.model.tile.A.A1()
)