package com.example.hexagonalchess.presentation_layer.composeui.gameplay

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hexagonalchess.R
import com.example.hexagonalchess.data_layer.chess_board_data.base.ChessboardData
import com.example.hexagonalchess.data_layer.model.pieces.ChessPiece
import com.example.hexagonalchess.domain_layer.BoardType
import com.example.hexagonalchess.domain_layer.ChessPieceKeyWord
import com.example.hexagonalchess.domain_layer.GameMode
import com.example.hexagonalchess.domain_layer.PieceColor
import com.example.hexagonalchess.domain_layer.PieceType
import com.example.hexagonalchess.domain_layer.getChessPieceFromKeyWord
import com.example.hexagonalchess.domain_layer.getChessPieceImage
import com.example.hexagonalchess.presentation_layer.viewmodel.ChessBoardViewModel


@Composable
fun PlayerUI(
    currentTurn: PieceColor,
    color: PieceColor,
    chessBoardViewModel: ChessBoardViewModel,
    listOfCapturedPiece: List<ChessPiece>
) {
    val borderWidth = if (currentTurn == color) { 6.dp } else { 0.dp }

    val currentAdvantage by if (color == PieceColor.BLACK) {
        chessBoardViewModel.blackAdvantage.collectAsState()
    } else {
        chessBoardViewModel.whiteAdvantage.collectAsState()
    }
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

    Surface(
        border = BorderStroke(borderWidth,color = Color.White),
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(
                color = Color.Gray
            )
    ) {
        Box(
            contentAlignment = Alignment.CenterStart,
            modifier = Modifier.padding(5.dp)
        ) {
            Row {
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = null
                )
                Column {
                    Text(text = if (color == PieceColor.WHITE) "Player White" else "Player Black")

                    Row {
                        if (capturedPawn.isNotEmpty()) {
                            CapturedPieceUi(listOfCapturedPiece = capturedPawn)
                        }
                        if (capturedKnight.isNotEmpty()) {
                            CapturedPieceUi(listOfCapturedPiece = capturedKnight)
                        }
                        if (capturedBishop.isNotEmpty()) {
                            CapturedPieceUi(listOfCapturedPiece = capturedBishop)
                        }
                        if (capturedRook.isNotEmpty()) {
                            CapturedPieceUi(listOfCapturedPiece = capturedRook)
                        }
                        if (capturedQueen.isNotEmpty()) {
                            CapturedPieceUi(listOfCapturedPiece = capturedQueen)
                        }
                        Text(text = if (currentAdvantage >= 1) " | Material +$currentAdvantage" else "")
                    }
                }
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    Row {
                        val sizeModifierForIcon = Modifier
                            .size(40.dp)
                        Image(
                            painter = painterResource(id = R.drawable.draw_offer_icon),
                            contentDescription = null,
                            modifier = sizeModifierForIcon
                                .clickable {

                                }
                        )
                        Image(
                            painter = painterResource(id = R.drawable.resign_flag),
                            contentDescription = null,
                            modifier = sizeModifierForIcon
                                .clickable {
                                    chessBoardViewModel.resign(color)
                                }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CapturedPieceUi(
    listOfCapturedPiece: List<ChessPiece>
) {
    Row {
        Image(
            painter = painterResource(id = getChessPieceImage(listOfCapturedPiece[0])),
            contentDescription = null
        )
        Text(
            text = "×${listOfCapturedPiece.size}",
            style = TextStyle(
                color = Color.Black
            )
        )
    }
}

@Preview
@Composable
fun PlayerUIPreview() {
    val viewModel = ChessBoardViewModel(
        ChessboardData().allTiles,
        BoardType.DEFAULT,
        context = LocalContext.current,
        GameMode.LOCAL,
        PieceColor.BLACK
    )

    val capturedPieces by viewModel.blackCaptured.collectAsState()

    PlayerUI(
        currentTurn = PieceColor.BLACK,
        color = PieceColor.BLACK,
        chessBoardViewModel = viewModel,
        listOfCapturedPiece = capturedPieces
    )
}