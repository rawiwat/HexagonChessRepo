package com.example.hexagonalchess.data_layer.chess_board_data.big

import com.example.hexagonalchess.data_layer.chess_board_data.shafran.ShafranColumnA
import com.example.hexagonalchess.data_layer.chess_board_data.shafran.ShafranColumnB
import com.example.hexagonalchess.data_layer.chess_board_data.shafran.ShafranColumnC
import com.example.hexagonalchess.data_layer.chess_board_data.shafran.ShafranColumnD
import com.example.hexagonalchess.data_layer.chess_board_data.shafran.ShafranColumnE
import com.example.hexagonalchess.data_layer.chess_board_data.shafran.ShafranColumnF
import com.example.hexagonalchess.data_layer.chess_board_data.shafran.ShafranColumnG
import com.example.hexagonalchess.data_layer.chess_board_data.shafran.ShafranColumnH
import com.example.hexagonalchess.data_layer.chess_board_data.shafran.ShafranColumnI

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