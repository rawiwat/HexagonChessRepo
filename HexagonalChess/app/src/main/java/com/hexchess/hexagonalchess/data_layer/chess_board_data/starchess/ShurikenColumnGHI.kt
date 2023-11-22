package com.hexchess.hexagonalchess.data_layer.chess_board_data.starchess

import com.hexchess.hexagonalchess.data_layer.model.tile.Tile
import com.hexchess.hexagonalchess.domain_layer.ChessPieceKeyWord
import com.hexchess.hexagonalchess.domain_layer.TileColor
import com.hexchess.hexagonalchess.domain_layer.TileId
import com.hexchess.hexagonalchess.domain_layer.getChessPieceFromKeyWord

class ShurikenColumnGHI {

    private val g1 = Tile(
        id = TileId.G1,
        color = TileColor.LIGHT,
        topTile = TileId.G2,
        upperRightTile = null,
        underRightTile = null,
        bottomTile = null,
        underLeftTile = null,
        upperLeftTile = TileId.F1,
        chessPiece = getChessPieceFromKeyWord(ChessPieceKeyWord.WHITE_KING)
    )

    private val g2 = Tile(
        id = TileId.G2,
        color = TileColor.MID,
        topTile = TileId.G3,
        upperRightTile = null,
        underRightTile = null,
        bottomTile = TileId.G1,
        underLeftTile = TileId.F1,
        upperLeftTile = TileId.F2,
        chessPiece = getChessPieceFromKeyWord(ChessPieceKeyWord.WHITE_PAWN)
    )

    private val g3 = Tile(
        id = TileId.G3,
        color = TileColor.DARK,
        topTile = TileId.G4,
        upperRightTile = TileId.H1,
        underRightTile = null,
        bottomTile = TileId.G2,
        underLeftTile = TileId.F2,
        upperLeftTile = TileId.F3,
        chessPiece = null
    )

    private val g4 = Tile(
        id = TileId.G4,
        color = TileColor.LIGHT,
        topTile = TileId.G5,
        upperRightTile = TileId.H2,
        underRightTile = TileId.H1,
        bottomTile = TileId.G3,
        underLeftTile = TileId.F3,
        upperLeftTile = TileId.F4,
        chessPiece = null
    )

    private val g5 = Tile(
        id = TileId.G5,
        color = TileColor.MID,
        topTile = TileId.G6,
        upperRightTile = null,
        underRightTile = TileId.H2,
        bottomTile = TileId.G4,
        underLeftTile = TileId.F4,
        upperLeftTile = TileId.F5,
        chessPiece = null
    )


    private val g6 = Tile(
        id = TileId.G6,
        color = TileColor.DARK,
        topTile = TileId.G7,
        upperRightTile = null,
        underRightTile = null,
        bottomTile = TileId.G5,
        underLeftTile = TileId.F5,
        upperLeftTile = TileId.F6,
        chessPiece = getChessPieceFromKeyWord(ChessPieceKeyWord.BLACK_PAWN)
    )

    private val g7 = Tile(
        id = TileId.G7,
        color = TileColor.LIGHT,
        topTile = null,
        upperRightTile = null,
        underRightTile = null,
        bottomTile = TileId.G6,
        underLeftTile = TileId.F6,
        upperLeftTile = null,
        chessPiece = getChessPieceFromKeyWord(ChessPieceKeyWord.BLACK_ROOK)
    )

    private val h1 = Tile(
        id = TileId.H1,
        color = TileColor.MID,
        topTile = TileId.H2,
        upperRightTile = TileId.I1,
        underRightTile = null,
        bottomTile = null,
        underLeftTile = TileId.G3,
        upperLeftTile = TileId.G4,
        chessPiece = null
    )

    private val h2 = Tile(
        id = TileId.H2,
        color = TileColor.DARK,
        topTile = null,
        upperRightTile = null,
        underRightTile = TileId.I1,
        bottomTile = TileId.H1,
        underLeftTile = TileId.G4,
        upperLeftTile = TileId.G5,
        chessPiece = null
    )


    private val i1 = Tile(
        id = TileId.I1,
        color = TileColor.LIGHT,
        topTile = null,
        upperRightTile = null,
        underRightTile = null,
        bottomTile = null,
        underLeftTile = TileId.H1,
        upperLeftTile = TileId.H2,
        chessPiece = null
    )

    val columnGHI = listOf(g7,g6,g5,g4,g3,g2,g1,h2,h1,i1)
}