package com.example.hexagonalchess.data_layer.model.tile

import com.example.hexagonalchess.domain_layer.ChessPieceKeyWord
import com.example.hexagonalchess.domain_layer.TileColor
import com.example.hexagonalchess.domain_layer.TileId
import com.example.hexagonalchess.domain_layer.getChessPieceFromKeyWord

class ChessboardData {

    private val a1 = Tile(
        id = TileId.A1,
        color = TileColor.DARK,
        topTile = TileId.A2,
        upperRightTile = TileId.B2,
        underRightTile = TileId.B1,
        bottomTile = null,
        underLeftTile = null,
        upperLeftTile = null,
        chessPiece = getChessPieceFromKeyWord(ChessPieceKeyWord.WHITE_PAWN)
    )

    private val a2 = Tile(
        id = TileId.A2,
        color = TileColor.LIGHT,
        topTile = TileId.A3,
        upperRightTile = TileId.B3,
        underRightTile = TileId.B2,
        bottomTile = TileId.A1,
        underLeftTile = null,
        upperLeftTile = null
    )

    private val a3 = Tile(
        id = TileId.A3,
        color = TileColor.MID,
        topTile = TileId.A4,
        upperRightTile = TileId.B4,
        underRightTile = TileId.B3,
        bottomTile = TileId.A2,
        underLeftTile = null,
        upperLeftTile = null
    )

    private val a4 = Tile(
        id = TileId.A4,
        color = TileColor.DARK,
        topTile = TileId.A5,
        upperRightTile = TileId.B5,
        underRightTile = TileId.B4,
        bottomTile = TileId.A3,
        underLeftTile = null,
        upperLeftTile = null
    )

    private val a5 = Tile(
        id = TileId.A5,
        color = TileColor.LIGHT,
        topTile = TileId.A6,
        upperRightTile = TileId.B6,
        underRightTile = TileId.B5,
        bottomTile = TileId.A4,
        underLeftTile = null,
        upperLeftTile = null
    )

    private val a6 = Tile(
        id = TileId.A6,
        color = TileColor.MID,
        topTile = TileId.A7,
        upperRightTile = TileId.B7,
        underRightTile = TileId.B6,
        bottomTile = TileId.A5,
        underLeftTile = null,
        upperLeftTile = null
    )

    private val a7 = Tile(
        id = TileId.A7,
        color = TileColor.DARK,
        topTile = TileId.A8,
        upperRightTile = TileId.B8,
        underRightTile = TileId.B7,
        bottomTile = TileId.A6,
        underLeftTile = null,
        upperLeftTile = null
    )

    private val a8 = Tile(
        id = TileId.A8,
        color = TileColor.LIGHT,
        topTile = null,
        upperRightTile = TileId.B9,
        underRightTile = TileId.B8,
        bottomTile = TileId.A7,
        underLeftTile = null,
        upperLeftTile = null,
        chessPiece = getChessPieceFromKeyWord(ChessPieceKeyWord.BLACK_PAWN)
    )

    private val b1 = Tile(
        id = TileId.B1,
        color = TileColor.LIGHT,
        topTile = TileId.B2,
        upperRightTile = TileId.C2,
        underRightTile = TileId.C1,
        bottomTile = null,
        underLeftTile = null,
        upperLeftTile = TileId.A1
    )

    private val b2 = Tile(
        id = TileId.B2,
        color = TileColor.MID,
        topTile = TileId.B3,
        upperRightTile = TileId.C3,
        underRightTile = TileId.C2,
        bottomTile = TileId.B1,
        underLeftTile = TileId.A1,
        upperLeftTile = TileId.A2,
        chessPiece = getChessPieceFromKeyWord(ChessPieceKeyWord.WHITE_PAWN)
    )

    private val b3 = Tile(
        id = TileId.B3,
        color = TileColor.DARK,
        topTile = TileId.B4,
        upperRightTile = TileId.C4,
        underRightTile = TileId.C3,
        bottomTile = TileId.B2,
        underLeftTile = TileId.A2,
        upperLeftTile = TileId.A3
    )

