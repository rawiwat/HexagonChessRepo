package com.example.hexagonalchess.data_layer.model.tile.D

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.data_layer.model.tile.C.C3
import com.example.hexagonalchess.data_layer.model.tile.C.C4

class D4: com.example.hexagonalchess.data_layer.model.tile.Tile(
    id = TileId.D4,
    color = TileColor.DARK,
    topTile = com.example.hexagonalchess.data_layer.model.tile.D.D5(),
    upperRightTile = com.example.hexagonalchess.data_layer.model.tile.E.E5(),
    underRightTile = com.example.hexagonalchess.data_layer.model.tile.E.E4(),
    bottomTile = com.example.hexagonalchess.data_layer.model.tile.D.D3(),
    underLeftTile = com.example.hexagonalchess.data_layer.model.tile.C.C3(),
    upperLeftTile = com.example.hexagonalchess.data_layer.model.tile.C.C4()
) {
}