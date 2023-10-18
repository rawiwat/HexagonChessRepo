package com.example.hexagonalchess.data_layer.chess_board_data

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

    val allTiles = columnA + columnB + columnC +
                   columnD + columnE + columnF +
                   columnG + columnH + columnI
        //a8,a7,a6,a5,a4,a3,a2,a1
        //b9,b8,b7,b6,b5,b4,b3,b2,b1,
        //c10,c9,c8,c7,c6,c5,c4,c3,c2,c1,
        //d11,d10,d9,d8,d7,d6,d5,d4,d3,d2,d1,
        //e12,e11,e10,e9,e8,e7,e6,e5,e4,e3,e2,e1,
        //f11,f10,f9,f8,f7,f6,f5,f4,f3,f2,f1,
        //g10,g9,g8,g7,g6,g5,g4,g3,g2,g1,
        //h9,h8,h7,h6,h5,h4,h3,h2,h1,
        //i8,i7,i6,i5,i4,i3,i2,i1,
}