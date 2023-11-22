package com.hexchess.hexagonalchess.data_layer.chess_board_data.shafran

class ShafranChessBoardData {
    private val columnA = ShafranColumnA().columnA
    private val columnB = ShafranColumnB().columnB
    private val columnC = ShafranColumnC().columnC
    private val columnD = ShafranColumnD().columnD
    private val columnE = ShafranColumnE().columnE
    private val columnF = ShafranColumnF().columnF
    private val columnG = ShafranColumnG().columnG
    private val columnH = ShafranColumnH().columnH
    private val columnI = ShafranColumnI().columnI

    val allTiles =
            columnA + columnB + columnC +
            columnD + columnE + columnF +
            columnG + columnH + columnI
}