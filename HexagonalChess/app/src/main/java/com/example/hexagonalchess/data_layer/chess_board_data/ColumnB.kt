package com.example.hexagonalchess.data_layer.chess_board_data

import com.example.hexagonalchess.data_layer.model.tile.Tile
import com.example.hexagonalchess.domain_layer.ChessPieceKeyWord
import com.example.hexagonalchess.domain_layer.TileColor
import com.example.hexagonalchess.domain_layer.TileId
import com.example.hexagonalchess.domain_layer.getChessPieceFromKeyWord

class ColumnB {
    private val b1 = Tile(
        id = TileId.B1,
        color = TileColor.LIGHT,
        topTile = TileId.B2,
        upperRightTile = TileId.C2,
        underRightTile = TileId.C1,
        bottomTile = null,
        underLeftTile = null,
        upperLeftTile = TileId.A1
    )

    private val b2 = Tile(
        id = TileId.B2,
        color = TileColor.MID,
        topTile = TileId.B3,
        upperRightTile = TileId.C3,
        underRightTile = TileId.C2,
        bottomTile = TileId.B1,
        underLeftTile = TileId.A1,
        upperLeftTile = TileId.A2,
        chessPiece = getChessPieceFromKeyWord(ChessPieceKeyWord.WHITE_PAWN)
    )

    private val b3 = Tile(
        id = TileId.B3,
        color = TileColor.DARK,
        topTile = TileId.B4,
        upperRightTile = TileId.C4,
        underRightTile = TileId.C3,
        bottomTile = TileId.B2,
        underLeftTile = TileId.A2,
        upperLeftTile = TileId.A3
    )

    private val b4 = Tile(
        id = TileId.B4,
        color = TileColor.LIGHT,
        topTile = TileId.B5,
        upperRightTile = TileId.C5,
        underRightTile = TileId.C4,
        bottomTile = TileId.B3,
        underLeftTile = TileId.A3,
        upperLeftTile = TileId.A4
    )

    private val b5 = Tile(
        id = TileId.B5,
        color = TileColor.MID,
        topTile = TileId.B6,
        upperRightTile = TileId.C6,
        underRightTile = TileId.C5,
        bottomTile = TileId.B4,
        underLeftTile = TileId.A4,
        upperLeftTile = TileId.A5
    )

    private val b6 = Tile(
        id = TileId.B6,
        color = TileColor.DARK,
        topTile = TileId.B7,
        upperRightTile = TileId.C7,
        underRightTile = TileId.C6,
        bottomTile = TileId.B5,
        underLeftTile = TileId.A5,
        upperLeftTile = TileId.A6
    )

    private val b7 = Tile(
        id = TileId.B7,
        color = TileColor.LIGHT,
        topTile = TileId.B8,
        upperRightTile = TileId.C8,
        underRightTile = TileId.C7,
        bottomTile = TileId.B6,
        underLeftTile = TileId.A6,
        upperLeftTile = TileId.A7
    )

    private val b8 = Tile(
        id = TileId.B8,
        color = TileColor.MID,
        topTile = TileId.B9,
        upperRightTile = TileId.C9,
        underRightTile = TileId.C8,
        bottomTile = TileId.B7,
        underLeftTile = TileId.A7,
        upperLeftTile = TileId.A8,
        chessPiece = getChessPieceFromKeyWord(ChessPieceKeyWord.BLACK_PAWN)
    )

    private val b9 = Tile(
        id = TileId.B9,
        color = TileColor.DARK,
        topTile = null,
        upperRightTile = TileId.C10,
        underRightTile = TileId.C9,
        bottomTile = TileId.B8,
        underLeftTile = TileId.A8,
        upperLeftTile = null
    )

    val columnB = listOf(b9,b8,b7,b6,b5,b4,b3,b2,b1)
}