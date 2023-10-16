package com.example.hexagonalchess.data_layer.model.tile.F

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.data_layer.model.tile.E.E5
import com.example.hexagonalchess.data_layer.model.tile.E.E6
import com.example.hexagonalchess.data_layer.model.tile.G.G4
import com.example.hexagonalchess.data_layer.model.tile.G.G5
import com.example.hexagonalchess.data_layer.model.tile.Tile

class F5: com.example.hexagonalchess.data_layer.model.tile.Tile(
    id = TileId.F5,
    color = TileColor.LIGHT,
    topTile = com.example.hexagonalchess.data_layer.model.tile.F.F6(),
    upperRightTile = com.example.hexagonalchess.data_layer.model.tile.G.G5(),
    underRightTile = com.example.hexagonalchess.data_layer.model.tile.G.G4(),
    bottomTile = com.example.hexagonalchess.data_layer.model.tile.F.F4(),
    underLeftTile = com.example.hexagonalchess.data_layer.model.tile.E.E5(),
    upperLeftTile = com.example.hexagonalchess.data_layer.model.tile.E.E6()
) {
}