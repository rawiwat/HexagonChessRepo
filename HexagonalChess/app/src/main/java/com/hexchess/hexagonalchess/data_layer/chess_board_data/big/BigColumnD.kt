package com.hexchess.hexagonalchess.data_layer.chess_board_data.big

import com.hexchess.hexagonalchess.data_layer.model.tile.Tile
import com.hexchess.hexagonalchess.domain_layer.ChessPieceKeyWord
import com.hexchess.hexagonalchess.domain_layer.TileColor
import com.hexchess.hexagonalchess.domain_layer.TileId
import com.hexchess.hexagonalchess.domain_layer.getChessPieceFromKeyWord

class BigColumnD {
    private val d1 = Tile(
        id = TileId.D1,
        color = TileColor.DARK,
        topTile = TileId.D2,
        upperRightTile = TileId.E2,
        underRightTile = TileId.E1,
        bottomTile = null,
        underLeftTile = null,
        upperLeftTile = TileId.C1,
        chessPiece = getChessPieceFromKeyWord(ChessPieceKeyWord.WHITE_KNIGHT)
    )

    private val d2 = Tile(
        id = TileId.D2,
        color = TileColor.MID,
        topTile = TileId.D3,
        upperRightTile = TileId.E3,
        underRightTile = TileId.E2,
        bottomTile = TileId.D1,
        underLeftTile = TileId.C1,
        upperLeftTile = TileId.C2
    )

    private val d3 = Tile(
        id = TileId.D3,
        color = TileColor.LIGHT,
        topTile = TileId.D4,
        upperRightTile = TileId.E4,
        underRightTile = TileId.E3,
        bottomTile = TileId.D2,
        underLeftTile = TileId.C2,
        upperLeftTile = TileId.C3,
        chessPiece = getChessPieceFromKeyWord(ChessPieceKeyWord.WHITE_PAWN)
    )

    private val d4 = Tile(
        id = TileId.D4,
        color = TileColor.DARK,
        topTile = TileId.D5,
        upperRightTile = TileId.E5,
        underRightTile = TileId.E4,
        bottomTile = TileId.D3,
        underLeftTile = TileId.C3,
        upperLeftTile = TileId.C4
    )

    private val d5 = Tile(
        id = TileId.D5,
        color = TileColor.MID,
        topTile = TileId.D6,
        upperRightTile = TileId.E6,
        underRightTile = TileId.E5,
        bottomTile = TileId.D4,
        underLeftTile = TileId.C4,
        upperLeftTile = TileId.C5
    )

    private val d6 = Tile(
        id = TileId.D6,
        color = TileColor.LIGHT,
        topTile = TileId.D7,
        upperRightTile = TileId.E7,
        underRightTile = TileId.E6,
        bottomTile = TileId.D5,
        underLeftTile = TileId.C5,
        upperLeftTile = TileId.C6
    )

    private val d7 = Tile(
        id = TileId.D7,
        color = TileColor.DARK,
        topTile = TileId.D8,
        upperRightTile = TileId.E8,
        underRightTile = TileId.E7,
        bottomTile = TileId.D6,
        underLeftTile = TileId.C6,
        upperLeftTile = TileId.C7,
        chessPiece = getChessPieceFromKeyWord(ChessPieceKeyWord.BLACK_PAWN)
    )

    private val d8 = Tile(
        id = TileId.D8,
        color = TileColor.MID,
        topTile = TileId.D9,
        upperRightTile = TileId.E9,
        underRightTile = TileId.E8,
        bottomTile = TileId.D7,
        underLeftTile = TileId.C7,
        upperLeftTile = TileId.C8
    )

    private val d9 = Tile(
        id = TileId.D9,
        color = TileColor.LIGHT,
        topTile = null,
        upperRightTile = TileId.E10,
        underRightTile = TileId.E9,
        bottomTile = TileId.D8,
        underLeftTile = TileId.C8,
        upperLeftTile = null,
        chessPiece = getChessPieceFromKeyWord(ChessPieceKeyWord.BLACK_KNIGHT)
    )

    val columnD = listOf(d9,d8,d7,d6,d5,d4,d3,d2,d1)
}