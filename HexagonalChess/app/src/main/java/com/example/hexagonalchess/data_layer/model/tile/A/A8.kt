package com.example.hexagonalchess.data_layer.model.tile.A

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.data_layer.model.tile.B.B8
import com.example.hexagonalchess.data_layer.model.tile.B.B9
import com.example.hexagonalchess.data_layer.model.tile.Tile

class A8: com.example.hexagonalchess.data_layer.model.tile.Tile(
    id = TileId.A8,
    color = TileColor.LIGHT,
    topTile = null,
    upperRightTile = com.example.hexagonalchess.data_layer.model.tile.B.B9(),
    underRightTile = com.example.hexagonalchess.data_layer.model.tile.B.B8(),
    bottomTile = com.example.hexagonalchess.data_layer.model.tile.A.A7(),
    underLeftTile = null,
    upperLeftTile = null
) {
}