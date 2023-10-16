package com.example.hexagonalchess.data_layer.model.tile.D

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.data_layer.model.tile.C.C4
import com.example.hexagonalchess.data_layer.model.tile.C.C5
import com.example.hexagonalchess.data_layer.model.tile.E.E5
import com.example.hexagonalchess.data_layer.model.tile.E.E6
import com.example.hexagonalchess.data_layer.model.tile.Tile

class D5: com.example.hexagonalchess.data_layer.model.tile.Tile(
    id = TileId.D5,
    color = TileColor.LIGHT,
    topTile = com.example.hexagonalchess.data_layer.model.tile.D.D6(),
    upperRightTile = com.example.hexagonalchess.data_layer.model.tile.E.E6(),
    underRightTile = com.example.hexagonalchess.data_layer.model.tile.E.E5(),
    bottomTile = com.example.hexagonalchess.data_layer.model.tile.D.D4(),
    underLeftTile = com.example.hexagonalchess.data_layer.model.tile.C.C4(),
    upperLeftTile = com.example.hexagonalchess.data_layer.model.tile.C.C5()
) {
}