    private val b4 = Tile(
        id = TileId.B4,
        color = TileColor.LIGHT,
        topTile = TileId.B5,
        upperRightTile = TileId.C5,
        underRightTile = TileId.C4,
        bottomTile = TileId.B3,
        underLeftTile = TileId.A3,
        upperLeftTile = TileId.A4
    )

    private val b5 = Tile(
        id = TileId.B5,
        color = TileColor.MID,
        topTile = TileId.B6,
        upperRightTile = TileId.C6,
        underRightTile = TileId.C5,
        bottomTile = TileId.B4,
        underLeftTile = TileId.A4,
        upperLeftTile = TileId.A5
    )

    private val b6 = Tile(
        id = TileId.B6,
        color = TileColor.DARK,
        topTile = TileId.B7,
        upperRightTile = TileId.C7,
        underRightTile = TileId.C6,
        bottomTile = TileId.B5,
        underLeftTile = TileId.A5,
        upperLeftTile = TileId.A6
    )

    private val b7 = Tile(
        id = TileId.B7,
        color = TileColor.LIGHT,
        topTile = TileId.B8,
        upperRightTile = TileId.C8,
        underRightTile = TileId.C7,
        bottomTile = TileId.B6,
        underLeftTile = TileId.A6,
        upperLeftTile = TileId.A7
    )

    private val b8 = Tile(
        id = TileId.B8,
        color = TileColor.MID,
        topTile = TileId.B9,
        upperRightTile = TileId.C9,
        underRightTile = TileId.C8,
        bottomTile = TileId.B7,
        underLeftTile = TileId.A7,
        upperLeftTile = TileId.A8,
        chessPiece = getChessPieceFromKeyWord(ChessPieceKeyWord.BLACK_PAWN)
    )

    private val b9 = Tile(
        id = TileId.B9,
        color = TileColor.DARK,
        topTile = null,
        upperRightTile = TileId.C10,
        underRightTile = TileId.C9,
        bottomTile = TileId.B8,
        underLeftTile = TileId.A8,
        upperLeftTile = null
    )

    private val c1 = Tile(
        id = TileId.C1,
        color = TileColor.MID,
        topTile = TileId.C2,
        upperRightTile = TileId.D2,
        underRightTile = TileId.D1,
        bottomTile = null,
        underLeftTile = null,
        upperLeftTile = TileId.B1
    )

    private val c2 = Tile(
        id = TileId.C2,
        color = TileColor.DARK,
        topTile = TileId.C3,
        upperRightTile = TileId.D3,
        underRightTile = TileId.D2,
        bottomTile = TileId.C1,
        underLeftTile = TileId.B1,
        upperLeftTile = TileId.B2
    )

    private val c3 = Tile(
        id = TileId.C3,
        color = TileColor.LIGHT,
        topTile = TileId.C4,
        upperRightTile = TileId.D4,
        underRightTile = TileId.D3,
        bottomTile = TileId.C2,
        underLeftTile = TileId.B2,
        upperLeftTile = TileId.B3,
        chessPiece = getChessPieceFromKeyWord(ChessPieceKeyWord.WHITE_PAWN)
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
        upperLeftTile = TileId.B7
    )

    private val c8 = Tile(
        id = TileId.C8,
        color = TileColor.DARK,
        topTile = TileId.C9,
        upperRightTile = TileId.D9,
        underRightTile = TileId.D8,
        bottomTile = TileId.C7,
        underLeftTile = TileId.B7,
        upperLeftTile = TileId.B8,
        chessPiece = getChessPieceFromKeyWord(ChessPieceKeyWord.BLACK_PAWN)
    )

    private val c9 = Tile(
        id = TileId.C9,
        color = TileColor.LIGHT,
        topTile = TileId.C10,
        upperRightTile = TileId.D10,
        underRightTile = TileId.D9,
        bottomTile = TileId.C8,
        underLeftTile = TileId.B8,
        upperLeftTile = TileId.B9
    )

    private val c10 = Tile(
        id = TileId.C10,
        color = TileColor.MID,
        topTile = null,
        upperRightTile = TileId.D11,
        underRightTile = TileId.D10,
        bottomTile = TileId.C9,
        underLeftTile = TileId.B9,
        upperLeftTile = null
    )

