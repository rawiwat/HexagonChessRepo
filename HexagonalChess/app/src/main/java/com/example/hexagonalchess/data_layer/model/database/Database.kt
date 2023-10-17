package com.example.hexagonalchess.data_layer.model.database

interface Database {
    fun getInitialBoard(){}
    fun getBoardState(){}
    fun movePieces(){}
    fun capture(){}
}