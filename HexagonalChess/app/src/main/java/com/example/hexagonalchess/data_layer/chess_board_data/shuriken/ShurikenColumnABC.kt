package com.example.hexagonalchess.data_layer.chess_board_data.shuriken

import com.example.hexagonalchess.data_layer.model.tile.Tile
import com.example.hexagonalchess.domain_layer.ChessPieceKeyWord
import com.example.hexagonalchess.domain_layer.TileColor
import com.example.hexagonalchess.domain_layer.TileId
import com.example.hexagonalchess.domain_layer.getChessPieceFromKeyWord

class ShurikenColumnABC {
    private val a1 = Tile(
        id = TileId.A1,
        color = TileColor.LIGHT,
        topTile = null,
        upperRightTile = TileId.B2,
        underRightTile = TileId.B1,
        bottomTile = null,
        underLeftTile = null,
        upperLeftTile = null,
    )

    private val b1 = Tile(
        id = TileId.B1,
        color = TileColor.MID,
        topTile = TileId.B2,
        upperRightTile = TileId.C4,
        underRightTile = TileId.C3,
        bottomTile = null,
        underLeftTile = null,
        upperLeftTile = TileId.A1,
    )

    private val b2 = Tile(
        id = TileId.B2,
        color = TileColor.DARK,
        topTile = TileId.B2,
        upperRightTile = TileId.C4,
        underRightTile = TileId.C3,
        bottomTile = null,
        underLeftTile = null,
        upperLeftTile = TileId.A1,
    )

    private val c1 = Tile(
        id = TileId.C1,
        color = TileColor.LIGHT,
        topTile = TileId.C2,
        upperRightTile = TileId.D1,
        underRightTile = null,
        bottomTile = null,
        underLeftTile = null,
        upperLeftTile = null,
        chessPiece = getChessPieceFromKeyWord(ChessPieceKeyWord.WHITE_ROOK)
    )

    private val c2 = Tile(
        id = TileId.C2,
        color = TileColor.MID,
        topTile = TileId.C3,
        upperRightTile = TileId.D2,
        underRightTile = TileId.D1,
        bottomTile = TileId.C1,
        underLeftTile = null,
        upperLeftTile = null,
        chessPiece = getChessPieceFromKeyWord(ChessPieceKeyWord.WHITE_PAWN)
    )

    private val c3 = Tile(
        id = TileId.C3,
        color = TileColor.DARK,
        topTile = TileId.C4,
        upperRightTile = TileId.D3,
        underRightTile = TileId.D2,
        bottomTile = TileId.C2,
        underLeftTile = null,
        upperLeftTile = TileId.B1,
        chessPiece = null
    )

    private val c4 = Tile(
        id = TileId.C4,
        color = TileColor.LIGHT,
        topTile = TileId.C5,
        upperRightTile = TileId.D4,
        underRightTile = TileId.D3,
        bottomTile = TileId.C3,
        underLeftTile = TileId.B1,
        upperLeftTile = TileId.B2,
        chessPiece = null
    )

    private val c5 = Tile(
        id = TileId.C5,
        color = TileColor.MID,
        topTile = TileId.C6,
        upperRightTile = TileId.D5,
        underRightTile = TileId.D4,
        bottomTile = TileId.C4,
        underLeftTile = TileId.B2,
        upperLeftTile = null,
        chessPiece = null
    )

    private val c6 = Tile(
        id = TileId.C6,
        color = TileColor.DARK,
        topTile = TileId.C7,
        upperRightTile = TileId.D6,
        underRightTile = TileId.D5,
        bottomTile = TileId.C5,
        underLeftTile = null,
        upperLeftTile = null,
        chessPiece = getChessPieceFromKeyWord(ChessPieceKeyWord.BLACK_PAWN)
    )

    private val c7 = Tile(
        id = TileId.C6,
        color = TileColor.LIGHT,
        topTile = null,
        upperRightTile = null,
        underRightTile = TileId.D6,
        bottomTile = TileId.C6,
        underLeftTile = null,
        upperLeftTile = null,
        chessPiece = getChessPieceFromKeyWord(ChessPieceKeyWord.BLACK_KING)
    )

    val columnABC = listOf(a1, b1, b2, c7, c6, c5, c4, c3, c2, c1)
}