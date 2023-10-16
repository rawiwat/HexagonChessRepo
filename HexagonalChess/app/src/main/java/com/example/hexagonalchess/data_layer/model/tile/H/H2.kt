package com.example.hexagonalchess.data_layer.model.tile.H

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.data_layer.model.tile.G.G2
import com.example.hexagonalchess.data_layer.model.tile.G.G3
import com.example.hexagonalchess.data_layer.model.tile.I.I1
import com.example.hexagonalchess.data_layer.model.tile.I.I2
import com.example.hexagonalchess.data_layer.model.tile.Tile

class H2: com.example.hexagonalchess.data_layer.model.tile.Tile(
    id = TileId.H2,
    color = TileColor.MID,
    topTile = com.example.hexagonalchess.data_layer.model.tile.H.H3(),
    upperRightTile = com.example.hexagonalchess.data_layer.model.tile.I.I2(),
    underRightTile = com.example.hexagonalchess.data_layer.model.tile.I.I1(),
    bottomTile = com.example.hexagonalchess.data_layer.model.tile.H.H1(),
    underLeftTile = com.example.hexagonalchess.data_layer.model.tile.G.G2(),
    upperLeftTile = com.example.hexagonalchess.data_layer.model.tile.G.G3()
) {
}