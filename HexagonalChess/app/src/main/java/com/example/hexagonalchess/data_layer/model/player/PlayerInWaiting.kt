package com.example.hexagonalchess.data_layer.model.player

import com.example.hexagonalchess.domain_layer.BoardType

data class PlayerInWaiting (
    val name: String = "",
    val boardType: BoardType = BoardType.DEFAULT
)