package com.example.hexagonalchess.data_layer.chess_board_data.starchess

class ShurikenBoardData {
    private val columnABC = ShurikenColumnABC().columnABC
    private val columnDEF = ShurikenColumnDEF().columnDEF
    private val columnGHI = ShurikenColumnGHI().columnGHI
    val allTiles = columnABC + columnDEF + columnGHI
}