package com.example.hexagonalchess.data_layer.model.tile.F

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.data_layer.model.tile.E.E6
import com.example.hexagonalchess.data_layer.model.tile.E.E7
import com.example.hexagonalchess.data_layer.model.tile.G.G5
import com.example.hexagonalchess.data_layer.model.tile.G.G6
import com.example.hexagonalchess.data_layer.model.tile.Tile

class F6: com.example.hexagonalchess.data_layer.model.tile.Tile(
    id = TileId.F6,
    color = TileColor.MID,
    topTile = com.example.hexagonalchess.data_layer.model.tile.F.F7(),
    upperRightTile = com.example.hexagonalchess.data_layer.model.tile.G.G6(),
    underRightTile = com.example.hexagonalchess.data_layer.model.tile.G.G5(),
    bottomTile = com.example.hexagonalchess.data_layer.model.tile.F.F5(),
    underLeftTile = com.example.hexagonalchess.data_layer.model.tile.E.E6(),
    upperLeftTile = com.example.hexagonalchess.data_layer.model.tile.E.E7()
) {
}