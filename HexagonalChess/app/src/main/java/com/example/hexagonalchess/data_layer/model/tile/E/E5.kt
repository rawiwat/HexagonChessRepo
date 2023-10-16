package com.example.hexagonalchess.data_layer.model.tile.E

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.data_layer.model.tile.D.D4
import com.example.hexagonalchess.data_layer.model.tile.D.D5
import com.example.hexagonalchess.data_layer.model.tile.F.F4
import com.example.hexagonalchess.data_layer.model.tile.F.F5
import com.example.hexagonalchess.data_layer.model.tile.Tile

class E5: com.example.hexagonalchess.data_layer.model.tile.Tile(
    id = TileId.E5,
    color = TileColor.MID,
    topTile = com.example.hexagonalchess.data_layer.model.tile.E.E6(),
    upperRightTile = com.example.hexagonalchess.data_layer.model.tile.F.F5(),
    underRightTile = com.example.hexagonalchess.data_layer.model.tile.F.F4(),
    bottomTile = com.example.hexagonalchess.data_layer.model.tile.E.E4(),
    underLeftTile = com.example.hexagonalchess.data_layer.model.tile.D.D4(),
    upperLeftTile = com.example.hexagonalchess.data_layer.model.tile.D.D5()
) {
}