package com.example.hexagonalchess.domain_layer.piecemove

import com.example.hexagonalchess.data_layer.model.tile.Tile
import com.example.hexagonalchess.domain_layer.TileId


fun queenMove(selectedTile: Tile, board: List<Tile>):List<TileId?> {
    return bishopMove(selectedTile, board) + rookMove(selectedTile, board)
}