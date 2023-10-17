package com.example.hexagonalchess.data_layer.model.pieces

import com.example.hexagonalchess.domain_layer.ChessPieceKeyWord
import com.example.hexagonalchess.domain_layer.PieceColor
import com.example.hexagonalchess.domain_layer.PieceType

open class ChessPiece(
    val type: PieceType,
    val color: PieceColor,
    val keyWord: ChessPieceKeyWord
)