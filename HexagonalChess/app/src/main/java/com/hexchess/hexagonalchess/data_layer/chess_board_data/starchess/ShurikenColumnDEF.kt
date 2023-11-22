package com.hexchess.hexagonalchess.data_layer.chess_board_data.starchess

import com.hexchess.hexagonalchess.data_layer.model.tile.Tile
import com.hexchess.hexagonalchess.domain_layer.ChessPieceKeyWord
import com.hexchess.hexagonalchess.domain_layer.TileColor
import com.hexchess.hexagonalchess.domain_layer.TileId
import com.hexchess.hexagonalchess.domain_layer.getChessPieceFromKeyWord

class ShurikenColumnDEF {
    private val d1 = Tile(
        id = TileId.D1,
        color = TileColor.DARK,
        topTile = TileId.D2,
        upperRightTile = TileId.E1,
        underRightTile = null,
        bottomTile = null,
        underLeftTile = TileId.C1,
        upperLeftTile = TileId.C2,
        chessPiece = getChessPieceFromKeyWord(ChessPieceKeyWord.WHITE_QUEEN)
    )

    private val d2 = Tile(
        id = TileId.D2,
        color = TileColor.LIGHT,
        topTile = TileId.D3,
        upperRightTile = TileId.E2,
        underRightTile = TileId.E1,
        bottomTile = TileId.D1,
        underLeftTile = TileId.C2,
        upperLeftTile = TileId.C3,
        chessPiece = getChessPieceFromKeyWord(ChessPieceKeyWord.WHITE_PAWN)
    )

    private val d3 = Tile(
        id = TileId.D3,
        color = TileColor.MID,
        topTile = TileId.D4,
        upperRightTile = TileId.E3,
        underRightTile = TileId.E2,
        bottomTile = TileId.D2,
        underLeftTile = TileId.C3,
        upperLeftTile = TileId.C4,
        chessPiece = null
    )

    val d4 = Tile(
        id = TileId.D4,
        color = TileColor.DARK,
        topTile = TileId.D5,
        upperRightTile = TileId.E4,
        underRightTile = TileId.E3,
        bottomTile = TileId.D3,
        underLeftTile = TileId.C4,
        upperLeftTile = TileId.C5,
        chessPiece = null
    )

    private val d5 = Tile(
        id = TileId.D5,
        color = TileColor.LIGHT,
        topTile = TileId.D6,
        upperRightTile = TileId.E5,
        underRightTile = TileId.E4,
        bottomTile = TileId.D4,
        underLeftTile = TileId.C5,
        upperLeftTile = TileId.C6,
        chessPiece = getChessPieceFromKeyWord(ChessPieceKeyWord.BLACK_PAWN)
    )

    private val d6 = Tile(
        id = TileId.D6,
        color = TileColor.MID,
        topTile = null,
        upperRightTile = null,
        underRightTile = TileId.E5,
        bottomTile = TileId.D5,
        underLeftTile = TileId.C6,
        upperLeftTile = TileId.C7,
        chessPiece = getChessPieceFromKeyWord(ChessPieceKeyWord.BLACK_KNIGHT)
    )

    private val e1 = Tile(
        id = TileId.E1,
        color = TileColor.MID,
        topTile = TileId.E2,
        upperRightTile = TileId.F2,
        underRightTile = TileId.F1,
        bottomTile = null,
        underLeftTile = TileId.D1,
        upperLeftTile = TileId.D2,
        chessPiece = getChessPieceFromKeyWord(ChessPieceKeyWord.WHITE_BISHOP)
    )

    private val e2 = Tile(
        id = TileId.E2,
        color = TileColor.DARK,
        topTile = TileId.E3,
        upperRightTile = TileId.F3,
        underRightTile = TileId.F2,
        bottomTile = TileId.E1,
        underLeftTile = TileId.D2,
        upperLeftTile = TileId.D3,
        chessPiece = getChessPieceFromKeyWord(ChessPieceKeyWord.WHITE_PAWN)
    )

