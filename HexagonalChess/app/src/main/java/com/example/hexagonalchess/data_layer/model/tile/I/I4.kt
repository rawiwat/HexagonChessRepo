package com.example.hexagonalchess.data_layer.model.tile.I

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.data_layer.model.tile.H.H4
import com.example.hexagonalchess.data_layer.model.tile.H.H5
import com.example.hexagonalchess.data_layer.model.tile.Tile

class I4: com.example.hexagonalchess.data_layer.model.tile.Tile(
    id = TileId.I4,
    color = TileColor.DARK,
    topTile = com.example.hexagonalchess.data_layer.model.tile.I.I5(),
    upperRightTile = null,
    underRightTile = null,
    bottomTile = com.example.hexagonalchess.data_layer.model.tile.I.I3(),
    underLeftTile = com.example.hexagonalchess.data_layer.model.tile.H.H4(),
    upperLeftTile = com.example.hexagonalchess.data_layer.model.tile.H.H5()
) {
}