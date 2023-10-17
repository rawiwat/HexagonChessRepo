package com.example.hexagonalchess.data_layer.model.tile

import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.data_layer.model.pieces.ChessPiece

open class Tile(
    val id: TileId,
    val color: TileColor,
    var topTile: TileId?,
    var upperRightTile: TileId?,
    var underRightTile: TileId?,
    var bottomTile: TileId?,
    var underLeftTile: TileId?,
    var upperLeftTile: TileId?,
    var isAPossibleMove: Boolean = false,
    var isSelected: Boolean = false,
    var chessPiece: ChessPiece? = null
)