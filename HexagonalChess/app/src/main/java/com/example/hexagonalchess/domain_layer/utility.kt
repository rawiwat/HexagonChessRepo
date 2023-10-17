package com.example.hexagonalchess.domain_layer

import com.example.hexagonalchess.data_layer.model.pieces.ChessPiece

fun getChessPieceFromKeyWord(chessPieceKeyWord: ChessPieceKeyWord): ChessPiece {
    return when(chessPieceKeyWord) {
        ChessPieceKeyWord.BLACK_PAWN -> ChessPiece(type = PieceType.PAWN, color = PieceColor.BLACK,ChessPieceKeyWord.BLACK_PAWN)
        ChessPieceKeyWord.BLACK_BISHOP -> ChessPiece(type = PieceType.BISHOP, color = PieceColor.BLACK,ChessPieceKeyWord.BLACK_BISHOP)
        ChessPieceKeyWord.BLACK_KNIGHT -> ChessPiece(type = PieceType.KNIGHT, color = PieceColor.BLACK,ChessPieceKeyWord.BLACK_KNIGHT)
        ChessPieceKeyWord.BLACK_ROOK -> ChessPiece(type = PieceType.ROOK, color = PieceColor.BLACK,ChessPieceKeyWord.BLACK_ROOK)
        ChessPieceKeyWord.BLACK_QUEEN -> ChessPiece(type = PieceType.QUEEN, color = PieceColor.BLACK,ChessPieceKeyWord.BLACK_QUEEN)
        ChessPieceKeyWord.BLACK_KING -> ChessPiece(type = PieceType.KING, color = PieceColor.BLACK,ChessPieceKeyWord.BLACK_KING)
        ChessPieceKeyWord.WHITE_PAWN -> ChessPiece(type = PieceType.PAWN, color = PieceColor.WHITE,ChessPieceKeyWord.WHITE_PAWN)
        ChessPieceKeyWord.WHITE_BISHOP -> ChessPiece(type = PieceType.BISHOP, color = PieceColor.WHITE,ChessPieceKeyWord.WHITE_BISHOP)
        ChessPieceKeyWord.WHITE_KNIGHT -> ChessPiece(type = PieceType.KNIGHT, color = PieceColor.WHITE,ChessPieceKeyWord.WHITE_KNIGHT)
        ChessPieceKeyWord.WHITE_ROOK -> ChessPiece(type = PieceType.ROOK, color = PieceColor.WHITE,ChessPieceKeyWord.WHITE_ROOK)
        ChessPieceKeyWord.WHITE_QUEEN -> ChessPiece(type = PieceType.QUEEN, color = PieceColor.WHITE,ChessPieceKeyWord.WHITE_QUEEN)
        ChessPieceKeyWord.WHITE_KING -> ChessPiece(type = PieceType.KING, color = PieceColor.WHITE,ChessPieceKeyWord.WHITE_KING)
        ChessPieceKeyWord.NONE -> ChessPiece(type = PieceType.NONE,color = PieceColor.NONE,ChessPieceKeyWord.NONE)
    }
}

fun getChessPieceFromKeyWordName(chessPieceKeyWord: String): ChessPiece? {
    return when(chessPieceKeyWord) {
        ChessPieceKeyWord.BLACK_PAWN.name -> ChessPiece(type = PieceType.PAWN, color = PieceColor.BLACK,ChessPieceKeyWord.BLACK_PAWN)
        ChessPieceKeyWord.BLACK_BISHOP.name -> ChessPiece(type = PieceType.BISHOP, color = PieceColor.BLACK,ChessPieceKeyWord.BLACK_BISHOP)
        ChessPieceKeyWord.BLACK_KNIGHT.name -> ChessPiece(type = PieceType.KNIGHT, color = PieceColor.BLACK,ChessPieceKeyWord.BLACK_KNIGHT)
        ChessPieceKeyWord.BLACK_ROOK.name -> ChessPiece(type = PieceType.ROOK, color = PieceColor.BLACK,ChessPieceKeyWord.BLACK_ROOK)
        ChessPieceKeyWord.BLACK_QUEEN.name -> ChessPiece(type = PieceType.QUEEN, color = PieceColor.BLACK,ChessPieceKeyWord.BLACK_QUEEN)
        ChessPieceKeyWord.BLACK_KING.name -> ChessPiece(type = PieceType.KING, color = PieceColor.BLACK,ChessPieceKeyWord.BLACK_KING)
        ChessPieceKeyWord.WHITE_PAWN.name -> ChessPiece(type = PieceType.PAWN, color = PieceColor.WHITE,ChessPieceKeyWord.WHITE_PAWN)
        ChessPieceKeyWord.WHITE_BISHOP.name -> ChessPiece(type = PieceType.BISHOP, color = PieceColor.WHITE,ChessPieceKeyWord.WHITE_BISHOP)
        ChessPieceKeyWord.WHITE_KNIGHT.name -> ChessPiece(type = PieceType.KNIGHT, color = PieceColor.WHITE,ChessPieceKeyWord.WHITE_KNIGHT)
        ChessPieceKeyWord.WHITE_ROOK.name -> ChessPiece(type = PieceType.ROOK, color = PieceColor.WHITE,ChessPieceKeyWord.WHITE_ROOK)
        ChessPieceKeyWord.WHITE_QUEEN.name -> ChessPiece(type = PieceType.QUEEN, color = PieceColor.WHITE,ChessPieceKeyWord.WHITE_QUEEN)
        ChessPieceKeyWord.WHITE_KING.name -> ChessPiece(type = PieceType.KING, color = PieceColor.WHITE,ChessPieceKeyWord.WHITE_KING)
        else -> null
    }
}