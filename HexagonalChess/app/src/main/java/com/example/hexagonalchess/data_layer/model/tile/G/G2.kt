package com.example.hexagonalchess.data_layer.model.tile.G

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.data_layer.model.tile.F.F2
import com.example.hexagonalchess.data_layer.model.tile.F.F3
import com.example.hexagonalchess.data_layer.model.tile.H.H1
import com.example.hexagonalchess.data_layer.model.tile.H.H2
import com.example.hexagonalchess.data_layer.model.tile.Tile

class G2: com.example.hexagonalchess.data_layer.model.tile.Tile(
    id = TileId.G2,
    color = TileColor.DARK,
    topTile = com.example.hexagonalchess.data_layer.model.tile.G.G3(),
    upperRightTile = com.example.hexagonalchess.data_layer.model.tile.H.H2(),
    underRightTile = com.example.hexagonalchess.data_layer.model.tile.H.H1(),
    bottomTile = com.example.hexagonalchess.data_layer.model.tile.G.G1(),
    underLeftTile = com.example.hexagonalchess.data_layer.model.tile.F.F2(),
    upperLeftTile = com.example.hexagonalchess.data_layer.model.tile.F.F3()
) {
}