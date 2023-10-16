package com.example.hexagonalchess.data_layer.model.tile.F

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.data_layer.model.tile.E.E8
import com.example.hexagonalchess.data_layer.model.tile.E.E9
import com.example.hexagonalchess.data_layer.model.tile.G.G7
import com.example.hexagonalchess.data_layer.model.tile.G.G8
import com.example.hexagonalchess.data_layer.model.tile.Tile

class F8: com.example.hexagonalchess.data_layer.model.tile.Tile(
    id = TileId.F8,
    color = TileColor.LIGHT,
    topTile = com.example.hexagonalchess.data_layer.model.tile.F.F9(),
    upperRightTile = com.example.hexagonalchess.data_layer.model.tile.G.G8(),
    underRightTile = com.example.hexagonalchess.data_layer.model.tile.G.G7(),
    bottomTile = com.example.hexagonalchess.data_layer.model.tile.F.F7(),
    underLeftTile = com.example.hexagonalchess.data_layer.model.tile.E.E8(),
    upperLeftTile = com.example.hexagonalchess.data_layer.model.tile.E.E9()
) {
}