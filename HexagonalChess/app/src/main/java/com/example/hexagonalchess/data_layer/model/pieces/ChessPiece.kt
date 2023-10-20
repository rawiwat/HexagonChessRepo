package com.example.hexagonalchess.data_layer.model.pieces

import com.example.hexagonalchess.domain_layer.ChessPieceKeyWord
import com.example.hexagonalchess.domain_layer.PieceColor
import com.example.hexagonalchess.domain_layer.PieceType

open class ChessPiece(
    val type: PieceType,
    val color: PieceColor,
    val keyWord: ChessPieceKeyWord,
    var materialValue: Int = 0
) {
    init {
        materialValue = when(type) {
            PieceType.KNIGHT -> 3
            PieceType.PAWN -> 1
            PieceType.BISHOP -> 3
            PieceType.ROOK -> 5
            PieceType.QUEEN -> 9
            PieceType.KING -> 0
        }
    }
}