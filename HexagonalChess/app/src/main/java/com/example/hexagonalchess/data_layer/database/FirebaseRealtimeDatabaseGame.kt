package com.example.hexagonalchess.data_layer.database

import com.example.hexagonalchess.data_layer.model.player.PlayerInGame
import com.example.hexagonalchess.data_layer.model.tile.Tile
import com.example.hexagonalchess.domain_layer.BoardType
import com.example.hexagonalchess.domain_layer.ChessPieceKeyWord
import com.example.hexagonalchess.domain_layer.PieceColor
import com.example.hexagonalchess.domain_layer.opposite
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class FirebaseRealtimeDatabaseGame: DatabaseGame {
    private val myRef = FirebaseDatabase.getInstance().getReference("game")

    private val waitingRoomRef = myRef.child("waiting-room")

    var gameRoomName = ""
    override fun sendPlayerToOnlineWaitingRoom(name: String, board: List<Tile>) {
        myRef.child("waiting-room").push().setValue(name)
            .addOnSuccessListener {
                checkAndCreateRoom(board)
            }
            .addOnCanceledListener {}
    }

    private fun checkAndCreateRoom(board: List<Tile>) {
        waitingRoomRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val count = dataSnapshot.childrenCount.toInt()
                if (count >= 2) {
                    val firstTwoPlayers = mutableListOf<String>()

                    for (childSnapshot in dataSnapshot.children) {
                        if (firstTwoPlayers.size < 2) {
                            val playerName = childSnapshot.getValue(String::class.java)
                            if (playerName != null) {
                                firstTwoPlayers.add(playerName)
                            }
                        } else {
                            break
                        }
                    }
                    gameRoomName = firstTwoPlayers[0] + "/" + firstTwoPlayers[1]
                    initializeGame(board)
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle any errors here
            }
        })
    }
    override fun observeBoardState(gameRoomName: String, boardType: BoardType) {
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

    override fun observeCapture(color: PieceColor) {
        TODO("Not yet implemented")
    }

    override fun giveRankPoint() {
        TODO("Not yet implemented")
    }

    override fun sendDrawOffer() {
        TODO("Not yet implemented")
    }

    override fun initializeGame(board: List<Tile>) {
        val gameRoomRef = myRef.child("online_game").child(gameRoomName)

        gameRoomRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    // do nothing the room already existed in database
                } else {
                    // Create the game room since it doesn't exist
                    val splitedPlayer = gameRoomName.split("/")
                    val player1Name = splitedPlayer[0]
                    val player2Name = splitedPlayer[1]
                    val player1Color = listOf(PieceColor.WHITE,PieceColor.BLACK).random()
                    val player2Color = player1Color.opposite()
                    val player1Model = PlayerInGame(
                        name = player1Name,
                        color = player1Color
                    )
                    val player2Model = PlayerInGame(
                        name = player2Name,
                        color = player2Color
                    )
                    val playersRef = gameRoomRef.child("players")
                    playersRef.child(player1Name).push().setValue(player1Model)
                    playersRef.child(player2Name).push().setValue(player2Model)

                    val boardRef = gameRoomRef.child("board")
                    for (tile in board) {
                        boardRef.push().setValue(tile)
                    }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // TODO : Handle error
            }
        })
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