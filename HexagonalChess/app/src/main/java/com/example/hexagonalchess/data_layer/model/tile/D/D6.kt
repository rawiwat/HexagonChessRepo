package com.example.hexagonalchess.data_layer.model.tile.D

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.data_layer.model.tile.C.C5
import com.example.hexagonalchess.data_layer.model.tile.C.C6

class D6: com.example.hexagonalchess.data_layer.model.tile.Tile(
    id = TileId.D6,
    color = TileColor.MID,
    topTile = com.example.hexagonalchess.data_layer.model.tile.D.D7(),
    upperRightTile = com.example.hexagonalchess.data_layer.model.tile.E.E7(),
    underRightTile = com.example.hexagonalchess.data_layer.model.tile.E.E6(),
    bottomTile = com.example.hexagonalchess.data_layer.model.tile.D.D5(),
    underLeftTile = com.example.hexagonalchess.data_layer.model.tile.C.C5(),
    upperLeftTile = com.example.hexagonalchess.data_layer.model.tile.C.C6()
) {
}