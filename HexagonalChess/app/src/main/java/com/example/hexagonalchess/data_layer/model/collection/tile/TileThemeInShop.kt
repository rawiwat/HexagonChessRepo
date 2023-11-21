package com.example.hexagonalchess.data_layer.model.collection.tile

import com.example.hexagonalchess.data_layer.model.collection.Collectable
import com.example.hexagonalchess.domain_layer.CollectableType
import com.example.hexagonalchess.domain_layer.TileTheme

class TileThemeInShop {
    private val greenCollectableTile = Collectable(
        type = CollectableType.TILE_THEME,
        name = TileTheme.GREEN.name
    )
    private val purpleCollectableTile = Collectable(
        type = CollectableType.TILE_THEME,
        name = TileTheme.PURPLE.name
    )
    private val orangeCollectableTile = Collectable(
        type = CollectableType.TILE_THEME,
        name = TileTheme.ORANGE.name
    )

    val allCollectableTile = listOf(greenCollectableTile, purpleCollectableTile, orangeCollectableTile)
}