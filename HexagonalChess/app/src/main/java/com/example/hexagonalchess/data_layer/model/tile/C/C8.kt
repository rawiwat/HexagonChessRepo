package com.example.hexagonalchess.data_layer.model.tile.C

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.data_layer.model.tile.B.B7
import com.example.hexagonalchess.data_layer.model.tile.B.B8
import com.example.hexagonalchess.data_layer.model.tile.D.D8
import com.example.hexagonalchess.data_layer.model.tile.D.D9
import com.example.hexagonalchess.data_layer.model.tile.Tile

class C8: com.example.hexagonalchess.data_layer.model.tile.Tile(
    id = TileId.C8,
    color = TileColor.DARK,
    topTile = com.example.hexagonalchess.data_layer.model.tile.C.C9(),
    upperRightTile = com.example.hexagonalchess.data_layer.model.tile.D.D9(),
    underRightTile = com.example.hexagonalchess.data_layer.model.tile.D.D8(),
    bottomTile = com.example.hexagonalchess.data_layer.model.tile.C.C7(),
    underLeftTile = com.example.hexagonalchess.data_layer.model.tile.B.B7(),
    upperLeftTile = com.example.hexagonalchess.data_layer.model.tile.B.B8()
)