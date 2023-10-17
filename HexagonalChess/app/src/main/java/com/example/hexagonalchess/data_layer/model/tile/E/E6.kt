package com.example.hexagonalchess.data_layer.model.tile.E

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId

class E6: com.example.hexagonalchess.data_layer.model.tile.Tile(
    id = TileId.E6,
    color = TileColor.DARK,
    topTile = com.example.hexagonalchess.data_layer.model.tile.E.E7(),
    upperRightTile = com.example.hexagonalchess.data_layer.model.tile.F.F6(),
    underRightTile = com.example.hexagonalchess.data_layer.model.tile.F.F5(),
    bottomTile = com.example.hexagonalchess.data_layer.model.tile.E.E5(),
    underLeftTile = com.example.hexagonalchess.data_layer.model.tile.D.D5(),
    upperLeftTile = com.example.hexagonalchess.data_layer.model.tile.D.D6()
) {
}