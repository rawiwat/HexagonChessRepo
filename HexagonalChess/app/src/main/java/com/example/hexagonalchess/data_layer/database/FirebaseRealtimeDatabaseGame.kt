package com.example.hexagonalchess.data_layer.database

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Handler
import android.os.Looper
import androidx.core.content.ContextCompat
import com.example.hexagonalchess.data_layer.model.pieces.ChessPiece
import com.example.hexagonalchess.data_layer.model.player.Player
import com.example.hexagonalchess.data_layer.model.player.PlayerInGame
import com.example.hexagonalchess.data_layer.model.player.PlayerInWaiting
import com.example.hexagonalchess.data_layer.model.tile.Tile
import com.example.hexagonalchess.domain_layer.BoardType
import com.example.hexagonalchess.domain_layer.ChessGameStateOnline
import com.example.hexagonalchess.domain_layer.ChessPieceKeyWord
import com.example.hexagonalchess.domain_layer.GameEndMethod
import com.example.hexagonalchess.domain_layer.PieceColor
import com.example.hexagonalchess.domain_layer.PieceType
import com.example.hexagonalchess.domain_layer.SimpleDirection
import com.example.hexagonalchess.domain_layer.TileId
import com.example.hexagonalchess.domain_layer.getChessPieceFromKeyWord
import com.example.hexagonalchess.domain_layer.getListOfPromotionTile
import com.example.hexagonalchess.domain_layer.opposite
import com.example.hexagonalchess.domain_layer.player.manager.PlayerNameSharedPref
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class FirebaseRealtimeDatabaseGame(
    val context: Context
) : DatabaseGame {
    private val myRef = FirebaseDatabase.getInstance().getReference("game")
    private val waitingRoomRef = myRef.child("waiting-room")
    private val onlineGameRef = myRef.child("online_game")
    override var gameRoomName = ""
    override var playerColor: PieceColor = PieceColor.WHITE
    override var opponentColor: PieceColor = PieceColor.WHITE
    override var playerName: String = PlayerNameSharedPref(context).getPlayerName().toString()
    override var opponentName: String = ""
    override var drawOffered: Boolean = false
    var allTiles: MutableList<Tile> = mutableListOf()
    val playerCapturedPiece = mutableListOf<ChessPiece>()
    val opponentCapturedPiece = mutableListOf<ChessPiece>()
    var gameEndMessage = ""
    private var currentTurn = PieceColor.WHITE
    override var gameState = ChessGameStateOnline.OPEN
    override var updateBoard: () -> Unit = { }
    override var updateCaptured: () -> Unit = { }
    override var updateTurn: () -> Unit = { }
    override var updateGameState: () -> Unit = { }
    override var updateGameOverMessage: () -> Unit = { }
    override var updateDrawOffering: () -> Unit = { }

    private val playerReceiver = object : BroadcastReceiver() {
        override fun onReceive(p0: Context?, p1: Intent?) {
            val playerReceived = p1?.getStringExtra("CurrentPlayer")
            playerReceived?.let {
                playerName = it
            }
        }
    }

    init {
        ContextCompat.registerReceiver(context, playerReceiver, IntentFilter("CurrentPlayer"), ContextCompat.RECEIVER_NOT_EXPORTED)
    }

    override fun sendPlayerToOnlineWaitingRoom(name: String, board: List<Tile>, boardType: BoardType) {
        val playerModel = PlayerInWaiting(
            name = name,
            boardType = boardType
        )
        val playerWaitingRefs =  myRef.child("waiting-room").push()

        val playerWaitingKey = playerWaitingRefs.key
        playerWaitingRefs.setValue(playerModel)
            .addOnSuccessListener { checkAndCreateRoom(board, boardType) }
        val playerWaitingRefListener = playerWaitingKey?.let { myRef.child("waiting-room").child(it) }

        playerWaitingRefListener?.addChildEventListener(
            object : ChildEventListener {
                override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {}

                override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {}

                override fun onChildRemoved(snapshot: DataSnapshot) {
                    onlineGameRef.get().addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val dataSnapshot = task.result
                            val gameRoomList = mutableListOf<String>()
                            if (dataSnapshot != null) {
                                for (child in dataSnapshot.children) {
                                    gameRoomList.add(child.value.toString())
                                }
                            }

                            for (roomName in gameRoomList) {
                                val splitName = roomName.split("_")
                                if (splitName[0] == playerName) {
                                    gameRoomName = roomName
                                    println(gameRoomName)
                                    opponentName = splitName[1]
                                    break
                                } else if (splitName[1] == playerName) {
                                    gameRoomName = roomName
                                    println(gameRoomName)
                                    opponentName = splitName[0]
                                    break
                                }
                            }
                            updateColor()

                            val turnValueEventListener = object : ValueEventListener {
                                override fun onDataChange(dataSnapshot: DataSnapshot) {
                                    dataSnapshot.getValue(PieceColor::class.java)?.let {
                                        currentTurn = it
                                        updateTurn.invoke()
                                        observeAndUpdateBoardState()
                                    }
                                }

                                override fun onCancelled(databaseError: DatabaseError) {
                                    // TODO : Handle errors that occurred while trying to read the data
                                }
                            }
                            onlineGameRef.child(gameRoomName).child("currentTurn").addValueEventListener(turnValueEventListener)

                            val gameStateEventListener = object : ValueEventListener {
                                override fun onDataChange(dataSnapshot: DataSnapshot) {
                                    dataSnapshot.getValue(ChessGameStateOnline::class.java)?.let {
                                        gameState = it
                                        when(it) {
                                            ChessGameStateOnline.GAME_OVER -> {
                                                updateGameOverMessage.invoke()
                                                updateGameState.invoke()
                                                onlineGameRef.child(gameRoomName).removeValue()
                                            }
                                            else -> { }
                                        }
                                    }
                                }

                                override fun onCancelled(databaseError: DatabaseError) {
                                    // TODO : Handle errors that occurred while trying to read the data
                                }
                            }
                            onlineGameRef.child(gameRoomName).child("gameState").addValueEventListener(gameStateEventListener)

                            val gameMessageEventListener = object : ValueEventListener {
                                override fun onDataChange(dataSnapshot: DataSnapshot) {
                                    dataSnapshot.getValue(String::class.java)?.let {
                                        gameEndMessage = it
                                    }
                                }

                                override fun onCancelled(databaseError: DatabaseError) {
                                    // TODO : Handle errors that occurred while trying to read the data
                                }
                            }
                            onlineGameRef.child(gameRoomName).child("gameOverMessage").addValueEventListener(gameMessageEventListener)

                            val drawOfferedListener = object : ValueEventListener {
                                override fun onDataChange(dataSnapshot: DataSnapshot) {
                                    val player = dataSnapshot.getValue(PlayerInGame::class.java)
                                    drawOffered = player?.offeredDraw ?: false
                                    updateDrawOffering.invoke()
                                }

                                override fun onCancelled(databaseError: DatabaseError) {}
                            }
                            onlineGameRef.child(gameRoomName).child(playerName).addValueEventListener(drawOfferedListener)

                            context.sendBroadcast(Intent("GAME_START"))
                        } else {
                            val exception = task.exception
                            println("Error: ${exception?.message}")
                        }
                    }
                }

                override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {}

                override fun onCancelled(error: DatabaseError) {}
            }
        )
    }

    private fun checkAndCreateRoom(board: List<Tile>, boardType: BoardType) {
        waitingRoomRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val count = dataSnapshot.childrenCount.toInt()
                println("Player : $playerName with $boardType board added to waiting room")
                println("number of player in waiting room : $count")
                if (count >= 2) {
                    val viablePlayersInWaitingRoom = mutableListOf<PlayerInWaiting>()

                    for (childSnapshot in dataSnapshot.children) {
                        val playerInWaiting = childSnapshot.getValue(PlayerInWaiting::class.java)
                        playerInWaiting?.let {
                            println("Player : ${it.name} with ${it.boardType} board founded in waiting room")
                            if (it.name != playerName && it.boardType == boardType) {
                                println("Player : ${it.name} with ${it.boardType} board is Available")
                                viablePlayersInWaitingRoom.add(it)
                            }
                        }
                    }

                    if (viablePlayersInWaitingRoom.isNotEmpty()) {
                        println("Available players are ")
                        for (availablePlayer in viablePlayersInWaitingRoom) {
                            println("${availablePlayer.name} with ${availablePlayer.boardType} board")
                        }
                        val randomPlayer = viablePlayersInWaitingRoom.random()
                        opponentName = randomPlayer.name
                        gameRoomName = "${playerName}_${opponentName}"
                        println(gameRoomName)
                        initializeGame(board)
                    }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle any errors here
            }
        })
    }

    override fun observeAndUpdateBoardState() {
        println("updating the board")
        val boardRef = onlineGameRef.child(gameRoomName).child("board")
        val updatedBoard = mutableListOf<Tile>()
        boardRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (tileSnapshot in dataSnapshot.children) {
                        val tile = tileSnapshot.getValue(Tile::class.java)
                        if (tile != null) {
                            updatedBoard.add(tile)
                        }
                    }
                    allTiles = updatedBoard
                    Handler(Looper.getMainLooper()).post {
                        updateBoard.invoke()
                    }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // TODO : Handle Errors
            }
        })
    }

    override fun getCurrentBoard(): List<Tile> {
        for (tile in allTiles) {
            if (tile.chessPiece != null) {
                println("${tile.id} : ${tile.chessPiece!!.keyWord}")
            } else {
                println("${tile.id} : no piece")
            }
        }
        return allTiles
    }

    override fun getPlayerCaptured(): MutableList <ChessPiece> {
        return playerCapturedPiece
    }

    override fun getOpponentCaptured(): MutableList <ChessPiece> {
        return opponentCapturedPiece
    }

    override fun getCurrentTurn(): PieceColor {
        return currentTurn
    }

    override fun getCurrentGameState(): ChessGameStateOnline {
        return gameState
    }

    override fun getGameOverMessage(): String {
        return gameEndMessage
    }

    override fun movePieces(from: Tile, to: Tile, boardType: BoardType) {
        val movingTileId = from.id
        val targetTileId = to.id
        val boardRef = onlineGameRef.child(gameRoomName).child("board")
        val movingPiece = from.chessPiece

        removePieceByTileID(movingTileId)

        boardRef.orderByChild("id").equalTo(targetTileId.name).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (tileSnapshot in dataSnapshot.children) {
                        val tile = tileSnapshot.getValue(Tile::class.java)
                        if (tile != null) {
                            // Do something with the retrieved tile
                            val movedInTile = Tile(
                                id = tile.id,
                                color = tile.color,
                                topTile = tile.topTile,
                                upperRightTile = tile.upperRightTile,
                                underRightTile = tile.underRightTile,
                                bottomTile = tile.bottomTile,
                                underLeftTile = tile.underLeftTile,
                                upperLeftTile = tile.upperLeftTile,
                                isAPossibleMove = false,
                                chessPiece = movingPiece
                            )
                            val tileKey = tileSnapshot.key
                            if (tileKey != null) {
                                boardRef.child(tileKey).setValue(movedInTile)
                                println("Tile $targetTileId updated successfully")
                            }
                        }
                    }
                } else {
                    println("No tile found with TileId: $targetTileId")
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                println("Error updating tile: ${databaseError.toException()}")
            }
        })

        if (to.chessPiece != null) {
            capture(to.chessPiece!!.keyWord)
        }

        val newTurn = playerColor.opposite()

        val turnRef = onlineGameRef.child(gameRoomName).child("currentTurn")
        if (from.chessPiece!!.type == PieceType.PAWN && getListOfPromotionTile(boardType, from.chessPiece!!.color).contains(to.id)) {
            when(from.chessPiece!!.color) {
                PieceColor.BLACK -> onlineGameRef.child(gameRoomName).child("gameState").setValue(ChessGameStateOnline.BLACK_PROMOTE)
                PieceColor.WHITE -> onlineGameRef.child(gameRoomName).child("gameState").setValue(ChessGameStateOnline.WHITE_PROMOTE)
            }
        }

        turnRef.setValue(newTurn)
            .addOnSuccessListener {
                println("Current turn is now : $newTurn")
        }
    }

    override fun capture(keyWord: ChessPieceKeyWord) {
        val piece = getChessPieceFromKeyWord(keyWord)
        val targetPlayerColor = when(piece.color) {
            PieceColor.WHITE -> {
                PieceColor.BLACK
            }

            PieceColor.BLACK -> {
                PieceColor.WHITE
            }
        }

        val path = onlineGameRef.child(gameRoomName).child("players")
        path.orderByChild("color").equalTo(targetPlayerColor.name).addListenerForSingleValueEvent(
            object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        for (playerFound in snapshot.children) {
                            val player = playerFound.getValue(PlayerInGame::class.java)
                            player?.let {
                                val updatedCapture = player.captured + piece.keyWord
                                player.captured = updatedCapture
                                val key = snapshot.key
                                if (key != null) {
                                    path.child(key).setValue(player)
                                }
                                when (targetPlayerColor) {
                                    playerColor -> playerCapturedPiece.add(piece)
                                    else -> opponentCapturedPiece.add(piece)
                                }
                                updateCaptured.invoke()
                            }
                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            }
        )
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
                    val splitPlayer = gameRoomName.split("_")
                    val player1Name = splitPlayer[0]
                    val player2Name = splitPlayer[1]
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

                    val turnRef = gameRoomRef.child("currentTurn")
                    turnRef.setValue(PieceColor.WHITE)

                    val gameStateRef = gameRoomRef.child("gameState")
                    gameStateRef.setValue(ChessGameStateOnline.OPEN)

                    val gameMessage = gameRoomRef.child("gameOverMessage")
                    gameMessage.setValue("The game is not over yet")

                    removeFromWaitingRoom(player1Name)
                    removeFromWaitingRoom(player2Name)
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // TODO : Handle error
            }
        })
    }

    override fun enableEnPassant(tileId: TileId, direction: SimpleDirection) {
        val boardRefs = onlineGameRef.child(gameRoomName).child("board")
        boardRefs.orderByChild("id").equalTo(tileId.name).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (tileSnapshot in dataSnapshot.children) {
                        val tile = tileSnapshot.getValue(Tile::class.java)
                        if (tile != null) {
                            tile.chessPiece?.let {
                                val updatedPiece = when(direction) {
                                    SimpleDirection.RIGHT -> ChessPiece(
                                        type = it.type,
                                        color = it.color,
                                        keyWord = it.keyWord,
                                        materialValue = it.materialValue,
                                        enPassantLeftEnable = it.enPassantLeftEnable,
                                        enPassantRightEnable = true
                                    )
                                    SimpleDirection.LEFT -> ChessPiece(
                                        type = it.type,
                                        color = it.color,
                                        keyWord = it.keyWord,
                                        materialValue = it.materialValue,
                                        enPassantLeftEnable = true,
                                        enPassantRightEnable = it.enPassantRightEnable
                                    )
                                }

                                val updatedTile = Tile(
                                    id = tile.id,
                                    color = tile.color,
                                    topTile = tile.topTile,
                                    upperRightTile = tile.upperRightTile,
                                    underRightTile = tile.underRightTile,
                                    bottomTile = tile.bottomTile,
                                    underLeftTile = tile.underLeftTile,
                                    upperLeftTile = tile.upperLeftTile,
                                    isAPossibleMove = false,
                                    chessPiece = updatedPiece
                                )

                                val tileKey = tileSnapshot.key
                                if (tileKey != null) {
                                    boardRefs.child(tileKey).setValue(updatedTile)
                                    println("Tile $tileId enable en passant")
                                }
                            }
                        }
                    }
                } else {
                    println("No tile found with TileId: $tileId")
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                println("Error getting tile: ${databaseError.toException()}")
            }
        })
    }

    override fun promote(tileId: TileId, keyWord: ChessPieceKeyWord) {
        val boardRefs = onlineGameRef.child(gameRoomName).child("board")
        boardRefs.orderByChild("id").equalTo(tileId.name).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (tileSnapshot in dataSnapshot.children) {
                        val tile = tileSnapshot.getValue(Tile::class.java)
                        if (tile != null) {
                            tile.chessPiece?.let {
                                val promotedPiece = getChessPieceFromKeyWord(keyWord)
                                val updatedTile = Tile(
                                    id = tile.id,
                                    color = tile.color,
                                    topTile = tile.topTile,
                                    upperRightTile = tile.upperRightTile,
                                    underRightTile = tile.underRightTile,
                                    bottomTile = tile.bottomTile,
                                    underLeftTile = tile.underLeftTile,
                                    upperLeftTile = tile.upperLeftTile,
                                    isAPossibleMove = false,
                                    chessPiece = promotedPiece
                                )

                                val tileKey = tileSnapshot.key
                                if (tileKey != null) {
                                    boardRefs.child(tileKey).setValue(updatedTile)
                                    println("Tile $tileId enable en passant")
                                }
                            }
                        }
                    }
                } else {
                    println("No tile found with TileId: $tileId")
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                println("Error getting tile: ${databaseError.toException()}")
            }
        })
    }

    override fun removePieceByTileID(tileId: TileId) {
        val boardRef = onlineGameRef.child(gameRoomName).child("board")

        boardRef.orderByChild("id").equalTo(tileId.name).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (tileSnapshot in dataSnapshot.children) {
                        val tile = tileSnapshot.getValue(Tile::class.java)
                        if (tile != null) {
                            val movedOutTile = Tile(
                                id = tile.id,
                                color = tile.color,
                                topTile = tile.topTile,
                                upperRightTile = tile.upperRightTile,
                                underRightTile = tile.underRightTile,
                                bottomTile = tile.bottomTile,
                                underLeftTile = tile.underLeftTile,
                                upperLeftTile = tile.upperLeftTile,
                                isAPossibleMove = false,
                                chessPiece = null
                            )
                            val tileKey = tileSnapshot.key
                            if (tileKey != null) {
                                boardRef.child(tileKey).setValue(movedOutTile)
                            }
                        }
                    }
                } else {
                    println("No tile found with TileId: $tileId")
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                println("Error getting tile: ${databaseError.toException()}")
            }
        })
    }

    override fun drawOffering(boolean: Boolean) {
        onlineGameRef.child(gameRoomName)
            .child("players")
            .child(opponentName)
            .updateChildren(mapOf<String, Any>("offeredDraw" to boolean))
    }

    override fun gameOver(winnerName: String, loserName: String, method: GameEndMethod) {
        val ratingGain = when (method) {
            GameEndMethod.KING_WAS_CAPTURED -> 10
            GameEndMethod.DRAW -> 0
            GameEndMethod.RESIGN -> 10
            GameEndMethod.CHECKMATE -> 10
        }
        val ratingLose = when (method) {
            GameEndMethod.KING_WAS_CAPTURED -> 5
            GameEndMethod.DRAW -> 0
            GameEndMethod.RESIGN -> 5
            GameEndMethod.CHECKMATE -> 5
        }

        val gameOverMessage = when(method) {
            GameEndMethod.KING_WAS_CAPTURED -> "$winnerName Wins\n$loserName King was captured"
            GameEndMethod.DRAW -> "Draw! $winnerName accept the draw offer"
            GameEndMethod.RESIGN -> "$winnerName win $loserName resign"
            GameEndMethod.CHECKMATE -> "$winnerName Wins\nCheckmate"
        }

        myRef.child("player").orderByChild("name").equalTo(winnerName)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (childSnapshot in snapshot.children) {
                        val winningPlayer = childSnapshot.getValue(Player::class.java)
                        winningPlayer?.let {
                            val winningPlayerWithUpdatedRating = Player(
                                name = winningPlayer.name,
                                password = winningPlayer.password,
                                rating = winningPlayer.rating + ratingGain,
                                encodedImageBitmap = winningPlayer.encodedImageBitmap,
                                playerId = winningPlayer.playerId,
                            )
                            val key = childSnapshot.key
                            if (key != null) {
                                myRef.child("player").child(key).setValue(winningPlayerWithUpdatedRating)
                            }
                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    // TODO: Handle error
                }
            })

        myRef.child("player").orderByChild("name").equalTo(loserName)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (childSnapshot in snapshot.children) {
                        val losingPlayer = childSnapshot.getValue(Player::class.java)
                        losingPlayer?.let {
                            val losingPlayerWithUpdatedRating = Player(
                                name = losingPlayer.name,
                                password = losingPlayer.password,
                                rating = losingPlayer.rating - ratingLose,
                                encodedImageBitmap = losingPlayer.encodedImageBitmap,
                                playerId = losingPlayer.playerId,
                            )
                            val key = childSnapshot.key
                            if (key != null) {
                                myRef.child("player").child(key).setValue(losingPlayerWithUpdatedRating)
                            }
                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    // TODO: Handle error
                }
            })

        onlineGameRef.child(gameRoomName).child("gameOverMessage").setValue(gameOverMessage)
        onlineGameRef.child(gameRoomName).child("gameState").setValue(ChessGameStateOnline.GAME_OVER)
    }

    private fun removeFromWaitingRoom(name: String) {
        waitingRoomRef.orderByChild("name").equalTo(name)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (childSnapshot in snapshot.children) {
                        childSnapshot.ref.removeValue()
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    // TODO : Handle error
                }
            })
    }

    fun updateColor() {
        val playersRef = onlineGameRef.child(gameRoomName).child("players")
        println("Player name is $playerName")
        playersRef.child(playerName).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    val playerModel = dataSnapshot.getValue(PlayerInGame::class.java)
                    val foundedPlayerColor = playerModel?.color
                    if (foundedPlayerColor != null) {
                        println("$playerName color: $foundedPlayerColor")
                        playerColor = foundedPlayerColor
                        opponentColor = playerColor.opposite()
                        checkValue()
                    }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // TODO : Handle Error
            }
        })
    }

    private fun checkValue() {
        println("checking name and color\nyour name : $playerName\nyour color : $playerColor\nopponent name : $opponentName\nopponent color : $opponentColor")
    }
}