package com.example.hexagonalchess.data_layer.model.tile.H

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.data_layer.model.tile.G.G5
import com.example.hexagonalchess.data_layer.model.tile.G.G6
import com.example.hexagonalchess.data_layer.model.tile.G.G7
import com.example.hexagonalchess.data_layer.model.tile.Tile

class H6: com.example.hexagonalchess.data_layer.model.tile.Tile(
    id = TileId.H6,
    color = TileColor.DARK,
    topTile = com.example.hexagonalchess.data_layer.model.tile.H.H7(),
    upperRightTile = com.example.hexagonalchess.data_layer.model.tile.G.G6(),
    underRightTile = com.example.hexagonalchess.data_layer.model.tile.G.G5(),
    bottomTile = com.example.hexagonalchess.data_layer.model.tile.H.H5(),
    underLeftTile = com.example.hexagonalchess.data_layer.model.tile.G.G6(),
    upperLeftTile = com.example.hexagonalchess.data_layer.model.tile.G.G7()
) {
}