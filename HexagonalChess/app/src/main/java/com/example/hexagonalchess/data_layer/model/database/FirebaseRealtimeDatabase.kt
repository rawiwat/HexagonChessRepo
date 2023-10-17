package com.example.hexagonalchess.data_layer.model.database

import com.example.hexagonalchess.data_layer.model.tile.Tile
import com.example.hexagonalchess.domain_layer.ChessPieceKeyWord
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class FirebaseRealtimeDatabase: Database {
    private val myRef = FirebaseDatabase.getInstance().getReference("game")

    override fun initialBoard(player1Name: String, player2Name: String) {
        myRef.child("game_room").child(player1Name + player2Name).child("a1").setValue(ChessPieceKeyWord.WHITE_PAWN.name)
        myRef.child("game_room").child(player1Name + player2Name).child("a2").setValue(ChessPieceKeyWord.NONE.name)
        myRef.child("game_room").child(player1Name + player2Name).child("a3").setValue(ChessPieceKeyWord.NONE.name)
        myRef.child("game_room").child(player1Name + player2Name).child("a4").setValue(ChessPieceKeyWord.NONE.name)
        myRef.child("game_room").child(player1Name + player2Name).child("a5").setValue(ChessPieceKeyWord.NONE.name)
        myRef.child("game_room").child(player1Name + player2Name).child("a6").setValue(ChessPieceKeyWord.NONE.name)
        myRef.child("game_room").child(player1Name + player2Name).child("a7").setValue(ChessPieceKeyWord.NONE.name)
        myRef.child("game_room").child(player1Name + player2Name).child("a8").setValue(ChessPieceKeyWord.BLACK_PAWN.name)
    }

    override fun sendPlayerToOnlineWaitingRoom(name: String) {
        myRef.child("waitingroom").child("player")
    }

    override fun getBoardState() {
        TODO("Not yet implemented")
    }

    override fun movePieces(from: Tile, to: Tile) {
        myRef.child("")
    }

    override fun capture() {
        TODO("Not yet implemented")
    }

    init {
        myRef.child("watingroom").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val childrenCount = dataSnapshot.childrenCount
                if (childrenCount >= 2) {

                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle an error
            }
        })
    }
}