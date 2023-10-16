package com.example.hexagonalchess.data_layer.model.tile.B

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.data_layer.model.tile.A.A7
import com.example.hexagonalchess.data_layer.model.tile.A.A8
import com.example.hexagonalchess.data_layer.model.tile.C.C8
import com.example.hexagonalchess.data_layer.model.tile.C.C9
import com.example.hexagonalchess.data_layer.model.tile.Tile

class B8: com.example.hexagonalchess.data_layer.model.tile.Tile(
    id = TileId.B8,
    color = TileColor.MID,
    topTile = com.example.hexagonalchess.data_layer.model.tile.B.B9(),
    upperRightTile = com.example.hexagonalchess.data_layer.model.tile.C.C9(),
    underRightTile = com.example.hexagonalchess.data_layer.model.tile.C.C8(),
    bottomTile = com.example.hexagonalchess.data_layer.model.tile.B.B7(),
    underLeftTile = com.example.hexagonalchess.data_layer.model.tile.A.A7(),
    upperLeftTile = com.example.hexagonalchess.data_layer.model.tile.A.A8()
) {
}