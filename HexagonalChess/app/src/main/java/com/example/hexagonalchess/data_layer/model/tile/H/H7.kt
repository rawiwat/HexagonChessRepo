package com.example.hexagonalchess.data_layer.model.tile.H

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.data_layer.model.tile.G.G7
import com.example.hexagonalchess.data_layer.model.tile.G.G8
import com.example.hexagonalchess.data_layer.model.tile.I.I6
import com.example.hexagonalchess.data_layer.model.tile.I.I7
import com.example.hexagonalchess.data_layer.model.tile.Tile

class H7: com.example.hexagonalchess.data_layer.model.tile.Tile(
    id = TileId.H7,
    color = TileColor.LIGHT,
    topTile = com.example.hexagonalchess.data_layer.model.tile.H.H8(),
    upperRightTile = com.example.hexagonalchess.data_layer.model.tile.I.I7(),
    underRightTile = com.example.hexagonalchess.data_layer.model.tile.I.I6(),
    bottomTile = com.example.hexagonalchess.data_layer.model.tile.H.H6(),
    underLeftTile = com.example.hexagonalchess.data_layer.model.tile.G.G7(),
    upperLeftTile = com.example.hexagonalchess.data_layer.model.tile.G.G8()
) {
}