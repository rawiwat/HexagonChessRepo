package com.example.hexagonalchess.data_layer.model.tile.H

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.data_layer.model.tile.G.G8
import com.example.hexagonalchess.data_layer.model.tile.G.G9
import com.example.hexagonalchess.data_layer.model.tile.I.I7
import com.example.hexagonalchess.data_layer.model.tile.I.I8
import com.example.hexagonalchess.data_layer.model.tile.Tile

class H8: com.example.hexagonalchess.data_layer.model.tile.Tile(
    id = TileId.H8,
    color = TileColor.MID,
    topTile = com.example.hexagonalchess.data_layer.model.tile.H.H9(),
    upperRightTile = com.example.hexagonalchess.data_layer.model.tile.I.I8(),
    underRightTile = com.example.hexagonalchess.data_layer.model.tile.I.I7(),
    bottomTile = com.example.hexagonalchess.data_layer.model.tile.H.H7(),
    underLeftTile = com.example.hexagonalchess.data_layer.model.tile.G.G8(),
    upperLeftTile = com.example.hexagonalchess.data_layer.model.tile.G.G9()
) {
}