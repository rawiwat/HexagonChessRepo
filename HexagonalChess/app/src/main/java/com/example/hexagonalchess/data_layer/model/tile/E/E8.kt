package com.example.hexagonalchess.data_layer.model.tile.E

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.data_layer.model.tile.D.D7
import com.example.hexagonalchess.data_layer.model.tile.D.D8
import com.example.hexagonalchess.data_layer.model.tile.F.F7
import com.example.hexagonalchess.data_layer.model.tile.F.F8
import com.example.hexagonalchess.data_layer.model.tile.Tile

class E8: com.example.hexagonalchess.data_layer.model.tile.Tile(
    id = TileId.E8,
    color = TileColor.MID,
    topTile = com.example.hexagonalchess.data_layer.model.tile.E.E9(),
    upperRightTile = com.example.hexagonalchess.data_layer.model.tile.F.F8(),
    underRightTile = com.example.hexagonalchess.data_layer.model.tile.F.F7(),
    bottomTile = com.example.hexagonalchess.data_layer.model.tile.E.E7(),
    underLeftTile = com.example.hexagonalchess.data_layer.model.tile.D.D7(),
    upperLeftTile = com.example.hexagonalchess.data_layer.model.tile.D.D8()
)