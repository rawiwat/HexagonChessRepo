package com.example.hexagonalchess.data_layer.model.tile.G

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.data_layer.model.tile.F.F8
import com.example.hexagonalchess.data_layer.model.tile.F.F9
import com.example.hexagonalchess.data_layer.model.tile.H.H7
import com.example.hexagonalchess.data_layer.model.tile.H.H8
import com.example.hexagonalchess.data_layer.model.tile.Tile

class G8: com.example.hexagonalchess.data_layer.model.tile.Tile(
    id = TileId.G8,
    color = TileColor.DARK,
    topTile = com.example.hexagonalchess.data_layer.model.tile.G.G9(),
    upperRightTile = com.example.hexagonalchess.data_layer.model.tile.H.H8(),
    underRightTile = com.example.hexagonalchess.data_layer.model.tile.H.H7(),
    bottomTile = com.example.hexagonalchess.data_layer.model.tile.G.G7(),
    underLeftTile = com.example.hexagonalchess.data_layer.model.tile.F.F8(),
    upperLeftTile = com.example.hexagonalchess.data_layer.model.tile.F.F9()
) {
}