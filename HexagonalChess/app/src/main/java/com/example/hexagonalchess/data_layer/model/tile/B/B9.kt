package com.example.hexagonalchess.data_layer.model.tile.B

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.data_layer.model.tile.A.A8
import com.example.hexagonalchess.data_layer.model.tile.C.C10
import com.example.hexagonalchess.data_layer.model.tile.C.C9
import com.example.hexagonalchess.data_layer.model.tile.Tile

class B9: com.example.hexagonalchess.data_layer.model.tile.Tile(
    id = TileId.B9,
    color = TileColor.DARK,
    topTile = null,
    upperRightTile = com.example.hexagonalchess.data_layer.model.tile.C.C10(),
    underRightTile = com.example.hexagonalchess.data_layer.model.tile.C.C9(),
    bottomTile = com.example.hexagonalchess.data_layer.model.tile.B.B8(),
    underLeftTile = com.example.hexagonalchess.data_layer.model.tile.A.A8(),
    upperLeftTile = null
) {
}