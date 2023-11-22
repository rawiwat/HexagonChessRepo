package com.hexchess.hexagonalchess.data_layer.database

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.core.content.ContextCompat
import com.hexchess.hexagonalchess.data_layer.model.collection.Collectable
import com.hexchess.hexagonalchess.data_layer.model.player.Player
import com.hexchess.hexagonalchess.domain_layer.player.manager.PlayerNameSharedPref
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.GenericTypeIndicator
import com.google.firebase.database.ServerValue
import com.google.firebase.database.ValueEventListener

class FireBaseDatabasePlayer(
    val context: Context
): DatabasePlayer {
    private val gameRef = FirebaseDatabase.getInstance().reference.child("game")
    private val playersRef = gameRef.child("player")
    private var playerKey = ""
    override var currentPrice = 0
    override var updatePrice:() -> Unit = { }
    private var playerName = PlayerNameSharedPref(context).getPlayerName()

    override fun addNewPlayer(name: String, password: String) {
        val newPlayer = Player(name, password)
        gameRef.child("player").push().setValue(newPlayer)
        PlayerNameSharedPref(context).savePlayer(name)
    }

    private val playerReceiver = object : BroadcastReceiver() {
        override fun onReceive(p0: Context?, p1: Intent?) {
            val playerReceived = p1?.getStringExtra("CurrentPlayer")
            playerReceived?.let {
                playerName = it
                getPlayerKey()
            }
        }
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

    override fun searchPlayerByName(name: String, callback: PlayerSearchCallback) {
        playersRef.orderByChild("name").equalTo(name)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    for (snapshot in dataSnapshot.children) {
                        val player = snapshot.getValue(Player::class.java)
                        player?.let {
                            callback.onPlayerFound(player)
                        }
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    // TODO : Handle error
                }
            })
    }

    override fun nameHasForbiddenChar (inputName: String): Boolean {
        val forbiddenCharacters = listOf(' ', '_','/')
        return inputName.any { forbiddenCharacters.contains(it) }
    }

    fun updatePlayerImage(playerName: String, encodedImage: String) {
        playersRef.orderByChild("name").equalTo(playerName)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    for (snapshot in dataSnapshot.children) {
                        val playerId = snapshot.key // Assuming playerId is the unique identifier
                        val playerRef = playerId?.let { playersRef.child(it) }

                        val updates = hashMapOf<String, Any>()
                        updates["encodedImageBitmap"] = encodedImage

                        playerRef?.updateChildren(updates)?.addOnSuccessListener {
                            // Update successful
                        }?.addOnFailureListener {
                            // TODO : Handle the error
                        }
                        return
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    // TODO : Handle error
                }
            })
    }

    override fun updatePlayerCoin(playerName: String, amount:Long) {
        playersRef.orderByChild("name").equalTo(playerName)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    for (snapshot in dataSnapshot.children) {
                        val playerKey = snapshot.key
                        val playerRef = playerKey?.let { playersRef.child(it) }

                        val updates = hashMapOf<String, Any>()
                        updates["coin"] = ServerValue.increment(amount)

                        playerRef?.updateChildren(updates)
                        return
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    // TODO : Handle error
                }
            })
    }

    override fun buy(playerName: String, item: Collectable, callback: () -> Unit) {
        playersRef.orderByChild("name").equalTo(playerName)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    for (snapshot in dataSnapshot.children) {
                        val playerKey = snapshot.key
                        val playerRef = playerKey?.let { playersRef.child(it) }

                        val updates = hashMapOf<String, Any>()
                        updates["coin"] = ServerValue.increment((currentPrice * -1).toLong())
                        playerRef?.updateChildren(updates)?.addOnSuccessListener {
                            addToCollection(item)
                            callback.invoke()
                        }
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    // TODO : Handle error
                }
            })
    }

    override fun calculatePrice() {
        playersRef.child(playerKey).child("collection").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val collection = snapshot.getValue(object : GenericTypeIndicator<List<Collectable>>() {})
                    ?: listOf()

                currentPrice = if(collection.size <= 5) {
                    100
                } else {
                    (collection.size - 4) * 100
                }
                updatePrice.invoke()
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error
            }
        })
    }

    override fun addToCollection(collectable: Collectable) {
        // Retrieve the current collection
        playersRef.child(playerKey).child("collection").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val currentCollection = snapshot.getValue(object : GenericTypeIndicator<List<Collectable>>() {})
                    ?: listOf()

                // Add the new collectable to the collection
                val updatedCollection = currentCollection.toMutableList()
                updatedCollection.add(collectable)

                playersRef.child(playerKey).child("collection").setValue(updatedCollection)
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error
            }
        })
    }

    private fun getPlayerKey() {
        playersRef.orderByChild("name").equalTo(playerName).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (playerSnapshot in snapshot.children) {
                    val player = playerSnapshot.getValue(Player::class.java)
                    // Handle the found player
                    if (player != null) {
                        playerKey = playerSnapshot.key.toString()
                        break
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error
            }
        })
    }

    init {
        ContextCompat.registerReceiver(context, playerReceiver, IntentFilter("CurrentPlayer"), ContextCompat.RECEIVER_NOT_EXPORTED)
        getPlayerKey()
    }
}