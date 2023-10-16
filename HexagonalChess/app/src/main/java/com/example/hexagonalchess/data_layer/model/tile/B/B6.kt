package com.example.hexagonalchess.data_layer.model.tile.B

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.data_layer.model.tile.A.A5
import com.example.hexagonalchess.data_layer.model.tile.A.A6
import com.example.hexagonalchess.data_layer.model.tile.C.C6
import com.example.hexagonalchess.data_layer.model.tile.C.C7
import com.example.hexagonalchess.data_layer.model.tile.Tile

class B6: com.example.hexagonalchess.data_layer.model.tile.Tile(
    id = TileId.B6,
    color = TileColor.DARK,
    topTile = com.example.hexagonalchess.data_layer.model.tile.B.B7(),
    upperRightTile = com.example.hexagonalchess.data_layer.model.tile.C.C7(),
    underRightTile = com.example.hexagonalchess.data_layer.model.tile.C.C6(),
    bottomTile = com.example.hexagonalchess.data_layer.model.tile.B.B5(),
    underLeftTile = com.example.hexagonalchess.data_layer.model.tile.A.A5(),
    upperLeftTile = com.example.hexagonalchess.data_layer.model.tile.A.A6()
)