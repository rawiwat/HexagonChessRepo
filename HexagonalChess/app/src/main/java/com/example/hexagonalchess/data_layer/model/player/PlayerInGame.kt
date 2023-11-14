package com.example.hexagonalchess.data_layer.model.player

import com.example.hexagonalchess.data_layer.model.pieces.ChessPiece
import com.example.hexagonalchess.domain_layer.ChessPieceKeyWord
import com.example.hexagonalchess.domain_layer.PieceColor

data class PlayerInGame(
    val name: String = "",
    val color: PieceColor = PieceColor.WHITE,
    val offeredDraw: Boolean = false,
    val offeredResign: Boolean = false,
    var captured: List<ChessPieceKeyWord> = listOf()
)