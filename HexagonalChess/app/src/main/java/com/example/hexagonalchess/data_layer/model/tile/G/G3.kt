package com.example.hexagonalchess.data_layer.model.tile.G

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId

class G3: com.example.hexagonalchess.data_layer.model.tile.Tile(
    id = TileId.G3,
    color = TileColor.LIGHT,
    topTile = com.example.hexagonalchess.data_layer.model.tile.G.G4(),
    upperRightTile = com.example.hexagonalchess.data_layer.model.tile.H.H3(),
    underRightTile = com.example.hexagonalchess.data_layer.model.tile.H.H2(),
    bottomTile = com.example.hexagonalchess.data_layer.model.tile.G.G2(),
    underLeftTile = com.example.hexagonalchess.data_layer.model.tile.F.F3(),
    upperLeftTile = com.example.hexagonalchess.data_layer.model.tile.F.F4()
) {
}