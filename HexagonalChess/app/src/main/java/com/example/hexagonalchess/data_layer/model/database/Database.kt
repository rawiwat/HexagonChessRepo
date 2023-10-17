package com.example.hexagonalchess.data_layer.model.database

import com.example.hexagonalchess.data_layer.model.tile.Tile

interface Database {
    fun sendPlayerToOnlineWaitingRoom(name: String)
    fun getBoardState()
    fun movePieces(from:Tile,to:Tile)
    fun capture()
}