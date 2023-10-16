package com.example.hexagonalchess.data_layer.model.tile.C

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.data_layer.model.tile.D.D6
import com.example.hexagonalchess.data_layer.model.tile.D.D7
import com.example.hexagonalchess.data_layer.model.tile.Tile

class C6: com.example.hexagonalchess.data_layer.model.tile.Tile(
    id = TileId.C6,
    color = TileColor.LIGHT,
    topTile = com.example.hexagonalchess.data_layer.model.tile.C.C7(),
    upperRightTile = com.example.hexagonalchess.data_layer.model.tile.D.D7(),
    underRightTile = com.example.hexagonalchess.data_layer.model.tile.D.D6(),
    bottomTile = com.example.hexagonalchess.data_layer.model.tile.C.C5(),
    underLeftTile = com.example.hexagonalchess.data_layer.model.tile.C.C5(),
    upperLeftTile = com.example.hexagonalchess.data_layer.model.tile.C.C6()
) {
}