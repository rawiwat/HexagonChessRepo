package com.example.hexagonalchess.data_layer.database

import com.example.hexagonalchess.data_layer.model.pieces.ChessPiece
import com.example.hexagonalchess.data_layer.model.tile.Tile
import com.example.hexagonalchess.domain_layer.BoardType
import com.example.hexagonalchess.domain_layer.ChessGameStateOnline
import com.example.hexagonalchess.domain_layer.ChessPieceKeyWord
import com.example.hexagonalchess.domain_layer.GameEndMethod
import com.example.hexagonalchess.domain_layer.PieceColor
import com.example.hexagonalchess.domain_layer.SimpleDirection
import com.example.hexagonalchess.domain_layer.TileId

interface DatabaseGame {
    fun sendPlayerToOnlineWaitingRoom(name: String, board: List<Tile>, boardType: BoardType)
    fun observeAndUpdateBoardState()
    fun getCurrentBoard(): List<Tile>
    fun getPlayerCaptured(): MutableList<ChessPiece>
    fun getOpponentCaptured(): MutableList<ChessPiece>
    fun getCurrentTurn(): PieceColor
    fun getCurrentGameState(): ChessGameStateOnline
    fun getGameOverMessage(): String
    fun movePieces(from: Tile, to: Tile, boardType: BoardType)
    fun capture(keyWord: ChessPieceKeyWord)
    fun sendDrawOffer()
    fun gameOver(winnerName: String, loserName: String, method: GameEndMethod)
    fun initializeGame(board: List<Tile>)
    fun enableEnPassant(tileId: TileId, direction: SimpleDirection)
    fun promote(tileId: TileId, keyWord: ChessPieceKeyWord)
    fun removePieceByTileID(tileId: TileId)
    fun drawOffering(boolean: Boolean)
    val playerColor: PieceColor
    val opponentColor: PieceColor
    val gameRoomName: String
    var playerName: String
    val opponentName: String
    var drawOffered: Boolean
    val gameState: ChessGameStateOnline
    var updateBoard:() -> Unit
    var updateCaptured:() -> Unit
    var updateTurn:()-> Unit
    var updateGameState:() -> Unit
    var updateGameOverMessage:() -> Unit
    var updateDrawOffering:() -> Unit
}