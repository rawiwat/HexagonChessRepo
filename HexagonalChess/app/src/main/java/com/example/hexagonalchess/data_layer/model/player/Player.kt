package com.example.hexagonalchess.data_layer.model.player

import androidx.compose.ui.graphics.ImageBitmap
import com.example.hexagonalchess.domain_layer.player.generatePlayerIdFromName

data class Player(
    val name: String = "",
    val password: String = "",
    val rating: Int = 100,
    val encodedImageBitmap:String = "",
    val playerId: Int = generatePlayerIdFromName(name),
)
