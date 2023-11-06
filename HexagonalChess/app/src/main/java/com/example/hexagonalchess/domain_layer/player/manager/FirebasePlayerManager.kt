package com.example.hexagonalchess.domain_layer.player.manager

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class FirebasePlayerManager: ManagePlayer {
    private val database = FirebaseDatabase.getInstance()
    private val playersRef = database.getReference("players")

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
}