    private val d1 = Tile(
        id = TileId.D1,
        color = TileColor.DARK,
        topTile = TileId.D2,
        upperRightTile = TileId.E2,
        underRightTile = TileId.E1,
        bottomTile = null,
        underLeftTile = null,
        upperLeftTile = TileId.C1
    )

    private val d2 = Tile(
        id = TileId.D2,
        color = TileColor.LIGHT,
        topTile = TileId.D3,
        upperRightTile = TileId.E3,
        underRightTile = TileId.E2,
        bottomTile = TileId.D1,
        underLeftTile = TileId.C1,
        upperLeftTile = TileId.C2
    )

    private val d3 = Tile(
        id = TileId.D3,
        color = TileColor.MID,
        topTile = TileId.D4,
        upperRightTile = TileId.E4,
        underRightTile = TileId.E3,
        bottomTile = TileId.D2,
        underLeftTile = TileId.C2,
        upperLeftTile = TileId.C3
    )

    private val d4 = Tile(
        id = TileId.D4,
        color = TileColor.DARK,
        topTile = TileId.D5,
        upperRightTile = TileId.E5,
        underRightTile = TileId.E4,
        bottomTile = TileId.D3,
        underLeftTile = TileId.C3,
        upperLeftTile = TileId.C4,
        chessPiece = getChessPieceFromKeyWord(ChessPieceKeyWord.WHITE_PAWN)
    )

    private val d5 = Tile(
        id = TileId.D5,
        color = TileColor.LIGHT,
        topTile = TileId.D6,
        upperRightTile = TileId.E6,
        underRightTile = TileId.E5,
        bottomTile = TileId.D4,
        underLeftTile = TileId.C4,
        upperLeftTile = TileId.C5
    )

    private val d6 = Tile(
        id = TileId.D6,
        color = TileColor.MID,
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
        upperLeftTile = TileId.C7
    )

    private val d8 = Tile(
        id = TileId.D8,
        color = TileColor.LIGHT,
        topTile = TileId.D9,
        upperRightTile = TileId.E9,
        underRightTile = TileId.E8,
        bottomTile = TileId.D7,
        underLeftTile = TileId.C7,
        upperLeftTile = TileId.C8,
        chessPiece = getChessPieceFromKeyWord(ChessPieceKeyWord.BLACK_PAWN)
    )

    private val d9 = Tile(
        id = TileId.D9,
        color = TileColor.MID,
        topTile = TileId.D10,
        upperRightTile = TileId.E10,
        underRightTile = TileId.E9,
        bottomTile = TileId.D8,
        underLeftTile = TileId.C8,
        upperLeftTile = TileId.C9
    )
    private val d10 = Tile(
        id = TileId.D10,
        color = TileColor.DARK,
        topTile = TileId.D11,
        upperRightTile = TileId.E11,
        underRightTile = TileId.E10,
        bottomTile = TileId.D9,
        underLeftTile = TileId.C9,
        upperLeftTile = TileId.C10
    )

    private val d11 = Tile(
        id = TileId.D11,
        color = TileColor.LIGHT,
        topTile = null,
        upperRightTile = TileId.E12,
        underRightTile = TileId.E11,
        bottomTile = TileId.D10,
        underLeftTile = TileId.C10,
        upperLeftTile = null
    )

    private val e1 = Tile(
        id = TileId.E1,
        color = TileColor.LIGHT,
        topTile = TileId.E2,
        upperRightTile = TileId.F1,
        underRightTile = null,
        bottomTile = null,
        underLeftTile = null,
        upperLeftTile = TileId.D1
    )

    private val e2 = Tile(
        id = TileId.E2,
        color = TileColor.MID,
        topTile = TileId.E3,
        upperRightTile = TileId.F2,
        underRightTile = TileId.F1,
        bottomTile = TileId.E1,
        underLeftTile = TileId.D1,
        upperLeftTile = TileId.D2
    )

    private val e3 = Tile(
        id = TileId.E3,
        color = TileColor.DARK,
        topTile = TileId.E4,
        upperRightTile = TileId.F3,
        underRightTile = TileId.F2,
        bottomTile = TileId.E2,
        underLeftTile = TileId.D2,
        upperLeftTile = TileId.D3
    )

