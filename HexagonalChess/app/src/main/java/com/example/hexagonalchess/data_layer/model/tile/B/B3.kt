package com.example.hexagonalchess.data_layer.model.tile.B

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.data_layer.model.tile.A.A2
import com.example.hexagonalchess.data_layer.model.tile.A.A3
import com.example.hexagonalchess.data_layer.model.tile.C.C3
import com.example.hexagonalchess.data_layer.model.tile.C.C4
import com.example.hexagonalchess.data_layer.model.tile.Tile

class B3: com.example.hexagonalchess.data_layer.model.tile.Tile(
    id = TileId.B3,
    color = TileColor.DARK,
    topTile = com.example.hexagonalchess.data_layer.model.tile.B.B4(),
    upperRightTile = com.example.hexagonalchess.data_layer.model.tile.C.C4(),
    underRightTile = com.example.hexagonalchess.data_layer.model.tile.C.C3(),
    bottomTile = com.example.hexagonalchess.data_layer.model.tile.B.B2(),
    underLeftTile = com.example.hexagonalchess.data_layer.model.tile.A.A2(),
    upperLeftTile = com.example.hexagonalchess.data_layer.model.tile.A.A3()
) {
}