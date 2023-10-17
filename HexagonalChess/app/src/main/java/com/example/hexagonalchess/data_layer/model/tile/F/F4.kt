package com.example.hexagonalchess.data_layer.model.tile.F

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId

class F4: com.example.hexagonalchess.data_layer.model.tile.Tile(
    id = TileId.F4,
    color = TileColor.DARK,
    topTile = com.example.hexagonalchess.data_layer.model.tile.F.F5(),
    upperRightTile = com.example.hexagonalchess.data_layer.model.tile.G.G4(),
    underRightTile = com.example.hexagonalchess.data_layer.model.tile.G.G3(),
    bottomTile = com.example.hexagonalchess.data_layer.model.tile.F.F3(),
    underLeftTile = com.example.hexagonalchess.data_layer.model.tile.E.E4(),
    upperLeftTile = com.example.hexagonalchess.data_layer.model.tile.E.E5()
) {
}