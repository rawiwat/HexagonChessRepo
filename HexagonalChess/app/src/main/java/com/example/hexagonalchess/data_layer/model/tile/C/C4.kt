package com.example.hexagonalchess.data_layer.model.tile.C

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.data_layer.model.tile.B.B3
import com.example.hexagonalchess.data_layer.model.tile.B.B4
import com.example.hexagonalchess.data_layer.model.tile.D.D4
import com.example.hexagonalchess.data_layer.model.tile.D.D5
import com.example.hexagonalchess.data_layer.model.tile.Tile

class C4: com.example.hexagonalchess.data_layer.model.tile.Tile(
    id = TileId.C4,
    color = TileColor.MID,
    topTile = com.example.hexagonalchess.data_layer.model.tile.C.C5(),
    upperRightTile = com.example.hexagonalchess.data_layer.model.tile.D.D5(),
    underRightTile = com.example.hexagonalchess.data_layer.model.tile.D.D4(),
    bottomTile = com.example.hexagonalchess.data_layer.model.tile.C.C3(),
    underLeftTile = com.example.hexagonalchess.data_layer.model.tile.B.B3(),
    upperLeftTile = com.example.hexagonalchess.data_layer.model.tile.B.B4()
)