package com.example.hexagonalchess.data_layer.model.tile.H

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId

class H9: com.example.hexagonalchess.data_layer.model.tile.Tile(
    id = TileId.H9,
    color = TileColor.DARK,
    topTile = null,
    upperRightTile = null,
    underRightTile = com.example.hexagonalchess.data_layer.model.tile.I.I8(),
    bottomTile = com.example.hexagonalchess.data_layer.model.tile.G.G8(),
    underLeftTile = com.example.hexagonalchess.data_layer.model.tile.G.G9(),
    upperLeftTile = com.example.hexagonalchess.data_layer.model.tile.G.G10()
) {
}