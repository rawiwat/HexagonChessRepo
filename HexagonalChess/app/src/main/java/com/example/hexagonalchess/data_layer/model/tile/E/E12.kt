package com.example.hexagonalchess.data_layer.model.tile.E

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.data_layer.model.tile.D.D11
import com.example.hexagonalchess.data_layer.model.tile.F.F11
import com.example.hexagonalchess.data_layer.model.tile.Tile

class E12: com.example.hexagonalchess.data_layer.model.tile.Tile(
    id = TileId.E12,
    color = TileColor.DARK,
    topTile = null,
    upperRightTile = com.example.hexagonalchess.data_layer.model.tile.F.F11(),
    underRightTile = null,
    bottomTile = com.example.hexagonalchess.data_layer.model.tile.E.E11(),
    underLeftTile = com.example.hexagonalchess.data_layer.model.tile.D.D11(),
    upperLeftTile = null
) {
}