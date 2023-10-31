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

    val bigTileWidth = screenWidth / 10
    val bigTileHeight = bigTileWidth / 1.15449202351
    val bigColumnAY = bigTileHeight * 2.5
    val bigColumnBX = bigTileWidth * 3 / 4
    val bigColumnBY = bigTileHeight * 2
    val bigColumnCX = bigTileWidth * 3 / 4
    val bigColumnCY = bigTileHeight * 1.5
    val bigColumnDX = bigTileWidth * 9 / 4
    val bigColumnDY = bigTileHeight
    val bigColumnEX = bigTileWidth * 12 / 4
    val bigColumnEY = bigTileHeight * 0.5
    val bigColumnFX = bigTileWidth * 15 / 4
    val bigColumnGX = bigTileWidth * 18 / 4
    val bigColumnGY = bigTileHeight * 0.5
    val bigColumnHX = bigTileWidth * 21 / 4
    val bigColumnHY = bigTileHeight
    val bigColumnIX = bigTileWidth * 24 / 4
    val bigColumnIY = bigTileHeight * 1.5
    val bigColumnJX = bigTileWidth * 27 / 4
    val bigColumnJY = bigTileHeight * 2
    val bigColumnKX = bigTileWidth * 30 / 4
    val bigColumnKY = bigTileHeight * 2.5
}