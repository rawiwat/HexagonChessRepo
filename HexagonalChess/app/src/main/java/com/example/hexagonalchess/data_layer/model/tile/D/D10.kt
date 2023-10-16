package com.example.hexagonalchess.data_layer.model.tile.D

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.data_layer.model.tile.C.C10
import com.example.hexagonalchess.data_layer.model.tile.C.C9
import com.example.hexagonalchess.data_layer.model.tile.E.E10
import com.example.hexagonalchess.data_layer.model.tile.E.E11
import com.example.hexagonalchess.data_layer.model.tile.Tile

class D10: com.example.hexagonalchess.data_layer.model.tile.Tile(
    id = TileId.D10,
    color = TileColor.DARK,
    topTile = com.example.hexagonalchess.data_layer.model.tile.D.D11(),
    upperRightTile = com.example.hexagonalchess.data_layer.model.tile.E.E11(),
    underRightTile = com.example.hexagonalchess.data_layer.model.tile.E.E10(),
    bottomTile = com.example.hexagonalchess.data_layer.model.tile.D.D9(),
    underLeftTile = com.example.hexagonalchess.data_layer.model.tile.C.C9(),
    upperLeftTile = com.example.hexagonalchess.data_layer.model.tile.C.C10()
) {
}