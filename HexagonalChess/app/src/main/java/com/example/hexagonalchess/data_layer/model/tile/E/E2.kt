package com.example.hexagonalchess.data_layer.model.tile.E

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.data_layer.model.tile.D.D1
import com.example.hexagonalchess.data_layer.model.tile.D.D2
import com.example.hexagonalchess.data_layer.model.tile.F.F1
import com.example.hexagonalchess.data_layer.model.tile.F.F2
import com.example.hexagonalchess.data_layer.model.tile.Tile

class E2: com.example.hexagonalchess.data_layer.model.tile.Tile(
    id = TileId.E2,
    color = TileColor.MID,
    topTile = com.example.hexagonalchess.data_layer.model.tile.E.E3(),
    upperRightTile = com.example.hexagonalchess.data_layer.model.tile.F.F2(),
    underRightTile = com.example.hexagonalchess.data_layer.model.tile.F.F1(),
    bottomTile = com.example.hexagonalchess.data_layer.model.tile.E.E1(),
    underLeftTile = com.example.hexagonalchess.data_layer.model.tile.D.D1(),
    upperLeftTile = com.example.hexagonalchess.data_layer.model.tile.D.D2()
) {
}