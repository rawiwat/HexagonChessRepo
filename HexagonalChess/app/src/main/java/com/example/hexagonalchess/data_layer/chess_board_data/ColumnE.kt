package com.example.hexagonalchess.data_layer.chess_board_data

import com.example.hexagonalchess.data_layer.model.tile.Tile
import com.example.hexagonalchess.domain_layer.ChessPieceKeyWord
import com.example.hexagonalchess.domain_layer.TileColor
import com.example.hexagonalchess.domain_layer.TileId
import com.example.hexagonalchess.domain_layer.getChessPieceFromKeyWord

class ColumnE {

    private val e1 = Tile(
        id = TileId.E1,
        color = TileColor.LIGHT,
        topTile = TileId.E2,
        upperRightTile = TileId.F1,
        underRightTile = null,
        bottomTile = null,
        underLeftTile = null,
        upperLeftTile = TileId.D1,
        chessPiece = getChessPieceFromKeyWord(ChessPieceKeyWord.WHITE_KING)
    )

    private val e2 = Tile(
        id = TileId.E2,
        color = TileColor.MID,
        topTile = TileId.E3,
        upperRightTile = TileId.F2,
        underRightTile = TileId.F1,
        bottomTile = TileId.E1,
        underLeftTile = TileId.D1,
        upperLeftTile = TileId.D2,
        chessPiece = getChessPieceFromKeyWord(ChessPieceKeyWord.WHITE_BISHOP)
    )

    private val e3 = Tile(
        id = TileId.E3,
        color = TileColor.DARK,
        topTile = TileId.E4,
        upperRightTile = TileId.F3,
        underRightTile = TileId.F2,
        bottomTile = TileId.E2,
        underLeftTile = TileId.D2,
        upperLeftTile = TileId.D3,
        chessPiece = getChessPieceFromKeyWord(ChessPieceKeyWord.WHITE_BISHOP)
    )

    private val e4 = Tile(
        id = TileId.E4,
        color = TileColor.LIGHT,
        topTile = TileId.E5,
        upperRightTile = TileId.F4,
        underRightTile = TileId.F3,
        bottomTile = TileId.E3,
        underLeftTile = TileId.D3,
        upperLeftTile = TileId.D4
    )

    private val e5 = Tile(
        id = TileId.E5,
        color = TileColor.MID,
        topTile = TileId.E6,
        upperRightTile = TileId.F5,
        underRightTile = TileId.F4,
        bottomTile = TileId.E4,
        underLeftTile = TileId.D4,
        upperLeftTile = TileId.D5,
        chessPiece = getChessPieceFromKeyWord(ChessPieceKeyWord.WHITE_PAWN)
    )

    private val e6 = Tile(
        id = TileId.E6,
        color = TileColor.DARK,
        topTile = TileId.E7,
        upperRightTile = TileId.F6,
        underRightTile = TileId.F5,
        bottomTile = TileId.E5,
        underLeftTile = TileId.D5,
        upperLeftTile = TileId.D6
    )

    private val e7 = Tile(
        id = TileId.E7,
        color = TileColor.LIGHT,
        topTile = TileId.E8,
        upperRightTile = TileId.F7,
        underRightTile = TileId.F6,
        bottomTile = TileId.E6,
        underLeftTile = TileId.D6,
        upperLeftTile = TileId.D7
    )

    private val e8 = Tile(
        id = TileId.E8,
        color = TileColor.MID,
        topTile = TileId.E9,
        upperRightTile = TileId.F8,
        underRightTile = TileId.F7,
        bottomTile = TileId.E7,
        underLeftTile = TileId.D7,
        upperLeftTile = TileId.D8,
        chessPiece = getChessPieceFromKeyWord(ChessPieceKeyWord.BLACK_PAWN)
    )

    private val e9 = Tile(
        id = TileId.E9,
        color = TileColor.DARK,
        topTile = TileId.E10,
        upperRightTile = TileId.F9,
        underRightTile = TileId.F8,
        bottomTile = TileId.E8,
        underLeftTile = TileId.D8,
        upperLeftTile = TileId.D9
    )

    private val e10 = Tile(
        id = TileId.E10,
        color = TileColor.LIGHT,
        topTile = TileId.E11,
        upperRightTile = TileId.F10,
        underRightTile = TileId.F9,
        bottomTile = TileId.E9,
        underLeftTile = TileId.D9,
        upperLeftTile = TileId.D10,
        chessPiece = getChessPieceFromKeyWord(ChessPieceKeyWord.BLACK_BISHOP)
    )

    private val e11 = Tile(
        id = TileId.E11,
        color = TileColor.MID,
        topTile = TileId.E12,
        upperRightTile = TileId.F11,
        underRightTile = TileId.F10,
        bottomTile = TileId.E10,
        underLeftTile = TileId.D10,
        upperLeftTile = TileId.D11,
        chessPiece = getChessPieceFromKeyWord(ChessPieceKeyWord.BLACK_BISHOP)
    )

    private val e12 = Tile(
        id = TileId.E12,
        color = TileColor.DARK,
        topTile = null,
        upperRightTile = null,
        underRightTile = TileId.F11,
        bottomTile = TileId.E11,
        underLeftTile = TileId.F11,
        upperLeftTile = null,
        chessPiece = getChessPieceFromKeyWord(ChessPieceKeyWord.BLACK_KING)
    )

    val columnE = listOf(e12,e11,e10,e9,e8,e7,e6,e5,e4,e3,e2,e1)
}