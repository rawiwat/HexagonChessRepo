package com.example.hexagonalchess.data_layer.chess_board_data.shuriken

import com.example.hexagonalchess.data_layer.model.pieces.ChessPiece
import com.example.hexagonalchess.data_layer.model.tile.Tile
import com.example.hexagonalchess.domain_layer.ChessPieceKeyWord
import com.example.hexagonalchess.domain_layer.TileColor
import com.example.hexagonalchess.domain_layer.TileId
import com.example.hexagonalchess.domain_layer.getChessPieceFromKeyWord

class ShurikenBoardData {
    val a1 = Tile(
        id = TileId.A1,
        color = TileColor.LIGHT,
        topTile = null,
        upperRightTile = TileId.B2,
        underRightTile = TileId.B1,
        bottomTile = null,
        underLeftTile = null,
        upperLeftTile = null,
    )

    val b1 = Tile(
        id = TileId.B1,
        color = TileColor.MID,
        topTile = TileId.B2,
        upperRightTile = TileId.C4,
        underRightTile = TileId.C3,
        bottomTile = null,
        underLeftTile = null,
        upperLeftTile = TileId.A1,
    )

    val b2 = Tile(
        id = TileId.B2,
        color = TileColor.DARK,
        topTile = TileId.B2,
        upperRightTile = TileId.C4,
        underRightTile = TileId.C3,
        bottomTile = null,
        underLeftTile = null,
        upperLeftTile = TileId.A1,
    )

    val c1 = Tile(
        id = TileId.C1,
        color = TileColor.LIGHT,
        topTile = TileId.C2,
        upperRightTile = TileId.D1,
        underRightTile = null,
        bottomTile = null,
        underLeftTile = null,
        upperLeftTile = null,
        chessPiece = getChessPieceFromKeyWord(ChessPieceKeyWord.WHITE_ROOK)
    )

    val c2 = Tile(
        id = TileId.C2,
        color = TileColor.MID,
        topTile = TileId.C3,
        upperRightTile = TileId.D1,
        underRightTile = null,
        bottomTile = null,
        underLeftTile = null,
        upperLeftTile = null,
        chessPiece = getChessPieceFromKeyWord(ChessPieceKeyWord.WHITE_PAWN)
    )
}