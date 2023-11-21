package com.example.hexagonalchess.data_layer.database

import com.example.hexagonalchess.data_layer.model.collection.Collectable
import com.example.hexagonalchess.data_layer.model.player.Player

interface DatabasePlayer {
    fun addNewPlayer(name: String, password: String)
    fun checkIfPlayerExists(inputName: String, callback: (Boolean) -> Unit)
    fun checkPassword(inputName: String,inputPassword: String, callback: (Boolean) -> Unit)
    fun searchPlayerByName(name: String, callback: PlayerSearchCallback)
    fun nameHasForbiddenChar (inputName: String): Boolean
    fun updatePlayerCoin(playerName: String, amount:Long)
    fun buy(playerName: String, item:Collectable, callback: () -> Unit)
    fun calculatePrice()
    fun addToCollection(collectable: Collectable)
    var currentPrice: Int
    var updatePrice:() -> Unit
}

interface PlayerSearchCallback {
    fun onPlayerFound(player: Player)
    fun onPlayerNotFound()
}
