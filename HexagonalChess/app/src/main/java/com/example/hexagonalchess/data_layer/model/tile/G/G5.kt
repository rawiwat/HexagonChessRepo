package com.example.hexagonalchess.data_layer.model.tile.G

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.data_layer.model.tile.F.F3
import com.example.hexagonalchess.data_layer.model.tile.F.F5
import com.example.hexagonalchess.data_layer.model.tile.F.F6
import com.example.hexagonalchess.data_layer.model.tile.H.H4
import com.example.hexagonalchess.data_layer.model.tile.H.H5
import com.example.hexagonalchess.data_layer.model.tile.Tile

class G5: com.example.hexagonalchess.data_layer.model.tile.Tile(
    id = TileId.G5,
    color = TileColor.DARK,
    topTile = com.example.hexagonalchess.data_layer.model.tile.G.G6(),
    upperRightTile = com.example.hexagonalchess.data_layer.model.tile.H.H5(),
    underRightTile = com.example.hexagonalchess.data_layer.model.tile.H.H4(),
    bottomTile = com.example.hexagonalchess.data_layer.model.tile.G.G4(),
    underLeftTile = com.example.hexagonalchess.data_layer.model.tile.F.F5(),
    upperLeftTile = com.example.hexagonalchess.data_layer.model.tile.F.F6()
) {
}