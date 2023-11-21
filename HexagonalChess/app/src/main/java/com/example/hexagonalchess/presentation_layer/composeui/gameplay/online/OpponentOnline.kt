package com.example.hexagonalchess.presentation_layer.composeui.gameplay.online

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.hexagonalchess.R
import com.example.hexagonalchess.data_layer.model.pieces.ChessPiece
import com.example.hexagonalchess.domain_layer.ChessSkin
import com.example.hexagonalchess.domain_layer.PieceColor
import com.example.hexagonalchess.domain_layer.PieceType
import com.example.hexagonalchess.presentation_layer.composeui.gameplay.local.CapturedPieceUi
import com.example.hexagonalchess.presentation_layer.viewmodel.ChessMultiPlayerViewModel

@Composable
fun OpponentUIOnline(
    name: String,
    currentTurn: PieceColor,
    color: PieceColor,
    chessBoardViewModel: ChessMultiPlayerViewModel,
    listOfCapturedPiece: List<ChessPiece>,
    screenWidth: Dp,
    opponentSkin: ChessSkin
) {
    val borderWidth = if (currentTurn == color) { screenWidth / 100 } else { 0.dp }

    val height = remember { screenWidth / 7 }

    val currentAdvantage by chessBoardViewModel.opponentAdvantage.collectAsState()

    val capturedPawn = mutableListOf<ChessPiece>()

    val capturedKnight = mutableListOf<ChessPiece>()

    val capturedBishop = mutableListOf<ChessPiece>()

    val capturedRook = mutableListOf<ChessPiece>()

    val capturedQueen = mutableListOf<ChessPiece>()

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

    val playerImage by remember {
        mutableIntStateOf(
            when(color) {
                PieceColor.WHITE -> R.drawable.white_player_icon
                PieceColor.BLACK -> R.drawable.black_player_icon
            }
        )
    }

    val pieceUiSize = remember { screenWidth / 20 }

    Surface(
        border = BorderStroke(borderWidth,color = Color.Yellow),
        modifier = Modifier
            .width(screenWidth)
            .height(height)
    ) {
        Image(
            painter = painterResource(id = R.drawable.menu_template),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .width(screenWidth)
                .height(height)
        )
        Box(
            contentAlignment = Alignment.CenterStart,
            modifier = Modifier.padding(screenWidth / 100)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = playerImage),
                    contentDescription = null,
                    modifier = Modifier
                        .size(height * 4 / 5)
                )
                Column {
                    Text(
                        text = name,
                        modifier = Modifier
                            .padding(start = 4.dp)
                    )

                    Row {
                        if (capturedPawn.isNotEmpty()) {
                            CapturedPieceUi(listOfCapturedPiece = capturedPawn, size = pieceUiSize, opponentSkin)
                        }
                        if (capturedKnight.isNotEmpty()) {
                            CapturedPieceUi(listOfCapturedPiece = capturedKnight, size = pieceUiSize, opponentSkin)
                        }
                        if (capturedBishop.isNotEmpty()) {
                            CapturedPieceUi(listOfCapturedPiece = capturedBishop, size = pieceUiSize, opponentSkin)
                        }
                        if (capturedRook.isNotEmpty()) {
                            CapturedPieceUi(listOfCapturedPiece = capturedRook, size = pieceUiSize, opponentSkin)
                        }
                        if (capturedQueen.isNotEmpty()) {
                            CapturedPieceUi(listOfCapturedPiece = capturedQueen, size = pieceUiSize, opponentSkin)
                        }
                        Text(text = if (currentAdvantage >= 1) " | +$currentAdvantage" else "")
                    }
                }
            }
        }
    }
}