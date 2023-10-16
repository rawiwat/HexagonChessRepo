package com.example.hexagonalchess.data_layer.model.tile.D

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.data_layer.model.tile.C.C2
import com.example.hexagonalchess.data_layer.model.tile.C.C3
import com.example.hexagonalchess.data_layer.model.tile.E.E3
import com.example.hexagonalchess.data_layer.model.tile.E.E4
import com.example.hexagonalchess.data_layer.model.tile.Tile

class D3: com.example.hexagonalchess.data_layer.model.tile.Tile(
    id = TileId.D3,
    color = TileColor.MID,
    topTile = com.example.hexagonalchess.data_layer.model.tile.D.D4(),
    upperRightTile = com.example.hexagonalchess.data_layer.model.tile.E.E4(),
    underRightTile = com.example.hexagonalchess.data_layer.model.tile.E.E3(),
    bottomTile = com.example.hexagonalchess.data_layer.model.tile.D.D2(),
    underLeftTile = com.example.hexagonalchess.data_layer.model.tile.C.C2(),
    upperLeftTile = com.example.hexagonalchess.data_layer.model.tile.C.C3()
) {
}