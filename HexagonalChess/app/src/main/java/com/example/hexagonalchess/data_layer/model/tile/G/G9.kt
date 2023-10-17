package com.example.hexagonalchess.data_layer.model.tile.G

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId

class G9: com.example.hexagonalchess.data_layer.model.tile.Tile(
    id = TileId.F1,
    color = TileColor.LIGHT,
    topTile = com.example.hexagonalchess.data_layer.model.tile.G.G10(),
    upperRightTile = com.example.hexagonalchess.data_layer.model.tile.H.H9(),
    underRightTile = com.example.hexagonalchess.data_layer.model.tile.H.H8(),
    bottomTile = com.example.hexagonalchess.data_layer.model.tile.G.G8(),
    underLeftTile = com.example.hexagonalchess.data_layer.model.tile.F.F9(),
    upperLeftTile = com.example.hexagonalchess.data_layer.model.tile.F.F10()
) {
}