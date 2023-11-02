package com.example.hexagonalchess.domain_layer

import com.example.hexagonalchess.data_layer.model.pieces.ChessPiece

class CapturedUiManager(listOfCapturedPiece:List<ChessPiece>) {

    private val capturedPawn = mutableListOf<ChessPiece>()

    private val capturedKnight = mutableListOf<ChessPiece>()

    private val capturedBishop = mutableListOf<ChessPiece>()

    private val capturedRook = mutableListOf<ChessPiece>()

    private val capturedQueen = mutableListOf<ChessPiece>()

    init {
        for (piece in listOfCapturedPiece) {
            when(piece.type) {
                PieceType.KNIGHT -> {
                    capturedKnight.add(piece)
                }
                PieceType.PAWN -> {
                    capturedPawn.add(piece)
                }
                PieceType.BISHOP -> {
                    capturedBishop.add(piece)
                }
                PieceType.ROOK -> {
                    capturedRook.add(piece)
                }
                PieceType.QUEEN -> {
                    capturedQueen.add(piece)
                }
                PieceType.KING -> { }
            }
        }
    }


}