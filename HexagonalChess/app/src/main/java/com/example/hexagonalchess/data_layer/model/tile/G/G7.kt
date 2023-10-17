package com.example.hexagonalchess.data_layer.model.tile.G

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId

class G7: com.example.hexagonalchess.data_layer.model.tile.Tile(
    id = TileId.G7,
    color = TileColor.MID,
    topTile = com.example.hexagonalchess.data_layer.model.tile.G.G8(),
    upperRightTile = com.example.hexagonalchess.data_layer.model.tile.H.H7(),
    underRightTile = com.example.hexagonalchess.data_layer.model.tile.H.H6(),
    bottomTile = com.example.hexagonalchess.data_layer.model.tile.G.G6(),
    underLeftTile = com.example.hexagonalchess.data_layer.model.tile.F.F7(),
    upperLeftTile = com.example.hexagonalchess.data_layer.model.tile.F.F8()
) {
}