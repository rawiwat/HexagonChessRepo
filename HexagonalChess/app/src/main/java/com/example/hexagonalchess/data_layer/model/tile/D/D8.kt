package com.example.hexagonalchess.data_layer.model.tile.D

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.data_layer.model.tile.C.C7
import com.example.hexagonalchess.data_layer.model.tile.C.C8
import com.example.hexagonalchess.data_layer.model.tile.E.E8
import com.example.hexagonalchess.data_layer.model.tile.E.E9
import com.example.hexagonalchess.data_layer.model.tile.Tile

class D8: com.example.hexagonalchess.data_layer.model.tile.Tile(
    id = TileId.D8,
    color = TileColor.LIGHT,
    topTile = com.example.hexagonalchess.data_layer.model.tile.D.D9(),
    upperRightTile = com.example.hexagonalchess.data_layer.model.tile.E.E9(),
    underRightTile = com.example.hexagonalchess.data_layer.model.tile.E.E8(),
    bottomTile = com.example.hexagonalchess.data_layer.model.tile.D.D7(),
    underLeftTile = com.example.hexagonalchess.data_layer.model.tile.C.C7(),
    upperLeftTile = com.example.hexagonalchess.data_layer.model.tile.C.C8()
) {
}