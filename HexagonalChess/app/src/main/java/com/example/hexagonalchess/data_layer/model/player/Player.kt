package com.example.hexagonalchess.data_layer.model.player

import androidx.compose.ui.graphics.ImageBitmap
import com.example.hexagonalchess.data_layer.model.collection.Collectable
import com.example.hexagonalchess.domain_layer.CollectableType
import com.example.hexagonalchess.domain_layer.TileTheme
import com.example.hexagonalchess.domain_layer.player.generatePlayerIdFromName

data class Player(
    val name: String = "",
    val password: String = "",
    val rating: Int = 100,
    val coin: Int = 0,
    val encodedImageBitmap:String = "",
    val playerId: Int = generatePlayerIdFromName(name),
    val collection:List<Collectable> = listOf(
        Collectable(
            type = CollectableType.TILE_THEME,
            name = TileTheme.DEFAULT.name
        ),
        Collectable(
            type = CollectableType.TILE_THEME,
            name = TileTheme.RED.name
        ),
        Collectable(
            type = CollectableType.TILE_THEME,
            name = TileTheme.YELLOW.name
        ),
        Collectable(
            type = CollectableType.TILE_THEME,
            name = TileTheme.BLUE.name
        )
    )
)
