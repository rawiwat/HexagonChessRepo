package com.hexchess.hexagonalchess.data_layer.chess_board_data.shafran

import com.hexchess.hexagonalchess.data_layer.model.tile.Tile
import com.hexchess.hexagonalchess.domain_layer.ChessPieceKeyWord
import com.hexchess.hexagonalchess.domain_layer.TileColor
import com.hexchess.hexagonalchess.domain_layer.TileId
import com.hexchess.hexagonalchess.domain_layer.getChessPieceFromKeyWord

class ShafranColumnC {
    private val c1 = Tile(
        id = TileId.C1,
        color = TileColor.MID,
        topTile = TileId.C2,
        upperRightTile = TileId.D2,
        underRightTile = TileId.D1,
        bottomTile = null,
        underLeftTile = null,
        upperLeftTile = TileId.B1,
        chessPiece = getChessPieceFromKeyWord(ChessPieceKeyWord.WHITE_KNIGHT)
    )

    private val c2 = Tile(
        id = TileId.C2,
        color = TileColor.DARK,
        topTile = TileId.C3,
        upperRightTile = TileId.D3,
        underRightTile = TileId.D2,
        bottomTile = TileId.C1,
        underLeftTile = TileId.B1,
        upperLeftTile = TileId.B2,
        chessPiece = getChessPieceFromKeyWord(ChessPieceKeyWord.WHITE_PAWN)
    )

    private val c3 = Tile(
        id = TileId.C3,
        color = TileColor.LIGHT,
        topTile = TileId.C4,
        upperRightTile = TileId.D4,
        underRightTile = TileId.D3,
        bottomTile = TileId.C2,
        underLeftTile = TileId.B2,
        upperLeftTile = TileId.B3
    )

    private val c4 = Tile(
        id = TileId.C4,
        color = TileColor.MID,
        topTile = TileId.C5,
        upperRightTile = TileId.D5,
        underRightTile = TileId.D4,
        bottomTile = TileId.C3,
        underLeftTile = TileId.B3,
        upperLeftTile = TileId.B4
    )

    private val c5 = Tile(
        id = TileId.C5,
        color = TileColor.DARK,
        topTile = TileId.C6,
        upperRightTile = TileId.D6,
        underRightTile = TileId.D5,
        bottomTile = TileId.C4,
        underLeftTile = TileId.B4,
        upperLeftTile = TileId.B5
    )

    private val c6 = Tile(
        id = TileId.C6,
        color = TileColor.LIGHT,
        topTile = TileId.C7,
        upperRightTile = TileId.D7,
        underRightTile = TileId.D6,
        bottomTile = TileId.C5,
        underLeftTile = TileId.B5,
        upperLeftTile = TileId.B6
    )

    private val c7 = Tile(
        id = TileId.C7,
        color = TileColor.MID,
        topTile = TileId.C8,
        upperRightTile = TileId.D8,
        underRightTile = TileId.D7,
        bottomTile = TileId.C6,
        underLeftTile = TileId.B6,
        upperLeftTile = TileId.B7,
        chessPiece = getChessPieceFromKeyWord(ChessPieceKeyWord.BLACK_PAWN)
    )

    private val c8 = Tile(
        id = TileId.C8,
        color = TileColor.DARK,
        topTile = null,
        upperRightTile = TileId.D9,
        underRightTile = TileId.D8,
        bottomTile = TileId.C7,
        underLeftTile = TileId.B7,
        upperLeftTile = null,
        chessPiece = getChessPieceFromKeyWord(ChessPieceKeyWord.BLACK_KNIGHT)
    )

    val columnC = listOf(c8,c7,c6,c5,c4,c3,c2,c1)
}