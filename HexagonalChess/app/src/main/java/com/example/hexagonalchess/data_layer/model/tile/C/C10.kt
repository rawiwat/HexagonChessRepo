package com.example.hexagonalchess.data_layer.model.tile.C

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.data_layer.model.tile.B.B9
import com.example.hexagonalchess.data_layer.model.tile.D.D10
import com.example.hexagonalchess.data_layer.model.tile.D.D11
import com.example.hexagonalchess.data_layer.model.tile.Tile

class C10: com.example.hexagonalchess.data_layer.model.tile.Tile(
    id = TileId.C10,
    color = TileColor.MID,
    topTile = null,
    upperRightTile = com.example.hexagonalchess.data_layer.model.tile.D.D11(),
    underRightTile = com.example.hexagonalchess.data_layer.model.tile.D.D10(),
    bottomTile = com.example.hexagonalchess.data_layer.model.tile.C.C9(),
    underLeftTile = com.example.hexagonalchess.data_layer.model.tile.B.B9(),
    upperLeftTile = null
) {
}