    private val e3 = Tile(
        id = TileId.E3,
        color = TileColor.LIGHT,
        topTile = TileId.E4,
        upperRightTile = TileId.F4,
        underRightTile = TileId.F3,
        bottomTile = TileId.E2,
        underLeftTile = TileId.D3,
        upperLeftTile = TileId.D4,
        chessPiece = null
    )

    private val e4 = Tile(
        id = TileId.E4,
        color = TileColor.MID,
        topTile = TileId.E5,
        upperRightTile = TileId.F5,
        underRightTile = TileId.F4,
        bottomTile = TileId.E3,
        underLeftTile = TileId.D4,
        upperLeftTile = TileId.D5,
        chessPiece = getChessPieceFromKeyWord(ChessPieceKeyWord.BLACK_PAWN)
    )

    private val e5 = Tile(
        id = TileId.E5,
        color = TileColor.DARK,
        topTile = null,
        upperRightTile = TileId.F6,
        underRightTile = TileId.F5,
        bottomTile = TileId.E4,
        underLeftTile = TileId.D5,
        upperLeftTile = TileId.D6,
        chessPiece = getChessPieceFromKeyWord(ChessPieceKeyWord.BLACK_QUEEN)
    )

    val f1 = Tile(
        id = TileId.F1,
        color = TileColor.DARK,
        topTile = TileId.F2,
        upperRightTile = TileId.G2,
        underRightTile = TileId.G1,
        bottomTile = null,
        underLeftTile = null,
        upperLeftTile = TileId.E1,
        chessPiece = getChessPieceFromKeyWord(ChessPieceKeyWord.WHITE_KNIGHT)
    )

    val f2 = Tile(
        id = TileId.F2,
        color = TileColor.LIGHT,
        topTile = TileId.F3,
        upperRightTile = TileId.G3,
        underRightTile = TileId.G2,
        bottomTile = TileId.F1,
        underLeftTile = TileId.E1,
        upperLeftTile = TileId.E2,
        chessPiece = getChessPieceFromKeyWord(ChessPieceKeyWord.WHITE_PAWN)
    )

    private val f3 = Tile(
        id = TileId.F3,
        color = TileColor.MID,
        topTile = TileId.F4,
        upperRightTile = TileId.G4,
        underRightTile = TileId.G3,
        bottomTile = TileId.F2,
        underLeftTile = TileId.E2,
        upperLeftTile = TileId.E3,
        chessPiece = null
    )

    private val f4 = Tile(
        id = TileId.F4,
        color = TileColor.DARK,
        topTile = TileId.F5,
        upperRightTile = TileId.G5,
        underRightTile = TileId.G4,
        bottomTile = TileId.F3,
        underLeftTile = TileId.E3,
        upperLeftTile = TileId.E4,
        chessPiece = null
    )

    private val f5 = Tile(
        id = TileId.F5,
        color = TileColor.LIGHT,
        topTile = TileId.F6,
        upperRightTile = TileId.G6,
        underRightTile = TileId.G5,
        bottomTile = TileId.F4,
        underLeftTile = TileId.E4,
        upperLeftTile = TileId.E5,
        chessPiece = getChessPieceFromKeyWord(ChessPieceKeyWord.BLACK_PAWN)
    )

    private val f6 = Tile(
        id = TileId.F6,
        color = TileColor.MID,
        topTile = null,
        upperRightTile = TileId.G7,
        underRightTile = TileId.G6,
        bottomTile = TileId.F5,
        underLeftTile = TileId.E5,
        upperLeftTile = null,
        chessPiece = getChessPieceFromKeyWord(ChessPieceKeyWord.BLACK_BISHOP)
    )

    val columnDEF = listOf(d6,d5,d4,d3,d2,d1,e5,e4,e3,e2,e1,f6,f5,f4,f3,f2,f1)
}