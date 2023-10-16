package com.example.hexagonalchess.data_layer.model.tile.H

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.data_layer.model.tile.G.G1
import com.example.hexagonalchess.data_layer.model.tile.G.G2
import com.example.hexagonalchess.data_layer.model.tile.I.I1
import com.example.hexagonalchess.data_layer.model.tile.Tile

class H1: com.example.hexagonalchess.data_layer.model.tile.Tile(
    id = TileId.H1,
    color = TileColor.LIGHT,
    topTile = com.example.hexagonalchess.data_layer.model.tile.H.H2(),
    upperRightTile = com.example.hexagonalchess.data_layer.model.tile.I.I1(),
    underRightTile = null,
    bottomTile = null,
    underLeftTile = com.example.hexagonalchess.data_layer.model.tile.G.G1(),
    upperLeftTile = com.example.hexagonalchess.data_layer.model.tile.G.G2()
) {
}