    private val e4 = Tile(
        id = TileId.E4,
        color = TileColor.LIGHT,
        topTile = TileId.E5,
        upperRightTile = TileId.F4,
        underRightTile = TileId.F3,
        bottomTile = TileId.E3,
        underLeftTile = TileId.D3,
        upperLeftTile = TileId.D4
    )

    private val e5 = Tile(
        id = TileId.E5,
        color = TileColor.MID,
        topTile = TileId.E6,
        upperRightTile = TileId.F5,
        underRightTile = TileId.F4,
        bottomTile = TileId.E4,
        underLeftTile = TileId.D4,
        upperLeftTile = TileId.D5,
        chessPiece = getChessPieceFromKeyWord(ChessPieceKeyWord.WHITE_PAWN)
    )

    private val e6 = Tile(
        id = TileId.E6,
        color = TileColor.DARK,
        topTile = TileId.E7,
        upperRightTile = TileId.F6,
        underRightTile = TileId.F5,
        bottomTile = TileId.E5,
        underLeftTile = TileId.D5,
        upperLeftTile = TileId.D6
    )

    private val e7 = Tile(
        id = TileId.E7,
        color = TileColor.LIGHT,
        topTile = TileId.E8,
        upperRightTile = TileId.F7,
        underRightTile = TileId.F6,
        bottomTile = TileId.E6,
        underLeftTile = TileId.D6,
        upperLeftTile = TileId.D7
    )

    private val e8 = Tile(
        id = TileId.E8,
        color = TileColor.MID,
        topTile = TileId.E9,
        upperRightTile = TileId.F8,
        underRightTile = TileId.F7,
        bottomTile = TileId.E7,
        underLeftTile = TileId.D7,
        upperLeftTile = TileId.D8,
        chessPiece = getChessPieceFromKeyWord(ChessPieceKeyWord.BLACK_PAWN)
    )

    private val e9 = Tile(
        id = TileId.E9,
        color = TileColor.DARK,
        topTile = TileId.E10,
        upperRightTile = TileId.F9,
        underRightTile = TileId.F8,
        bottomTile = TileId.E8,
        underLeftTile = TileId.D8,
        upperLeftTile = TileId.D9
    )

    private val e10 = Tile(
        id = TileId.E10,
        color = TileColor.LIGHT,
        topTile = TileId.E11,
        upperRightTile = TileId.F10,
        underRightTile = TileId.F9,
        bottomTile = TileId.E9,
        underLeftTile = TileId.D9,
        upperLeftTile = TileId.D10
    )

    private val e11 = Tile(
        id = TileId.E11,
        color = TileColor.MID,
        topTile = TileId.E12,
        upperRightTile = TileId.F11,
        underRightTile = TileId.F10,
        bottomTile = TileId.E10,
        underLeftTile = TileId.D10,
        upperLeftTile = TileId.D11
    )

    private val e12 = Tile(
        id = TileId.E12,
        color = TileColor.DARK,
        topTile = null,
        upperRightTile = null,
        underRightTile = TileId.F11,
        bottomTile = TileId.E11,
        underLeftTile = TileId.F11,
        upperLeftTile = null
    )

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
        upperLeftTile = TileId.E3
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
        upperLeftTile = TileId.E11
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

    private val g1 = Tile(
        id = TileId.G1,
        color = TileColor.MID,
        topTile = TileId.G2,
        upperRightTile = TileId.H1,
        underRightTile = null,
        bottomTile = null,
        underLeftTile = TileId.F1,
        upperLeftTile = TileId.F2
    )

    private val g2 = Tile(
        id = TileId.G2,
        color = TileColor.DARK,
        topTile = TileId.G3,
        upperRightTile = TileId.H2,
        underRightTile = TileId.H1,
        bottomTile = TileId.G1,
        underLeftTile = TileId.F2,
        upperLeftTile = TileId.F3
    )

    private val g3 = Tile(
        id = TileId.G3,
        color = TileColor.LIGHT,
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
        color = TileColor.DARK,
        topTile = TileId.G6,
        upperRightTile = TileId.H5,
        underRightTile = TileId.H4,
        bottomTile = TileId.G4,
        underLeftTile = TileId.F5,
        upperLeftTile = TileId.F6
    )

