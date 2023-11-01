package com.example.hexagonalchess.domain_layer.piecemove

import com.example.hexagonalchess.data_layer.model.tile.Tile
import com.example.hexagonalchess.domain_layer.BoardType
import com.example.hexagonalchess.domain_layer.TileId

suspend fun queenMove(selectedTile: Tile, board: List<Tile>, boardType: BoardType):List<TileId?> {
    return bishopMove(selectedTile, board, boardType) + rookMove(selectedTile, board, boardType)
}