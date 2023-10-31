package com.example.hexagonalchess.data_layer.chess_board_data.big

import com.example.hexagonalchess.data_layer.model.tile.Tile
import com.example.hexagonalchess.domain_layer.ChessPieceKeyWord
import com.example.hexagonalchess.domain_layer.TileColor
import com.example.hexagonalchess.domain_layer.TileId
import com.example.hexagonalchess.domain_layer.getChessPieceFromKeyWord

class BigColumnH {
    private val h1 = Tile(
        id = TileId.H1,
        color = TileColor.DARK,
        topTile = TileId.H2,
        upperRightTile = TileId.I1,
        underRightTile = null,
        bottomTile = null,
        underLeftTile = TileId.G1,
        upperLeftTile = TileId.G2,
        chessPiece = getChessPieceFromKeyWord(ChessPieceKeyWord.WHITE_KNIGHT)
    )

    private val h2 = Tile(
        id = TileId.H2,
        color = TileColor.MID,
        topTile = TileId.H3,
        upperRightTile = TileId.I2,
        underRightTile = TileId.I1,
        bottomTile = TileId.H1,
        underLeftTile = TileId.G2,
        upperLeftTile = TileId.G3
    )

    private val h3 = Tile(
        id = TileId.H3,
        color = TileColor.LIGHT,
        topTile = TileId.H4,
        upperRightTile = TileId.I3,
        underRightTile = TileId.I2,
        bottomTile = TileId.H2,
        underLeftTile = TileId.G3,
        upperLeftTile = TileId.G4,
        chessPiece = getChessPieceFromKeyWord(ChessPieceKeyWord.WHITE_PAWN)
    )

    private val h4 = Tile(
        id = TileId.H4,
        color = TileColor.DARK,
        topTile = TileId.H5,
        upperRightTile = TileId.I4,
        underRightTile = TileId.I3,
        bottomTile = TileId.H3,
        underLeftTile = TileId.G4,
        upperLeftTile = TileId.G5
    )

    private val h5 = Tile(
        id = TileId.H5,
        color = TileColor.MID,
        topTile = TileId.H6,
        upperRightTile = TileId.I5,
        underRightTile = TileId.I4,
        bottomTile = TileId.H4,
        underLeftTile = TileId.G5,
        upperLeftTile = TileId.G6
    )

    private val h6 = Tile(
        id = TileId.H6,
        color = TileColor.LIGHT,
        topTile = TileId.H7,
        upperRightTile = TileId.I6,
        underRightTile = TileId.I5,
        bottomTile = TileId.H5,
        underLeftTile = TileId.G6,
        upperLeftTile = TileId.G7
    )

    private val h7 = Tile(
        id = TileId.H7,
        color = TileColor.DARK,
        topTile = TileId.H8,
        upperRightTile = TileId.I7,
        underRightTile = TileId.I6,
        bottomTile = TileId.H6,
        underLeftTile = TileId.G7,
        upperLeftTile = TileId.G8,
        chessPiece = getChessPieceFromKeyWord(ChessPieceKeyWord.BLACK_PAWN)
    )

    private val h8 = Tile(
        id = TileId.H8,
        color = TileColor.MID,
        topTile = TileId.H9,
        upperRightTile = TileId.I8,
        underRightTile = TileId.I7,
        bottomTile = TileId.H7,
        underLeftTile = TileId.G8,
        upperLeftTile = TileId.G9
    )

    private val h9 = Tile(
        id = TileId.H9,
        color = TileColor.LIGHT,
        topTile = null,
        upperRightTile = null,
        underRightTile = TileId.I8,
        bottomTile = TileId.H8,
        underLeftTile = TileId.G9,
        upperLeftTile = TileId.G10,
        chessPiece = getChessPieceFromKeyWord(ChessPieceKeyWord.BLACK_KNIGHT)
    )

    val columnH = listOf(h9,h8,h7,h6,h5,h4,h3,h2,h1)

}