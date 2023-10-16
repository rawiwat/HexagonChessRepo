package com.example.hexagonalchess.data_layer.model.tile.D

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.data_layer.model.tile.C.C8
import com.example.hexagonalchess.data_layer.model.tile.C.C9
import com.example.hexagonalchess.data_layer.model.tile.E.E10
import com.example.hexagonalchess.data_layer.model.tile.E.E9
import com.example.hexagonalchess.data_layer.model.tile.Tile

class D9: com.example.hexagonalchess.data_layer.model.tile.Tile(
    id = TileId.D9,
    color = TileColor.MID,
    topTile = com.example.hexagonalchess.data_layer.model.tile.D.D10(),
    upperRightTile = com.example.hexagonalchess.data_layer.model.tile.E.E10(),
    underRightTile = com.example.hexagonalchess.data_layer.model.tile.E.E9(),
    bottomTile = com.example.hexagonalchess.data_layer.model.tile.D.D8(),
    underLeftTile = com.example.hexagonalchess.data_layer.model.tile.C.C8(),
    upperLeftTile = com.example.hexagonalchess.data_layer.model.tile.C.C9()
)