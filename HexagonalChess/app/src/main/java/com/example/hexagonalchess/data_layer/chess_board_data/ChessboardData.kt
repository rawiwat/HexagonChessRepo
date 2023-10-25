package com.example.hexagonalchess.data_layer.chess_board_data

import com.example.hexagonalchess.data_layer.model.tile.Tile

class ChessboardData {
    private val columnAWhitePov = ColumnA().columnAWhitePov
    private val columnBWhitePov = ColumnB().columnBWhitePov
    private val columnCWhitePov = ColumnC().columnCWhitePov
    private val columnDWhitePov = ColumnD().columnDWhitePov
    private val columnEWhitePov = ColumnE().columnEWhitePov
    private val columnFWhitePov = ColumnF().columnFWhitePov
    private val columnGWhitePov = ColumnG().columnGWhitePov
    private val columnHWhitePov = ColumnH().columnHWhitePov
    private val columnIWhitePov = ColumnI().columnIWhitePov

    private val columnABlackPov = ColumnA().columnABlackPov
    private val columnBBlackPov = ColumnB().columnBBlackPov
    private val columnCBlackPov = ColumnC().columnCBlackPov
    private val columnDBlackPov = ColumnD().columnDBlackPov
    private val columnEBlackPov = ColumnE().columnEBlackPov
    private val columnFBlackPov = ColumnF().columnFBlackPov
    private val columnGBlackPov = ColumnG().columnGBlackPov
    private val columnHBlackPov = ColumnH().columnHBlackPov
    private val columnIBlackPov = ColumnI().columnIBlackPov

    val allTilesWhitePov: List<Tile> =
                columnAWhitePov + columnBWhitePov + columnCWhitePov +
                columnDWhitePov + columnEWhitePov + columnFWhitePov +
                columnGWhitePov + columnHWhitePov + columnIWhitePov

    val allTilesBlackPov: List<Tile> =
                columnABlackPov + columnBBlackPov + columnCBlackPov +
                columnDBlackPov + columnEBlackPov + columnFBlackPov +
                columnGBlackPov + columnHBlackPov + columnIBlackPov
}