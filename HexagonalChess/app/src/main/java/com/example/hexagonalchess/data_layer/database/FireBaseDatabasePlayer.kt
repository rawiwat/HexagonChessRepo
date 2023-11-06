package com.example.hexagonalchess.data_layer.database

import android.content.Context
import com.example.hexagonalchess.data_layer.model.player.Player
import com.example.hexagonalchess.domain_layer.player.manager.PlayerNameSharedPref
import com.google.firebase.database.FirebaseDatabase

class FireBaseDatabasePlayer(
    val context: Context
): DatabasePlayer {
    private val gameRef = FirebaseDatabase.getInstance().reference.child("game")
    override fun addNewPlayer(name: String, password: String) {
        val newPlayer = Player(name, password)
        gameRef.child("player").push().setValue(newPlayer)
        PlayerNameSharedPref(context).savePlayer(name)
    }
}