package com.example.hexagonalchess.data_layer.model.tile.H

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId

class H4: com.example.hexagonalchess.data_layer.model.tile.Tile(
    id = TileId.H4,
    color = TileColor.LIGHT,
    topTile = com.example.hexagonalchess.data_layer.model.tile.H.H5(),
    upperRightTile = com.example.hexagonalchess.data_layer.model.tile.I.I4(),
    underRightTile = com.example.hexagonalchess.data_layer.model.tile.I.I3(),
    bottomTile = com.example.hexagonalchess.data_layer.model.tile.H.H3(),
    underLeftTile = com.example.hexagonalchess.data_layer.model.tile.G.G4(),
    upperLeftTile = com.example.hexagonalchess.data_layer.model.tile.G.G5()
) {
}