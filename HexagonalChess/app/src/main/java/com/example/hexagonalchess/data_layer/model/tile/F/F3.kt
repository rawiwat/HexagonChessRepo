package com.example.hexagonalchess.data_layer.model.tile.F

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId

class F3: com.example.hexagonalchess.data_layer.model.tile.Tile(
    id = TileId.E3,
    color = TileColor.MID,
    topTile = com.example.hexagonalchess.data_layer.model.tile.F.F4(),
    upperRightTile = com.example.hexagonalchess.data_layer.model.tile.G.G3(),
    underRightTile = com.example.hexagonalchess.data_layer.model.tile.G.G2(),
    bottomTile = com.example.hexagonalchess.data_layer.model.tile.F.F2(),
    underLeftTile = com.example.hexagonalchess.data_layer.model.tile.E.E3(),
    upperLeftTile = com.example.hexagonalchess.data_layer.model.tile.E.E4()
) {
}