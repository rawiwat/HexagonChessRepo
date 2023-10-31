package com.example.hexagonalchess.data_layer.chess_board_data.big

import com.example.hexagonalchess.data_layer.model.tile.Tile
import com.example.hexagonalchess.domain_layer.ChessPieceKeyWord
import com.example.hexagonalchess.domain_layer.TileColor
import com.example.hexagonalchess.domain_layer.TileId
import com.example.hexagonalchess.domain_layer.getChessPieceFromKeyWord

class BigColumnJ {
    private val j1 = Tile(
        id = TileId.J1,
        color = TileColor.MID,
        topTile = TileId.J2,
        upperRightTile = TileId.K1,
        underRightTile = null,
        bottomTile = null,
        underLeftTile = TileId.I1,
        upperLeftTile = TileId.I2,
        chessPiece = getChessPieceFromKeyWord(ChessPieceKeyWord.WHITE_PAWN)
    )

    private val j2 = Tile(
        id = TileId.J2,
        color = TileColor.LIGHT,
        topTile = TileId.J3,
        upperRightTile = TileId.K2,
        underRightTile = TileId.K1,
        bottomTile = TileId.J1,
        underLeftTile = TileId.I2,
        upperLeftTile = TileId.I3
    )

    private val j3 = Tile(
        id = TileId.J3,
        color = TileColor.DARK,
        topTile = TileId.J4,
        upperRightTile = TileId.K3,
        underRightTile = TileId.K2,
        bottomTile = TileId.J2,
        underLeftTile = TileId.I3,
        upperLeftTile = TileId.I4
    )

    private val j4 = Tile(
        id = TileId.J4,
        color = TileColor.MID,
        topTile = TileId.J5,
        upperRightTile = TileId.K4,
        underRightTile = TileId.K3,
        bottomTile = TileId.J3,
        underLeftTile = TileId.I4,
        upperLeftTile = TileId.I5
    )

    private val j5 = Tile(
        id = TileId.J5,
        color = TileColor.LIGHT,
        topTile = TileId.J6,
        upperRightTile = TileId.K5,
        underRightTile = TileId.K4,
        bottomTile = TileId.J4,
        underLeftTile = TileId.I5,
        upperLeftTile = TileId.I6
    )

    private val j6 = Tile(
        id = TileId.J6,
        color = TileColor.DARK,
        topTile = TileId.J7,
        upperRightTile = TileId.K6,
        underRightTile = TileId.K5,
        bottomTile = TileId.J5,
        underLeftTile = TileId.I6,
        upperLeftTile = TileId.I7
    )

    private val j7 = Tile(
        id = TileId.J7,
        color = TileColor.MID,
        topTile = null,
        upperRightTile = null,
        underRightTile = TileId.K6,
        bottomTile = TileId.J6,
        underLeftTile = TileId.I7,
        upperLeftTile = TileId.I8,
        chessPiece = getChessPieceFromKeyWord(ChessPieceKeyWord.BLACK_PAWN)
    )

    val columnJ = listOf(j1,j2,j3,j4,j5,j6,j7)
}