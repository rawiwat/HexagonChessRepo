package com.example.hexagonalchess.data_layer.chess_board_data.base

import com.example.hexagonalchess.data_layer.model.tile.Tile
import com.example.hexagonalchess.domain_layer.ChessPieceKeyWord
import com.example.hexagonalchess.domain_layer.TileColor
import com.example.hexagonalchess.domain_layer.TileId
import com.example.hexagonalchess.domain_layer.getChessPieceFromKeyWord

class ColumnF {
    private val f1 = Tile(
        id = TileId.F1,
        color = TileColor.DARK,
        topTile = TileId.F2,
        upperRightTile = TileId.G1,
        underRightTile = null,
        bottomTile = null,
        underLeftTile = TileId.E1,
        upperLeftTile = TileId.E2
    )

    private val f2 = Tile(
        id = TileId.F2,
        color = TileColor.LIGHT,
        topTile = TileId.F3,
        upperRightTile = TileId.G2,
        underRightTile = TileId.G1,
        bottomTile = TileId.F1,
        underLeftTile = TileId.E2,
        upperLeftTile = TileId.E3,
        chessPiece = getChessPieceFromKeyWord(ChessPieceKeyWord.WHITE_BISHOP)
    )

    private val f3 = Tile(
        id = TileId.F3,
        color = TileColor.MID,
        topTile = TileId.F4,
        upperRightTile = TileId.G3,
        underRightTile = TileId.G2,
        bottomTile = TileId.F2,
        underLeftTile = TileId.E3,
        upperLeftTile = TileId.E4
    )

    private val f4 = Tile(
        id = TileId.F4,
        color = TileColor.DARK,
        topTile = TileId.F5,
        upperRightTile = TileId.G4,
        underRightTile = TileId.G3,
        bottomTile = TileId.F3,
        underLeftTile = TileId.E4,
        upperLeftTile = TileId.E5,
        chessPiece = getChessPieceFromKeyWord(ChessPieceKeyWord.WHITE_PAWN)
    )

    private val f5 = Tile(
        id = TileId.F5,
        color = TileColor.LIGHT,
        topTile = TileId.F6,
        upperRightTile = TileId.G5,
        underRightTile = TileId.G4,
        bottomTile = TileId.F4,
        underLeftTile = TileId.E5,
        upperLeftTile = TileId.E6
    )

    private val f6 = Tile(
        id = TileId.F6,
        color = TileColor.MID,
        topTile = TileId.F7,
        upperRightTile = TileId.G6,
        underRightTile = TileId.G5,
        bottomTile = TileId.F5,
        underLeftTile = TileId.E6,
        upperLeftTile = TileId.E7
    )

    private val f7 = Tile(
        id = TileId.F7,
        color = TileColor.DARK,
        topTile = TileId.F8,
        upperRightTile = TileId.G7,
        underRightTile = TileId.G6,
        bottomTile = TileId.F6,
        underLeftTile = TileId.E7,
        upperLeftTile = TileId.E8
    )

    private val f8 = Tile(
        id = TileId.F8,
        color = TileColor.LIGHT,
        topTile = TileId.F9,
        upperRightTile = TileId.G8,
        underRightTile = TileId.G7,
        bottomTile = TileId.F7,
        underLeftTile = TileId.E8,
        upperLeftTile = TileId.E9,
        chessPiece = getChessPieceFromKeyWord(ChessPieceKeyWord.BLACK_PAWN)
    )

    private val f9 = Tile(
        id = TileId.F9,
        color = TileColor.MID,
        topTile = TileId.F10,
        upperRightTile = TileId.G9,
        underRightTile = TileId.G8,
        bottomTile = TileId.F8,
        underLeftTile = TileId.E9,
        upperLeftTile = TileId.E10
    )

    private val f10 = Tile(
        id = TileId.F10,
        color = TileColor.DARK,
        topTile = TileId.F11,
        upperRightTile = TileId.G10,
        underRightTile = TileId.G9,
        bottomTile = TileId.F9,
        underLeftTile = TileId.E10,
        upperLeftTile = TileId.E11,
        chessPiece = getChessPieceFromKeyWord(ChessPieceKeyWord.BLACK_BISHOP)
    )

    private val f11 = Tile(
        id = TileId.F11,
        color = TileColor.LIGHT,
        topTile = null,
        upperRightTile = null,
        underRightTile = TileId.G10,
        bottomTile = TileId.F10,
        underLeftTile = TileId.E11,
        upperLeftTile = TileId.E12
    )

    val columnF = listOf(f11,f10,f9,f8,f7,f6,f5,f4,f3,f2,f1)
}