    private val g6 = Tile(
        id = TileId.G6,
        color = TileColor.LIGHT,
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
        upperLeftTile = TileId.F8
    )

    private val g8 = Tile(
        id = TileId.G8,
        color = TileColor.DARK,
        topTile = TileId.G9,
        upperRightTile = TileId.H8,
        underRightTile = TileId.H7,
        bottomTile = TileId.G7,
        underLeftTile = TileId.F8,
        upperLeftTile = TileId.F9,
        chessPiece = getChessPieceFromKeyWord(ChessPieceKeyWord.BLACK_PAWN)
    )

    private val g9 = Tile(
        id = TileId.G9,
        color = TileColor.LIGHT,
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
        upperLeftTile = TileId.F11
    )

    private val h1 = Tile(
        id = TileId.H1,
        color = TileColor.LIGHT,
        topTile = TileId.H2,
        upperRightTile = TileId.H1,
        underRightTile = null,
        bottomTile = null,
        underLeftTile = TileId.G1,
        upperLeftTile = TileId.G2
    )

    private val h2 = Tile(
        id = TileId.H2,
        color = TileColor.MID,
        topTile = TileId.H3,
        upperRightTile = TileId.I2,
        underRightTile = TileId.I1,
        bottomTile = TileId.H1,
        underLeftTile = TileId.G2,
        upperLeftTile = TileId.G3,
        chessPiece = getChessPieceFromKeyWord(ChessPieceKeyWord.WHITE_PAWN)
    )

    private val h3 = Tile(
        id = TileId.H3,
        color = TileColor.DARK,
        topTile = TileId.H4,
        upperRightTile = TileId.I3,
        underRightTile = TileId.I2,
        bottomTile = TileId.H2,
        underLeftTile = TileId.G3,
        upperLeftTile = TileId.G4
    )

    private val h4 = Tile(
        id = TileId.H4,
        color = TileColor.LIGHT,
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
        color = TileColor.DARK,
        topTile = TileId.H7,
        upperRightTile = TileId.I6,
        underRightTile = TileId.I5,
        bottomTile = TileId.H5,
        underLeftTile = TileId.G6,
        upperLeftTile = TileId.G7
    )

    private val h7 = Tile(
        id = TileId.H7,
        color = TileColor.LIGHT,
        topTile = TileId.H8,
        upperRightTile = TileId.I7,
        underRightTile = TileId.I6,
        bottomTile = TileId.H6,
        underLeftTile = TileId.G7,
        upperLeftTile = TileId.G8
    )

    private val h8 = Tile(
        id = TileId.H8,
        color = TileColor.MID,
        topTile = TileId.H9,
        upperRightTile = TileId.I8,
        underRightTile = TileId.I7,
        bottomTile = TileId.H7,
        underLeftTile = TileId.G8,
        upperLeftTile = TileId.G9,
        chessPiece = getChessPieceFromKeyWord(ChessPieceKeyWord.BLACK_PAWN)
    )

    private val h9 = Tile(
        id = TileId.H9,
        color = TileColor.DARK,
        topTile = null,
        upperRightTile = null,
        underRightTile = TileId.I8,
        bottomTile = TileId.H8,
        underLeftTile = TileId.G9,
        upperLeftTile = TileId.G10
    )

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

    val allTiles = listOf(
        a8,a7,a6,a5,a4,a3,a2,a1,
        b9,b8,b7,b6,b5,b4,b3,b2,b1,
        c10,c9,c8,c7,c6,c5,c4,c3,c2,c1,
        d11,d10,d9,d8,d7,d6,d5,d4,d3,d2,d1,
        e12,e11,e10,e9,e8,e7,e6,e5,e4,e3,e2,e1,
        f11,f10,f9,f8,f7,f6,f5,f4,f3,f2,f1,
        g10,g9,g8,g7,g6,g5,g4,g3,g2,g1,
        h9,h8,h7,h6,h5,h4,h3,h2,h1,
        i8,i7,i6,i5,i4,i3,i2,i1,
    )
}