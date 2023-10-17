package com.example.hexagonalchess.data_layer.model.tile.E

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId

class E7: com.example.hexagonalchess.data_layer.model.tile.Tile(
    id = TileId.E7,
    color = TileColor.LIGHT,
    topTile = com.example.hexagonalchess.data_layer.model.tile.E.E8(),
    upperRightTile = com.example.hexagonalchess.data_layer.model.tile.F.F7(),
    underRightTile = com.example.hexagonalchess.data_layer.model.tile.F.F6(),
    bottomTile = com.example.hexagonalchess.data_layer.model.tile.E.E6(),
    underLeftTile = com.example.hexagonalchess.data_layer.model.tile.D.D6(),
    upperLeftTile = com.example.hexagonalchess.data_layer.model.tile.D.D7()
) {
}