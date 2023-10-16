package com.example.hexagonalchess.data_layer.model.tile.B

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.data_layer.model.tile.A.A4
import com.example.hexagonalchess.data_layer.model.tile.A.A5
import com.example.hexagonalchess.data_layer.model.tile.C.C5
import com.example.hexagonalchess.data_layer.model.tile.C.C6
import com.example.hexagonalchess.data_layer.model.tile.Tile

class B5: com.example.hexagonalchess.data_layer.model.tile.Tile(
    id = TileId.B5,
    color = TileColor.MID,
    topTile = com.example.hexagonalchess.data_layer.model.tile.B.B6(),
    upperRightTile = com.example.hexagonalchess.data_layer.model.tile.C.C6(),
    underRightTile = com.example.hexagonalchess.data_layer.model.tile.C.C5(),
    bottomTile = com.example.hexagonalchess.data_layer.model.tile.B.B4(),
    underLeftTile = com.example.hexagonalchess.data_layer.model.tile.A.A4(),
    upperLeftTile = com.example.hexagonalchess.data_layer.model.tile.A.A5()
) {
}