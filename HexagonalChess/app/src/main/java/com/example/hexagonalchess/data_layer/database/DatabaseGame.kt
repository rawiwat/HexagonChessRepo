package com.example.hexagonalchess.data_layer.database

import com.example.hexagonalchess.data_layer.model.pieces.ChessPiece
import com.example.hexagonalchess.data_layer.model.tile.Tile
import com.example.hexagonalchess.domain_layer.BoardType
import com.example.hexagonalchess.domain_layer.ChessGameStateOnline
import com.example.hexagonalchess.domain_layer.ChessPieceKeyWord
import com.example.hexagonalchess.domain_layer.PieceColor

interface DatabaseGame {
    fun sendPlayerToOnlineWaitingRoom(name: String, board: List<Tile>, boardType: BoardType)
    fun observeAndUpdateBoardState()
    fun getCurrentBoard(): List<Tile>
    fun getPlayerCaptured(): MutableList<ChessPiece>
    fun getOpponentCaptured(): MutableList<ChessPiece>
    fun getCurrentTurn(): PieceColor
    fun getGameState(): ChessGameStateOnline
    fun movePieces(from: Tile, to: Tile)
    fun capture(keyWord: ChessPieceKeyWord)
    fun giveRankPoint()
    fun sendDrawOffer()
    fun initializeGame(board: List<Tile>)
    val playerColor: PieceColor
    val opponentColor: PieceColor
    val gameRoomName: String
    var playerName: String
    val opponentName: String
    val gameState: ChessGameStateOnline
    var updateBoard:() -> Unit
    var updateCaptured:() -> Unit
    var updateTurn:()-> Unit
    var updateGameState:() -> Unit
}