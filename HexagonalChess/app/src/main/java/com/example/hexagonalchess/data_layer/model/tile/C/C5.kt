package com.example.hexagonalchess.data_layer.model.tile.C

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.data_layer.model.tile.B.B4
import com.example.hexagonalchess.data_layer.model.tile.B.B5
import com.example.hexagonalchess.data_layer.model.tile.D.D5
import com.example.hexagonalchess.data_layer.model.tile.D.D6
import com.example.hexagonalchess.data_layer.model.tile.Tile

class C5: com.example.hexagonalchess.data_layer.model.tile.Tile(
    id = TileId.C5,
    color = TileColor.DARK,
    topTile = com.example.hexagonalchess.data_layer.model.tile.C.C6(),
    upperRightTile = com.example.hexagonalchess.data_layer.model.tile.D.D6(),
    underRightTile = com.example.hexagonalchess.data_layer.model.tile.D.D5(),
    bottomTile = com.example.hexagonalchess.data_layer.model.tile.C.C4(),
    underLeftTile = com.example.hexagonalchess.data_layer.model.tile.B.B4(),
    upperLeftTile = com.example.hexagonalchess.data_layer.model.tile.B.B5()
) {
}