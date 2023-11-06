package com.example.hexagonalchess.data_layer.database

interface DatabasePlayer {
    fun addNewPlayer(name: String, password: String)
    fun checkIfPlayerExists(inputName: String, callback: (Boolean) -> Unit)
    fun checkPassword(inputName: String,inputPassword: String, callback: (Boolean) -> Unit)
}