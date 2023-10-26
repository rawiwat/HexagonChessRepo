package com.example.hexagonalchess.domain_layer

enum class TileId {
    A1,A2,A3,A4,A5,A6,A7,A8,
    B1,B2,B3,B4,B5,B6,B7,B8,B9,
    C1,C2,C3,C4,C5,C6,C7,C8,C9,C10,
    D1,D2,D3,D4,D5,D6,D7,D8,D9,D10,D11,
    E1,E2,E3,E4,E5,E6,E7,E8,E9,E10,E11,E12,
    F1,F2,F3,F4,F5,F6,F7,F8,F9,F10,F11,
    G1,G2,G3,G4,G5,G6,G7,G8,G9,G10,
    H1,H2,H3,H4,H5,H6,H7,H8,H9,
    I1,I2,I3,I4,I5,I6,I7,I8
}

enum class TileColor {
    DARK,
    MID,
    LIGHT,
}

enum class PieceType {
    KNIGHT,
    PAWN,
    BISHOP,
    ROOK,
    QUEEN,
    KING,
}

enum class PieceColor {
    BLACK,
    WHITE
}

enum class TileDirections {
    TOP,
    UPPER_RIGHT,
    UNDER_RIGHT,
    BOTTOM,
    UPPER_LEFT,
    UNDER_LEFT
}

enum class ChessPieceKeyWord(key:String) {
    WHITE_PAWN("wP"),
    WHITE_KNIGHT("wN"),
    WHITE_BISHOP("wB"),
    WHITE_ROOK("wR"),
    WHITE_QUEEN("wQ"),
    WHITE_KING("wK"),
    BLACK_PAWN("bP"),
    BLACK_KNIGHT("bN"),
    BLACK_BISHOP("bB"),
    BLACK_ROOK("bR"),
    BLACK_QUEEN("bQ"),
    BLACK_KING("bK"),
}

enum class GameEndMethod {
    KING_WAS_CAPTURED,
    DRAW,
    RESIGN,
    CHECKMATE
}

enum class GameStateLocal {
    OPEN,
    PROMOTE,
    GAME_OVER,
}

enum class GameStateVsCpu {
    PLAYER_TURN,
    PROMOTE,
    GAME_OVER,
    CPU_TURN
}

enum class TileTheme {
    DEFAULT,
    RED,
    BLUE,
    YELLOW,
    PURPLE,
    GREEN,
    ORANGE
}

class Route {
    companion object {
        const val main = "Main"
        const val local = "Local"
        const val setting = "Setting"
        const val vsCpu = "Vs Cpu"
    }
}