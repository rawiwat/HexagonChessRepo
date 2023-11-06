package com.example.hexagonalchess.data_layer.model.database

import com.example.hexagonalchess.data_layer.database.DatabaseGame
import com.example.hexagonalchess.data_layer.model.pieces.ChessPiece
import com.example.hexagonalchess.data_layer.model.tile.Tile
import com.example.hexagonalchess.domain_layer.BoardType
import com.example.hexagonalchess.domain_layer.ChessPieceKeyWord
import com.example.hexagonalchess.domain_layer.PieceColor
import com.google.firebase.database.FirebaseDatabase

class FirebaseRealtimeDatabaseGame: DatabaseGame {
    private val myRef = FirebaseDatabase.getInstance().getReference("game")

    override fun sendPlayerToOnlineWaitingRoom(name: String) {
        myRef.child("waiting-room").push().setValue(name)
            .addOnSuccessListener {

            }
            .addOnCanceledListener {

            }
    }

    override fun observeBoardState(playerId:Int, boardType: BoardType) {
        when(boardType) {
            BoardType.DEFAULT -> TODO()
            BoardType.STAR_CHESS -> TODO()
            BoardType.SHAFRAN -> TODO()
            BoardType.BIG -> TODO()
        }
    }

    override fun movePieces(from: Tile, to: Tile) {
        myRef.child("")
    }

    override fun capture(keyWord: ChessPieceKeyWord) {
        TODO("Not yet implemented")
    }

    override fun observeCapture(color: PieceColor): List<ChessPiece> {
        TODO("Not yet implemented")
    }

    override fun giveRankPoint() {
        TODO("Not yet implemented")
    }

    override fun sendDrawOffer() {
        TODO("Not yet implemented")
    }

    /*init {
        myRef.child("waiting-room").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val childrenCount = dataSnapshot.childrenCount
                if (childrenCount >= 2) {

                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle an error
            }
        })
    }*/
}