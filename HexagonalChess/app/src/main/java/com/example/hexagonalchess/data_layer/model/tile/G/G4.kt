package com.example.hexagonalchess.data_layer.model.tile.G

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.data_layer.model.tile.F.F4
import com.example.hexagonalchess.data_layer.model.tile.F.F5
import com.example.hexagonalchess.data_layer.model.tile.H.H3
import com.example.hexagonalchess.data_layer.model.tile.H.H4
import com.example.hexagonalchess.data_layer.model.tile.Tile

class G4: com.example.hexagonalchess.data_layer.model.tile.Tile(
    id = TileId.G4,
    color = TileColor.MID,
    topTile = com.example.hexagonalchess.data_layer.model.tile.G.G5(),
    upperRightTile = com.example.hexagonalchess.data_layer.model.tile.H.H4(),
    underRightTile = com.example.hexagonalchess.data_layer.model.tile.H.H3(),
    bottomTile = com.example.hexagonalchess.data_layer.model.tile.G.G3(),
    underLeftTile = com.example.hexagonalchess.data_layer.model.tile.F.F4(),
    upperLeftTile = com.example.hexagonalchess.data_layer.model.tile.F.F5()
)