package com.example.hexagonalchess.data_layer.model.tile.E

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.data_layer.model.tile.D.D2
import com.example.hexagonalchess.data_layer.model.tile.D.D3
import com.example.hexagonalchess.data_layer.model.tile.F.F2
import com.example.hexagonalchess.data_layer.model.tile.F.F3
import com.example.hexagonalchess.data_layer.model.tile.Tile

class E3: com.example.hexagonalchess.data_layer.model.tile.Tile(
    id = TileId.E3,
    color = TileColor.DARK,
    topTile = com.example.hexagonalchess.data_layer.model.tile.E.E4(),
    upperRightTile = com.example.hexagonalchess.data_layer.model.tile.F.F3(),
    underRightTile = com.example.hexagonalchess.data_layer.model.tile.F.F2(),
    bottomTile = com.example.hexagonalchess.data_layer.model.tile.E.E2(),
    underLeftTile = com.example.hexagonalchess.data_layer.model.tile.D.D2(),
    upperLeftTile = com.example.hexagonalchess.data_layer.model.tile.D.D3()
) {
}