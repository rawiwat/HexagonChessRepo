package com.example.hexagonalchess.data_layer.model.tile.C

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.data_layer.model.tile.B.B8
import com.example.hexagonalchess.data_layer.model.tile.B.B9
import com.example.hexagonalchess.data_layer.model.tile.D.D10
import com.example.hexagonalchess.data_layer.model.tile.D.D9
import com.example.hexagonalchess.data_layer.model.tile.Tile

class C9: com.example.hexagonalchess.data_layer.model.tile.Tile(
    id = TileId.C9,
    color = TileColor.LIGHT,
    topTile = com.example.hexagonalchess.data_layer.model.tile.C.C10(),
    upperRightTile = com.example.hexagonalchess.data_layer.model.tile.D.D10(),
    underRightTile = com.example.hexagonalchess.data_layer.model.tile.D.D9(),
    bottomTile = com.example.hexagonalchess.data_layer.model.tile.C.C8(),
    underLeftTile = com.example.hexagonalchess.data_layer.model.tile.B.B8(),
    upperLeftTile = com.example.hexagonalchess.data_layer.model.tile.B.B9()
) {
}