package com.example.hexagonalchess.domain_layer

import com.example.hexagonalchess.R
import com.example.hexagonalchess.data_layer.model.pieces.ChessPiece
import kotlin.math.absoluteValue

fun getChessPieceFromKeyWord(chessPieceKeyWord: ChessPieceKeyWord): ChessPiece {
    return when(chessPieceKeyWord) {
        ChessPieceKeyWord.BLACK_PAWN -> ChessPiece(
            type = PieceType.PAWN,
            color = PieceColor.BLACK,
            ChessPieceKeyWord.BLACK_PAWN
        )
        ChessPieceKeyWord.BLACK_BISHOP -> ChessPiece(
            type = PieceType.BISHOP,
            color = PieceColor.BLACK,
            ChessPieceKeyWord.BLACK_BISHOP
        )
        ChessPieceKeyWord.BLACK_KNIGHT -> ChessPiece(
            type = PieceType.KNIGHT,
            color = PieceColor.BLACK,
            ChessPieceKeyWord.BLACK_KNIGHT
        )
        ChessPieceKeyWord.BLACK_ROOK -> ChessPiece(
            type = PieceType.ROOK,
            color = PieceColor.BLACK,
            ChessPieceKeyWord.BLACK_ROOK
        )
        ChessPieceKeyWord.BLACK_QUEEN -> ChessPiece(
            type = PieceType.QUEEN,
            color = PieceColor.BLACK,
            ChessPieceKeyWord.BLACK_QUEEN
        )
        ChessPieceKeyWord.BLACK_KING -> ChessPiece(
            type = PieceType.KING,
            color = PieceColor.BLACK,
            ChessPieceKeyWord.BLACK_KING
        )
        ChessPieceKeyWord.WHITE_PAWN -> ChessPiece(
            type = PieceType.PAWN,
            color = PieceColor.WHITE,
            ChessPieceKeyWord.WHITE_PAWN
        )
        ChessPieceKeyWord.WHITE_BISHOP -> ChessPiece(
            type = PieceType.BISHOP,
            color = PieceColor.WHITE,
            ChessPieceKeyWord.WHITE_BISHOP
        )
        ChessPieceKeyWord.WHITE_KNIGHT -> ChessPiece(
            type = PieceType.KNIGHT,
            color = PieceColor.WHITE,
            ChessPieceKeyWord.WHITE_KNIGHT
        )
        ChessPieceKeyWord.WHITE_ROOK -> ChessPiece(
            type = PieceType.ROOK,
            color = PieceColor.WHITE,
            ChessPieceKeyWord.WHITE_ROOK
        )
        ChessPieceKeyWord.WHITE_QUEEN -> ChessPiece(
            type = PieceType.QUEEN,
            color = PieceColor.WHITE,
            ChessPieceKeyWord.WHITE_QUEEN
        )
        ChessPieceKeyWord.WHITE_KING -> ChessPiece(
            type = PieceType.KING,
            color = PieceColor.WHITE,
            ChessPieceKeyWord.WHITE_KING
        )
    }
}

fun getChessPieceImage(chessPiece: ChessPiece): Int {
    return when(chessPiece.keyWord) {
        ChessPieceKeyWord.BLACK_PAWN -> R.drawable.black_pawn
        ChessPieceKeyWord.BLACK_BISHOP -> R.drawable.black_bishop
        ChessPieceKeyWord.BLACK_KNIGHT -> R.drawable.black_knight
        ChessPieceKeyWord.BLACK_ROOK -> R.drawable.black_rook
        ChessPieceKeyWord.BLACK_QUEEN -> R.drawable.black_queen
        ChessPieceKeyWord.BLACK_KING -> R.drawable.black_king
        ChessPieceKeyWord.WHITE_PAWN -> R.drawable.white_pawn
        ChessPieceKeyWord.WHITE_BISHOP -> R.drawable.white_bishop
        ChessPieceKeyWord.WHITE_KNIGHT -> R.drawable.white_knight
        ChessPieceKeyWord.WHITE_ROOK -> R.drawable.white_rook
        ChessPieceKeyWord.WHITE_QUEEN -> R.drawable.white_queen
        ChessPieceKeyWord.WHITE_KING -> R.drawable.white_king
    }
}

fun getPromotionKeyWordFromColor(color: PieceColor):List<ChessPieceKeyWord> {
    return when(color) {
        PieceColor.BLACK -> listOf(
            ChessPieceKeyWord.BLACK_BISHOP,
            ChessPieceKeyWord.BLACK_KNIGHT,
            ChessPieceKeyWord.BLACK_ROOK,
            ChessPieceKeyWord.BLACK_QUEEN
        )
        PieceColor.WHITE -> listOf(
            ChessPieceKeyWord.WHITE_BISHOP,
            ChessPieceKeyWord.WHITE_KNIGHT,
            ChessPieceKeyWord.WHITE_ROOK,
            ChessPieceKeyWord.WHITE_QUEEN
        )
    }
}

fun generatePlayerIdFromName(input: String): Int {
    val hashCode = input.hashCode().absoluteValue
    return String.format("%06d", hashCode % 1000000).toInt()
}