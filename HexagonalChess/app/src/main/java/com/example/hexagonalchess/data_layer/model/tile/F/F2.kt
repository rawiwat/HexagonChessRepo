package com.example.hexagonalchess.data_layer.model.tile.F

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.data_layer.model.tile.E.E2
import com.example.hexagonalchess.data_layer.model.tile.E.E3
import com.example.hexagonalchess.data_layer.model.tile.G.G1
import com.example.hexagonalchess.data_layer.model.tile.G.G2
import com.example.hexagonalchess.data_layer.model.tile.Tile

class F2: com.example.hexagonalchess.data_layer.model.tile.Tile(
    id = TileId.F2,
    color = TileColor.LIGHT,
    topTile = com.example.hexagonalchess.data_layer.model.tile.F.F3(),
    upperRightTile = com.example.hexagonalchess.data_layer.model.tile.G.G2(),
    underRightTile = com.example.hexagonalchess.data_layer.model.tile.G.G1(),
    bottomTile = com.example.hexagonalchess.data_layer.model.tile.F.F1(),
    underLeftTile = com.example.hexagonalchess.data_layer.model.tile.E.E2(),
    upperLeftTile = com.example.hexagonalchess.data_layer.model.tile.E.E3()
) {
}