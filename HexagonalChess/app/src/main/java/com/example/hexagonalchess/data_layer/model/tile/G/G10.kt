package com.example.hexagonalchess.data_layer.model.tile.G

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId

class G10: com.example.hexagonalchess.data_layer.model.tile.Tile(
    id = TileId.G10,
    color = TileColor.MID,
    topTile = null,
    upperRightTile = null,
    underRightTile = com.example.hexagonalchess.data_layer.model.tile.H.H9(),
    bottomTile = com.example.hexagonalchess.data_layer.model.tile.G.G9(),
    underLeftTile = com.example.hexagonalchess.data_layer.model.tile.F.F10(),
    upperLeftTile = com.example.hexagonalchess.data_layer.model.tile.F.F11()
) {
}