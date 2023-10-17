package com.example.hexagonalchess.data_layer.model.tile.E

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId

class E4: com.example.hexagonalchess.data_layer.model.tile.Tile(
    id = TileId.E4,
    color = TileColor.LIGHT,
    topTile = com.example.hexagonalchess.data_layer.model.tile.E.E5(),
    upperRightTile = com.example.hexagonalchess.data_layer.model.tile.F.F4(),
    underRightTile = com.example.hexagonalchess.data_layer.model.tile.F.F3(),
    bottomTile = com.example.hexagonalchess.data_layer.model.tile.E.E3(),
    underLeftTile = com.example.hexagonalchess.data_layer.model.tile.D.D3(),
    upperLeftTile = com.example.hexagonalchess.data_layer.model.tile.D.D4()
) {
}