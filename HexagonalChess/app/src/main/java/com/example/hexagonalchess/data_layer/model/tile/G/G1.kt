package com.example.hexagonalchess.data_layer.model.tile.G

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.data_layer.model.tile.F.F1
import com.example.hexagonalchess.data_layer.model.tile.F.F2
import com.example.hexagonalchess.data_layer.model.tile.H.H1
import com.example.hexagonalchess.data_layer.model.tile.Tile

class G1: com.example.hexagonalchess.data_layer.model.tile.Tile(
    id = TileId.G1,
    color = TileColor.MID,
    topTile = com.example.hexagonalchess.data_layer.model.tile.G.G2(),
    upperRightTile = com.example.hexagonalchess.data_layer.model.tile.H.H1(),
    underRightTile = null,
    bottomTile = null,
    underLeftTile = com.example.hexagonalchess.data_layer.model.tile.F.F1(),
    upperLeftTile = com.example.hexagonalchess.data_layer.model.tile.F.F2()
) {
}