package com.example.hexagonalchess.data_layer.model.pieces

import com.example.hexagonalchess.PieceColor
import com.example.hexagonalchess.PieceType
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.data_layer.model.tile.Tile

open class ChessPiece(
    val type: PieceType,
    val color: PieceColor,
    val tile: com.example.hexagonalchess.data_layer.model.tile.Tile,
)