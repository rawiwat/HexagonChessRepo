package com.example.hexagonalchess.data_layer.database

import com.example.hexagonalchess.data_layer.model.player.Player

interface DatabasePlayer {
    fun addNewPlayer(name: String, password: String)
    fun checkIfPlayerExists(inputName: String, callback: (Boolean) -> Unit)
    fun checkPassword(inputName: String,inputPassword: String, callback: (Boolean) -> Unit)
    fun searchPlayerByName(name: String, callback: PlayerSearchCallback)
    fun nameHasForbiddenChar (inputName: String): Boolean
}

interface PlayerSearchCallback {
    fun onPlayerFound(player: Player)
    fun onPlayerNotFound()
}
