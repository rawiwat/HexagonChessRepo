package com.example.hexagonalchess.data_layer.model.tile.H

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId

class H5: com.example.hexagonalchess.data_layer.model.tile.Tile(
    id = TileId.H5,
    color = TileColor.MID,
    topTile = com.example.hexagonalchess.data_layer.model.tile.H.H6(),
    upperRightTile = com.example.hexagonalchess.data_layer.model.tile.I.I5(),
    underRightTile = com.example.hexagonalchess.data_layer.model.tile.I.I4(),
    bottomTile = com.example.hexagonalchess.data_layer.model.tile.H.H4(),
    underLeftTile = com.example.hexagonalchess.data_layer.model.tile.G.G5(),
    upperLeftTile = com.example.hexagonalchess.data_layer.model.tile.G.G6()
) {
}