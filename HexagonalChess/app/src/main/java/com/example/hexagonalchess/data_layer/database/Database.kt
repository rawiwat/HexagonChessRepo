package com.example.hexagonalchess.data_layer.model.database

import com.example.hexagonalchess.data_layer.model.pieces.ChessPiece
import com.example.hexagonalchess.data_layer.model.tile.Tile
import com.example.hexagonalchess.domain_layer.ChessPieceKeyWord
import com.example.hexagonalchess.domain_layer.PieceColor

interface Database {
    fun sendPlayerToOnlineWaitingRoom(name: String)
    fun observeBoardState()
    fun movePieces(from:Tile,to:Tile)
    fun capture(keyWord: ChessPieceKeyWord)
    fun observeCapture(color: PieceColor):List<ChessPiece>
    fun giveRankPoint()
    fun sendDrawOffer()
}