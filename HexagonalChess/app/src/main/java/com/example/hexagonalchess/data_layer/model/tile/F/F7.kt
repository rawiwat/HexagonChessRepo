package com.example.hexagonalchess.data_layer.model.tile.F

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.data_layer.model.tile.E.E7
import com.example.hexagonalchess.data_layer.model.tile.E.E8
import com.example.hexagonalchess.data_layer.model.tile.G.G6
import com.example.hexagonalchess.data_layer.model.tile.G.G7
import com.example.hexagonalchess.data_layer.model.tile.Tile

class F7: com.example.hexagonalchess.data_layer.model.tile.Tile(
    id = TileId.F7,
    color = TileColor.DARK,
    topTile = com.example.hexagonalchess.data_layer.model.tile.F.F8(),
    upperRightTile = com.example.hexagonalchess.data_layer.model.tile.G.G7(),
    underRightTile = com.example.hexagonalchess.data_layer.model.tile.G.G6(),
    bottomTile = com.example.hexagonalchess.data_layer.model.tile.F.F6(),
    underLeftTile = com.example.hexagonalchess.data_layer.model.tile.E.E7(),
    upperLeftTile = com.example.hexagonalchess.data_layer.model.tile.E.E8()
) {
}