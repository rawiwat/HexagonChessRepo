package com.example.hexagonalchess.data_layer.model.collection

import com.example.hexagonalchess.domain_layer.CollectableType

data class Collectable(
    val type: CollectableType = CollectableType.PIECE_THEME,
    val name: String = ""
)