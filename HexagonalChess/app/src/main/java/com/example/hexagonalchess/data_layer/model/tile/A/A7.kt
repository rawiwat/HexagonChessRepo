package com.example.hexagonalchess.data_layer.model.tile.A

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.data_layer.model.tile.B.B7
import com.example.hexagonalchess.data_layer.model.tile.B.B8
import com.example.hexagonalchess.data_layer.model.tile.Tile

class A7: com.example.hexagonalchess.data_layer.model.tile.Tile(
    id = TileId.A7,
    color = TileColor.DARK,
    topTile = com.example.hexagonalchess.data_layer.model.tile.A.A8(),
    upperRightTile = com.example.hexagonalchess.data_layer.model.tile.B.B8(),
    underRightTile = com.example.hexagonalchess.data_layer.model.tile.B.B7(),
    bottomTile = com.example.hexagonalchess.data_layer.model.tile.A.A6(),
    underLeftTile = null,
    upperLeftTile = null
) {
}