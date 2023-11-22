package com.hexchess.hexagonalchess.data_layer.model.player

import com.hexchess.hexagonalchess.domain_layer.BoardType

data class PlayerInWaiting (
    val name: String = "",
    val boardType: BoardType = BoardType.DEFAULT
)