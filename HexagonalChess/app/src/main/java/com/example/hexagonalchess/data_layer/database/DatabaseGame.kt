package com.example.hexagonalchess.data_layer.database

import com.example.hexagonalchess.data_layer.model.pieces.ChessPiece
import com.example.hexagonalchess.data_layer.model.tile.Tile
import com.example.hexagonalchess.domain_layer.BoardType
import com.example.hexagonalchess.domain_layer.ChessPieceKeyWord
import com.example.hexagonalchess.domain_layer.PieceColor

interface DatabaseGame {
    fun sendPlayerToOnlineWaitingRoom(name: String, board: List<Tile>)
    fun observeBoardState(gameRoomName: String, boardType: BoardType)
    fun movePieces(from: Tile, to: Tile)
    fun capture(keyWord: ChessPieceKeyWord)
    fun observeCapture(color: PieceColor)
    fun giveRankPoint()
    fun sendDrawOffer()
    fun initializeGame(board: List<Tile>)
}