package com.hexchess.hexagonalchess.data_layer.chess_board_data.base

import com.hexchess.hexagonalchess.data_layer.model.tile.Tile
import com.hexchess.hexagonalchess.domain_layer.ChessPieceKeyWord
import com.hexchess.hexagonalchess.domain_layer.TileColor
import com.hexchess.hexagonalchess.domain_layer.TileId
import com.hexchess.hexagonalchess.domain_layer.getChessPieceFromKeyWord

class ColumnI {

    private val i1 = Tile(
        id = TileId.I1,
        color = TileColor.DARK,
        topTile = TileId.I2,
        upperRightTile = null,
        underRightTile = null,
        bottomTile = null,
        underLeftTile = TileId.H1,
        upperLeftTile = TileId.H2,
        chessPiece = getChessPieceFromKeyWord(ChessPieceKeyWord.WHITE_PAWN)
    )

    private val i2 = Tile(
        id = TileId.I2,
        color = TileColor.LIGHT,
        topTile = TileId.I3,
        upperRightTile = null,
        underRightTile = null,
        bottomTile = TileId.I1,
        underLeftTile = TileId.H2,
        upperLeftTile = TileId.H3
    )

    private val i3 = Tile(
        id = TileId.I3,
        color = TileColor.MID,
        topTile = TileId.I4,
        upperRightTile = null,
        underRightTile = null,
        bottomTile = TileId.I2,
        underLeftTile = TileId.H3,
        upperLeftTile = TileId.H4
    )

    private val i4 = Tile(
        id = TileId.I4,
        color = TileColor.DARK,
        topTile = TileId.I5,
        upperRightTile = null,
        underRightTile = null,
        bottomTile = TileId.I3,
        underLeftTile = TileId.H4,
        upperLeftTile = TileId.H5
    )

    private val i5 = Tile(
        id = TileId.I5,
        color = TileColor.LIGHT,
        topTile = TileId.I6,
        upperRightTile = null,
        underRightTile = null,
        bottomTile = TileId.I4,
        underLeftTile = TileId.H5,
        upperLeftTile = TileId.H6
    )

    private val i6 = Tile(
        id = TileId.I6,
        color = TileColor.MID,
        topTile = TileId.I7,
        upperRightTile = null,
        underRightTile = null,
        bottomTile = TileId.I5,
        underLeftTile = TileId.H6,
        upperLeftTile = TileId.H7,
    )

    private val i7 = Tile(
        id = TileId.I7,
        color = TileColor.DARK,
        topTile = TileId.I8,
        upperRightTile = null,
        underRightTile = null,
        bottomTile = TileId.I6,
        underLeftTile = TileId.H7,
        upperLeftTile = TileId.H8
    )

    private val i8 = Tile(
        id = TileId.I8,
        color = TileColor.LIGHT,
        topTile = null,
        upperRightTile = null,
        underRightTile = null,
        bottomTile = TileId.I7,
        underLeftTile = TileId.H8,
        upperLeftTile = TileId.H9,
        chessPiece = getChessPieceFromKeyWord(ChessPieceKeyWord.BLACK_PAWN)
    )

    val columnI = listOf(i8,i7,i6,i5,i4,i3,i2,i1)
}