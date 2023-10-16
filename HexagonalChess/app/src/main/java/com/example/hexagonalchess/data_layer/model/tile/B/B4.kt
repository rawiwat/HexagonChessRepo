package com.example.hexagonalchess.data_layer.model.tile.B

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.data_layer.model.tile.A.A3
import com.example.hexagonalchess.data_layer.model.tile.A.A4
import com.example.hexagonalchess.data_layer.model.tile.C.C4
import com.example.hexagonalchess.data_layer.model.tile.C.C5
import com.example.hexagonalchess.data_layer.model.tile.Tile

class B4: com.example.hexagonalchess.data_layer.model.tile.Tile(
    id = TileId.B4,
    color = TileColor.LIGHT,
    topTile = com.example.hexagonalchess.data_layer.model.tile.B.B5(),
    upperRightTile = com.example.hexagonalchess.data_layer.model.tile.C.C5(),
    underRightTile = com.example.hexagonalchess.data_layer.model.tile.C.C4(),
    bottomTile = com.example.hexagonalchess.data_layer.model.tile.B.B3(),
    underLeftTile = com.example.hexagonalchess.data_layer.model.tile.A.A3(),
    upperLeftTile = com.example.hexagonalchess.data_layer.model.tile.A.A4()
) {
}