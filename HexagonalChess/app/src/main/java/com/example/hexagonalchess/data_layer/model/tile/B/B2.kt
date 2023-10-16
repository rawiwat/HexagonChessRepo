package com.example.hexagonalchess.data_layer.model.tile.B

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.data_layer.model.tile.A.A1
import com.example.hexagonalchess.data_layer.model.tile.A.A2
import com.example.hexagonalchess.data_layer.model.tile.C.C2
import com.example.hexagonalchess.data_layer.model.tile.C.C3
import com.example.hexagonalchess.data_layer.model.tile.Tile

class B2: com.example.hexagonalchess.data_layer.model.tile.Tile(
    id = TileId.B2,
    color = TileColor.MID,
    topTile = com.example.hexagonalchess.data_layer.model.tile.B.B3(),
    upperRightTile = com.example.hexagonalchess.data_layer.model.tile.C.C3(),
    underRightTile = com.example.hexagonalchess.data_layer.model.tile.C.C2(),
    bottomTile = com.example.hexagonalchess.data_layer.model.tile.B.B1(),
    underLeftTile = com.example.hexagonalchess.data_layer.model.tile.A.A1(),
    upperLeftTile = com.example.hexagonalchess.data_layer.model.tile.A.A2()
)