package com.example.hexagonalchess.domain_layer

import android.content.Context
import android.media.MediaPlayer
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

fun getImageIdFromBoardType(boardType: BoardType): Int {
    return when(boardType) {
        BoardType.DEFAULT -> R.drawable.board_preview_default
        BoardType.STAR_CHESS -> R.drawable.board_preview_shuriken
        BoardType.SHAFRAN -> R.drawable.board_preview_shafran
        BoardType.BIG -> R.drawable.board_preview_equilateral
    }
}

fun getListOfPromotionTile(boardType: BoardType, pieceColor: PieceColor): List<TileId> {
    return when(boardType) {
        BoardType.DEFAULT ->
            when(pieceColor) {
                PieceColor.WHITE -> listOf(
                    TileId.A8, TileId.B9, TileId.C10, TileId.D11, TileId.E12,
                    TileId.F11, TileId.G10, TileId.H9, TileId.I8
                )
                PieceColor.BLACK -> listOf(
                    TileId.A1, TileId.B1, TileId.C1, TileId.D1, TileId.E1,
                    TileId.F1, TileId.G1, TileId.H1, TileId.I1,
                )
            }

        BoardType.STAR_CHESS ->
            when(pieceColor) {
                PieceColor.WHITE -> listOf(
                    TileId.C7, TileId.D6, TileId.E5, TileId.F6, TileId.G7
                )
                PieceColor.BLACK -> listOf(
                    TileId.C1, TileId.D1, TileId.E1, TileId.F1, TileId.G1
                )
            }

        BoardType.SHAFRAN ->
            when(pieceColor) {
                PieceColor.WHITE -> listOf(
                    TileId.A6,TileId.B7,TileId.C8,TileId.D9,TileId.E10,
                    TileId.F9,TileId.G8,TileId.H7,TileId.I6
                )

                PieceColor.BLACK -> listOf(
                    TileId.A1, TileId.B1, TileId.C1, TileId.D1, TileId.E1,
                    TileId.F1, TileId.G1, TileId.H1, TileId.I1,
                )
            }

        BoardType.BIG ->
            when(pieceColor) {
                PieceColor.WHITE -> listOf(
                    TileId.B7,TileId.C8,TileId.D9,TileId.E10,
                    TileId.F11,TileId.G10,TileId.H9,TileId.I8,TileId.J7
                )

                PieceColor.BLACK -> listOf(
                    TileId.B1,TileId.C1,TileId.D1,TileId.E1,
                    TileId.F1,TileId.G1,TileId.H1,TileId.I1,TileId.J1
                )
            }
    }
}

fun playSoundEffect(context: Context, soundEffectId: Int) {
    val soundEffect = MediaPlayer.create(context, soundEffectId)
    soundEffect.setOnCompletionListener {
        soundEffect.release()
    }
    soundEffect.start()
}

fun PieceColor.opposite():PieceColor {
    return when(this){
        PieceColor.WHITE -> PieceColor.BLACK
        PieceColor.BLACK -> PieceColor.WHITE
    }
}

fun getPawnStartingPoint(boardType: BoardType, color: PieceColor): List<TileId> {
    val result = when(boardType) {
        BoardType.DEFAULT -> when(color) {
            PieceColor.WHITE -> listOf(
                TileId.A1, TileId.B2, TileId.C3, TileId.D4, TileId.E5,
                TileId.F4, TileId.G3, TileId.H2, TileId.I1
            )

            PieceColor.BLACK -> listOf(
                TileId.A8, TileId.B8, TileId.C8, TileId.D8, TileId.E8,
                TileId.F8, TileId.G8, TileId.H8, TileId.I8
            )
        }

        BoardType.STAR_CHESS -> listOf()
        BoardType.SHAFRAN -> when(color) {
            PieceColor.WHITE -> listOf(
                TileId.A2, TileId.B2, TileId.C2, TileId.D2, TileId.E2,
                TileId.F2, TileId.G2, TileId.H2, TileId.I2
            )

            PieceColor.BLACK -> listOf(
                TileId.A5, TileId.B6, TileId.C7, TileId.D8, TileId.E9,
                TileId.F8, TileId.G7, TileId.H6, TileId.I5
            )
        }

        BoardType.BIG -> when(color) {
            PieceColor.WHITE -> listOf(
                TileId.B1,TileId.C2,TileId.D3,TileId.E4,TileId.F5,
                TileId.G4,TileId.H3,TileId.I2,TileId.J1
            )
            PieceColor.BLACK -> listOf(
                TileId.B7, TileId.C7, TileId.D7, TileId.E7, TileId.F7,
                TileId.G7, TileId.H7, TileId.I7, TileId.J7
            )
        }
    }
    println(result)
    return result
}