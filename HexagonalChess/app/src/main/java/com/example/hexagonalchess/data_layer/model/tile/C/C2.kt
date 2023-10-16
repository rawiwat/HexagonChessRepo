package com.example.hexagonalchess.data_layer.model.tile.C

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.data_layer.model.tile.B.B1
import com.example.hexagonalchess.data_layer.model.tile.B.B2
import com.example.hexagonalchess.data_layer.model.tile.D.D2
import com.example.hexagonalchess.data_layer.model.tile.D.D3
import com.example.hexagonalchess.data_layer.model.tile.Tile

class C2: com.example.hexagonalchess.data_layer.model.tile.Tile(
    id = TileId.C2,
    color = TileColor.DARK,
    topTile = com.example.hexagonalchess.data_layer.model.tile.C.C3(),
    upperRightTile = com.example.hexagonalchess.data_layer.model.tile.D.D3(),
    underRightTile = com.example.hexagonalchess.data_layer.model.tile.D.D2(),
    bottomTile = com.example.hexagonalchess.data_layer.model.tile.C.C1(),
    underLeftTile = com.example.hexagonalchess.data_layer.model.tile.B.B1(),
    upperLeftTile = com.example.hexagonalchess.data_layer.model.tile.B.B2()
)