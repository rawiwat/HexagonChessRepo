package com.example.hexagonalchess.data_layer.model.tile.G

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.data_layer.model.tile.F.F6
import com.example.hexagonalchess.data_layer.model.tile.F.F7
import com.example.hexagonalchess.data_layer.model.tile.H.H5
import com.example.hexagonalchess.data_layer.model.tile.H.H6
import com.example.hexagonalchess.data_layer.model.tile.Tile

class G6: com.example.hexagonalchess.data_layer.model.tile.Tile(
    id = TileId.G6,
    color = TileColor.LIGHT,
    topTile = com.example.hexagonalchess.data_layer.model.tile.G.G7(),
    upperRightTile = com.example.hexagonalchess.data_layer.model.tile.H.H6(),
    underRightTile = com.example.hexagonalchess.data_layer.model.tile.H.H5(),
    bottomTile = com.example.hexagonalchess.data_layer.model.tile.G.G5(),
    underLeftTile = com.example.hexagonalchess.data_layer.model.tile.F.F6(),
    upperLeftTile = com.example.hexagonalchess.data_layer.model.tile.F.F7()
) {
}