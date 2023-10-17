package com.example.hexagonalchess.data_layer.model.tile.H

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId

class H3: com.example.hexagonalchess.data_layer.model.tile.Tile(
    id = TileId.H3,
    color = TileColor.DARK,
    topTile = com.example.hexagonalchess.data_layer.model.tile.H.H4(),
    upperRightTile = com.example.hexagonalchess.data_layer.model.tile.I.I3(),
    underRightTile = com.example.hexagonalchess.data_layer.model.tile.I.I2(),
    bottomTile = com.example.hexagonalchess.data_layer.model.tile.H.H2(),
    underLeftTile = com.example.hexagonalchess.data_layer.model.tile.G.G3(),
    upperLeftTile = com.example.hexagonalchess.data_layer.model.tile.G.G4()
) {
}