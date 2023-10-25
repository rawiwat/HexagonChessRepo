package com.example.hexagonalchess.data_layer.chess_board_data

import com.example.hexagonalchess.data_layer.model.tile.Tile

class ChessboardData {
    private val columnA = ColumnA().columnA
    private val columnB = ColumnB().columnB
    private val columnC = ColumnC().columnC
    private val columnD = ColumnD().columnD
    private val columnE = ColumnE().columnE
    private val columnF = ColumnF().columnF
    private val columnG = ColumnG().columnG
    private val columnH = ColumnH().columnH
    private val columnI = ColumnI().columnI

    val allTilesWhitePov: List<Tile> =
                columnA + columnB + columnC +
                columnD + columnE + columnF +
                columnG + columnH + columnI

}