package com.example.hexagonalchess.data_layer.model.tile.E

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.data_layer.model.tile.D.D8
import com.example.hexagonalchess.data_layer.model.tile.D.D9
import com.example.hexagonalchess.data_layer.model.tile.F.F8
import com.example.hexagonalchess.data_layer.model.tile.F.F9
import com.example.hexagonalchess.data_layer.model.tile.Tile

class E9: com.example.hexagonalchess.data_layer.model.tile.Tile(
    id = TileId.E9,
    color = TileColor.DARK,
    topTile = com.example.hexagonalchess.data_layer.model.tile.E.E10(),
    upperRightTile = com.example.hexagonalchess.data_layer.model.tile.F.F9(),
    underRightTile = com.example.hexagonalchess.data_layer.model.tile.F.F8(),
    bottomTile = com.example.hexagonalchess.data_layer.model.tile.E.E8(),
    underLeftTile = com.example.hexagonalchess.data_layer.model.tile.D.D8(),
    upperLeftTile = com.example.hexagonalchess.data_layer.model.tile.D.D9()
) {
}