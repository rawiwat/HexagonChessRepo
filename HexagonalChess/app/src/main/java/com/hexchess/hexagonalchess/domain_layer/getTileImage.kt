package com.hexchess.hexagonalchess.domain_layer

import com.hexchess.hexagonalchess.R

fun getTileImage(tileColor: TileColor, theme: TileTheme): Int {
    return when(theme){
        TileTheme.DEFAULT -> when(tileColor) {
            TileColor.DARK -> R.drawable.tile_black
            TileColor.MID -> R.drawable.tile_grey
            TileColor.LIGHT -> R.drawable.tile_white
        }
        TileTheme.RED -> when(tileColor) {
            TileColor.DARK -> R.drawable.tile_red_dark
            TileColor.MID -> R.drawable.tile_red_mid
            TileColor.LIGHT -> R.drawable.tile_white
        }
        TileTheme.BLUE -> when(tileColor) {
            TileColor.DARK -> R.drawable.tile_blue_dark
            TileColor.MID -> R.drawable.tile_blue_mid
            TileColor.LIGHT -> R.drawable.tile_white
        }
        TileTheme.YELLOW -> when(tileColor) {
            TileColor.DARK -> R.drawable.tile_yellow_dark
            TileColor.MID -> R.drawable.tile_yellow_mid
            TileColor.LIGHT -> R.drawable.tile_white
        }

        TileTheme.PURPLE -> when(tileColor) {
            TileColor.DARK -> R.drawable.tile_purple_dark
            TileColor.MID -> R.drawable.tile_purple_mid
            TileColor.LIGHT -> R.drawable.tile_white
        }
        TileTheme.GREEN -> when(tileColor) {
            TileColor.DARK -> R.drawable.tile_green_dark
            TileColor.MID -> R.drawable.tile_green_mid
            TileColor.LIGHT -> R.drawable.tile_white
        }
        TileTheme.ORANGE -> when(tileColor) {
            TileColor.DARK -> R.drawable.tile_orange_dark
            TileColor.MID -> R.drawable.tile_orange_mid
            TileColor.LIGHT -> R.drawable.tile_white
        }
    }
}