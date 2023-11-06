package com.example.hexagonalchess.data_layer.database

interface DatabasePlayer {
    fun addNewPlayer(name: String, password: String)
}