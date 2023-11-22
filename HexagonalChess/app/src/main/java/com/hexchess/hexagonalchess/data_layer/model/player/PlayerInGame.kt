package com.hexchess.hexagonalchess.data_layer.model.player

import com.hexchess.hexagonalchess.domain_layer.ChessPieceKeyWord
import com.hexchess.hexagonalchess.domain_layer.PieceColor

data class PlayerInGame(
    val name: String = "",
    val color: PieceColor = PieceColor.WHITE,
    val offeredDraw: Boolean = false,
    var captured: List<ChessPieceKeyWord> = listOf()
)