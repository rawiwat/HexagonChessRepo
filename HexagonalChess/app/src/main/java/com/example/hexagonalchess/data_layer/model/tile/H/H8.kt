package com.example.hexagonalchess.data_layer.model.tile.H

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.data_layer.model.tile.G.G8
import com.example.hexagonalchess.data_layer.model.tile.G.G9
import com.example.hexagonalchess.data_layer.model.tile.I.I7
import com.example.hexagonalchess.data_layer.model.tile.I.I8
import com.example.hexagonalchess.data_layer.model.tile.Tile

class H8: Tile(
    id = TileId.H8,
    color = TileColor.MID,
    topTile = H9(),
    upperRightTile = I8(),
    underRightTile = I7(),
    bottomTile = H7(),
    underLeftTile = G8(),
    upperLeftTile = G9()
)