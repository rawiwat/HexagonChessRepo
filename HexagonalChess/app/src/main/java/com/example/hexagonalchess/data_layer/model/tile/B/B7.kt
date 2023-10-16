package com.example.hexagonalchess.data_layer.model.tile.B

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.data_layer.model.tile.A.A6
import com.example.hexagonalchess.data_layer.model.tile.Tile

class B7: com.example.hexagonalchess.data_layer.model.tile.Tile(
    id = TileId.B7,
    color = TileColor.MID,
    topTile = null,
    upperRightTile = null,
    underRightTile = null,
    bottomTile = com.example.hexagonalchess.data_layer.model.tile.B.B6(),
    underLeftTile = com.example.hexagonalchess.data_layer.model.tile.A.A6(),
    upperLeftTile = null
) {
}