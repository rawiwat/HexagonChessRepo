package com.example.hexagonalchess.data_layer.chess_board_data.big

import com.example.hexagonalchess.data_layer.model.tile.Tile
import com.example.hexagonalchess.domain_layer.ChessPieceKeyWord
import com.example.hexagonalchess.domain_layer.TileColor
import com.example.hexagonalchess.domain_layer.TileId
import com.example.hexagonalchess.domain_layer.getChessPieceFromKeyWord

class BigColumnG {
    private val g1 = Tile(
        id = TileId.G1,
        color = TileColor.MID,
        topTile = TileId.G2,
        upperRightTile = TileId.H1,
        underRightTile = null,
        bottomTile = null,
        underLeftTile = TileId.F1,
        upperLeftTile = TileId.F2,
        chessPiece = getChessPieceFromKeyWord(ChessPieceKeyWord.WHITE_KING)
    )

    private val g2 = Tile(
        id = TileId.G2,
        color = TileColor.LIGHT,
        topTile = TileId.G3,
        upperRightTile = TileId.H2,
        underRightTile = TileId.H1,
        bottomTile = TileId.G1,
        underLeftTile = TileId.F2,
        upperLeftTile = TileId.F3
    )

    private val g3 = Tile(
        id = TileId.G3,
        color = TileColor.DARK,
        topTile = TileId.G4,
        upperRightTile = TileId.H3,
        underRightTile = TileId.H2,
        bottomTile = TileId.G2,
        underLeftTile = TileId.F3,
        upperLeftTile = TileId.F4,
        chessPiece = getChessPieceFromKeyWord(ChessPieceKeyWord.WHITE_PAWN)
    )

    private val g4 = Tile(
        id = TileId.G4,
        color = TileColor.MID,
        topTile = TileId.G5,
        upperRightTile = TileId.H4,
        underRightTile = TileId.H3,
        bottomTile = TileId.G3,
        underLeftTile = TileId.F4,
        upperLeftTile = TileId.F5
    )

    private val g5 = Tile(
        id = TileId.G5,
        color = TileColor.LIGHT,
        topTile = TileId.G6,
        upperRightTile = TileId.H5,
        underRightTile = TileId.H4,
        bottomTile = TileId.G4,
        underLeftTile = TileId.F5,
        upperLeftTile = TileId.F6
    )

    private val g6 = Tile(
        id = TileId.G6,
        color = TileColor.DARK,
        topTile = TileId.G7,
        upperRightTile = TileId.H6,
        underRightTile = TileId.H5,
        bottomTile = TileId.G5,
        underLeftTile = TileId.F6,
        upperLeftTile = TileId.F7
    )

    private val g7 = Tile(
        id = TileId.G7,
        color = TileColor.MID,
        topTile = TileId.G8,
        upperRightTile = TileId.H7,
        underRightTile = TileId.H6,
        bottomTile = TileId.G6,
        underLeftTile = TileId.F7,
        upperLeftTile = TileId.F8,
        chessPiece = getChessPieceFromKeyWord(ChessPieceKeyWord.BLACK_PAWN)
    )

    private val g8 = Tile(
        id = TileId.G8,
        color = TileColor.LIGHT,
        topTile = TileId.G9,
        upperRightTile = TileId.H8,
        underRightTile = TileId.H7,
        bottomTile = TileId.G7,
        underLeftTile = TileId.F8,
        upperLeftTile = TileId.F9
    )

    private val g9 = Tile(
        id = TileId.G9,
        color = TileColor.DARK,
        topTile = TileId.G10,
        upperRightTile = TileId.H9,
        underRightTile = TileId.H8,
        bottomTile = TileId.G8,
        underLeftTile = TileId.F9,
        upperLeftTile = TileId.F10
    )

    private val g10 = Tile(
        id = TileId.G10,
        color = TileColor.MID,
        topTile = null,
        upperRightTile = null,
        underRightTile = TileId.H9,
        bottomTile = TileId.G9,
        underLeftTile = TileId.F10,
        upperLeftTile = TileId.F11,
        chessPiece = getChessPieceFromKeyWord(ChessPieceKeyWord.BLACK_KING)
    )

    val columnG = listOf(g10,g9,g8,g7,g6,g5,g4,g3,g2,g1)
}