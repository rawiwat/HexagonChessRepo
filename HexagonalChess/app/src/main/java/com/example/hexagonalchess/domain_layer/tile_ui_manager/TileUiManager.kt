package com.example.hexagonalchess.domain_layer.tile_ui_manager

class TileUiManager(screenWidth:Int) {
    val tileHeight = screenWidth / 9
    val tileWidth = tileHeight * 1.15449202351
    val columnAY = tileHeight * 2
    val columnBX = tileWidth * 3 / 4
    val columnBY = tileHeight * 3 / 2
    val columnCX = tileWidth * 6 / 4
    val columnCY = tileHeight
    val columnDX = tileWidth * 9 / 4
    val columnDY = tileHeight / 2
    val columnEX = tileWidth * 12 / 4
    val columnFX = tileWidth * 15 / 4
    val columnFY = tileHeight / 2
    val columnGX = tileWidth * 18 / 4
    val columnGY = tileHeight
    val columnHX = tileWidth * 21 / 4
    val columnHY = tileHeight * 3 / 2
    val columnIX = tileWidth * 24 / 4
    val columnIY = tileHeight * 2

    val columnAYShuriken = tileHeight * 3
    val columnBYShuriken = tileHeight * 2.5
    val columnDYShuriken = tileHeight * 0.5
    val columnEYShuriken = tileHeight
    val columnFYShuriken = tileHeight * 0.5
    val columnHYShuriken = tileHeight * 2.5
    val columnIYShuriken = tileHeight * 3
}