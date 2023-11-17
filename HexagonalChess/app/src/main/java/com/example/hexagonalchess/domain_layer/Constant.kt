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
    I1,I2,I3,I4,I5,I6,I7,I8,
    J1,J2,J3,J4,J5,J6,J7,
    K1,K2,K3,K4,K5,K6
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

enum class ChessPieceKeyWord {
    WHITE_PAWN,
    WHITE_KNIGHT,
    WHITE_BISHOP,
    WHITE_ROOK,
    WHITE_QUEEN,
    WHITE_KING,
    BLACK_PAWN,
    BLACK_KNIGHT,
    BLACK_BISHOP,
    BLACK_ROOK,
    BLACK_QUEEN,
    BLACK_KING,
}

enum class GameEndMethod {
    KING_WAS_CAPTURED,
    DRAW,
    RESIGN,
    CHECKMATE
}

enum class ChessGameState {
    PLAYER1_TURN,
    PLAYER2_TURN,
    PLAYER1_PROMOTE,
    PLAYER2_PROMOTE,
    GAME_OVER,
    CPU_TURN
}

enum class ChessGameStateOnline {
    PLAYER1_PROMOTE,
    PLAYER2_PROMOTE,
    GAME_OVER,
    OPEN
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
        const val game = "gameplay"
        const val setting = "Setting"
        const val online = "Online"
        const val boardSelection = "Board Selection"
        const val loading = "Loading"
        const val loadingOnline = "LoadingOnline"
        const val signUp = "SignUp"
        const val signIn = "SignIn"
    }
}

enum class BoardType(val nameInSelection: String) {
    DEFAULT("Default"),
    STAR_CHESS("Starchess"),
    SHAFRAN("Shafran"),
    BIG("Big")
}

enum class GameMode {
    LOCAL,
    ONLINE,
    CPU
}

enum class SettingState {
    NONE,
    THEME,
    LOG_OUT
}

enum class AuthenticationState {
    NEUTRAL,
    INVALID,
    VALID
}