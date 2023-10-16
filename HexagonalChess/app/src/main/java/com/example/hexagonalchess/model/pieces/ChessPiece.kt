package com.example.hexagonalchess.model.pieces

import com.example.hexagonalchess.PieceColor
import com.example.hexagonalchess.PieceType
import com.example.hexagonalchess.model.tile.Tile

data class ChessPiece(
    val type: PieceType,
    val color: PieceColor,
    val tile: Tile,
) {
    fun calculatePossibleMove() {

    }
}