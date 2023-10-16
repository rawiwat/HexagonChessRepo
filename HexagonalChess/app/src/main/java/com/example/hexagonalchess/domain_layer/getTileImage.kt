package com.example.hexagonalchess.domain_layer

import com.example.hexagonalchess.R
import com.example.hexagonalchess.TileColor

fun getTileImage(tileColor: TileColor): Int {
    return when(tileColor) {
        TileColor.DARK -> R.drawable.tile_black
        TileColor.MID -> R.drawable.tile_grey
        TileColor.LIGHT -> R.drawable.tile_white
    }
}