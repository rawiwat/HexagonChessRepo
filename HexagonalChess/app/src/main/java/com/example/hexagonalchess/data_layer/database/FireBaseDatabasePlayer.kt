package com.example.hexagonalchess.data_layer.database

import android.content.Context
import com.example.hexagonalchess.data_layer.model.player.Player
import com.example.hexagonalchess.domain_layer.player.manager.PlayerNameSharedPref
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class FireBaseDatabasePlayer(
    val context: Context
): DatabasePlayer {
    private val gameRef = FirebaseDatabase.getInstance().reference.child("game")
    private val playersRef = gameRef.child("player")
    override fun addNewPlayer(name: String, password: String) {
        val newPlayer = Player(name, password)
        gameRef.child("player").push().setValue(newPlayer)
        PlayerNameSharedPref(context).savePlayer(name)
    }

    override fun checkIfPlayerExists(inputName: String, callback: (Boolean) -> Unit) {
        playersRef.orderByChild("name").equalTo(inputName)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val playerExists = dataSnapshot.exists()
                    callback(playerExists)
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    callback(false)
                }
            })
    }

    override fun checkPassword(
        inputName: String,
        inputPassword: String,
        callback: (Boolean) -> Unit
    ) {
        gameRef.child("player").orderByChild("name").equalTo(inputName)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    if (dataSnapshot.exists()) {
                        val player = dataSnapshot.children.first().getValue(Player::class.java)
                        if (player != null && player.password == inputPassword) {
                            callback(true)
                        } else {
                            callback(false)
                        }
                    } else {
                        callback(false)
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    // Handle any errors here
                }
            })
    }
}