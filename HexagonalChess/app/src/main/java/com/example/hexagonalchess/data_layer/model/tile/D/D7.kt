package com.example.hexagonalchess.data_layer.model.tile.D

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.data_layer.model.tile.C.C6
import com.example.hexagonalchess.data_layer.model.tile.C.C7
import com.example.hexagonalchess.data_layer.model.tile.E.E7
import com.example.hexagonalchess.data_layer.model.tile.E.E8
import com.example.hexagonalchess.data_layer.model.tile.Tile

class D7: com.example.hexagonalchess.data_layer.model.tile.Tile(
    id = TileId.D7,
    color = TileColor.DARK,
    topTile = com.example.hexagonalchess.data_layer.model.tile.D.D8(),
    upperRightTile = com.example.hexagonalchess.data_layer.model.tile.E.E8(),
    underRightTile = com.example.hexagonalchess.data_layer.model.tile.E.E7(),
    bottomTile = com.example.hexagonalchess.data_layer.model.tile.D.D6(),
    underLeftTile = com.example.hexagonalchess.data_layer.model.tile.C.C6(),
    upperLeftTile = com.example.hexagonalchess.data_layer.model.tile.C.C7()
) {
}