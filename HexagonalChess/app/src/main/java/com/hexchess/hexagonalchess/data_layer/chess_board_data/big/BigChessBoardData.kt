package com.hexchess.hexagonalchess.data_layer.chess_board_data.big

class BigChessBoardData {
    private val columnA = BigColumnA().columnA
    private val columnB = BigColumnB().columnB
    private val columnC = BigColumnC().columnC
    private val columnD = BigColumnD().columnD
    private val columnE = BigColumnE().columnE
    private val columnF = BigColumnF().columnF
    private val columnG = BigColumnG().columnG
    private val columnH = BigColumnH().columnH
    private val columnI = BigColumnI().columnI
    private val columnJ = BigColumnJ().columnJ
    private val columnK = BigColumnK().columnK

    val allTiles = columnA + columnB + columnC + columnD + columnE + columnF +
                columnG + columnH + columnI + columnJ + columnK
}