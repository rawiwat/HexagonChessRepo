package com.example.hexagonalchess.data_layer.model.tile.C

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.data_layer.model.tile.B.B2
import com.example.hexagonalchess.data_layer.model.tile.B.B3
import com.example.hexagonalchess.data_layer.model.tile.D.D3
import com.example.hexagonalchess.data_layer.model.tile.D.D4
import com.example.hexagonalchess.data_layer.model.tile.Tile

class C3: com.example.hexagonalchess.data_layer.model.tile.Tile(
    id = TileId.C3,
    color = TileColor.LIGHT,
    topTile = com.example.hexagonalchess.data_layer.model.tile.C.C4(),
    upperRightTile = com.example.hexagonalchess.data_layer.model.tile.D.D4(),
    underRightTile = com.example.hexagonalchess.data_layer.model.tile.D.D3(),
    bottomTile = com.example.hexagonalchess.data_layer.model.tile.C.C2(),
    underLeftTile = com.example.hexagonalchess.data_layer.model.tile.B.B2(),
    upperLeftTile = com.example.hexagonalchess.data_layer.model.tile.B.B3()
) {
}