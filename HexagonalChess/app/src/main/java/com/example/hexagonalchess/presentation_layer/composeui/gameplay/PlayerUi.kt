package com.example.hexagonalchess.presentation_layer.composeui.gameplay

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.hexagonalchess.R
import com.example.hexagonalchess.data_layer.model.pieces.ChessPiece
import com.example.hexagonalchess.domain_layer.PieceColor
import com.example.hexagonalchess.domain_layer.PieceType
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
                        Box {
                            var totalPawnOffset = capturedPawn.size * 10
                            when (capturedPawn.size) {
                                1 -> totalPawnOffset += 5
                                else -> totalPawnOffset -= 5 * (capturedPawn.size - 2)
                            }
                            var totalKnightOffset = (capturedKnight.size * 10) + totalPawnOffset
                            if (capturedKnight.isNotEmpty()) {
                                totalKnightOffset += 5
                            }
                            var totalBishopOffset = (capturedBishop.size * 10) + totalKnightOffset
                            if (capturedBishop.isNotEmpty()) {
                                totalBishopOffset += 5
                            }
                            var totalRookOffset = (capturedRook.size * 10) + totalBishopOffset
                            if (capturedRook.isNotEmpty()) {
                                totalRookOffset += 5
                            }

                            LazyRow {
                                items(capturedPawn.size) {
                                    Image(
                                        painter = painterResource(id = getChessPieceImage(capturedPawn[it])),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .size(20.dp)
                                            .offset(x = (it * (-15)).dp)
                                    )
                                }
                            }

                            LazyRow(
                                modifier = Modifier.offset(x = totalPawnOffset.dp)
                            ) {
                                items(capturedKnight.size) {
                                    Image(
                                        painter = painterResource(id = getChessPieceImage(capturedKnight[it])),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .size(20.dp)
                                            .offset(x = (it * (-15)).dp)
                                    )
                                }
                            }
                            LazyRow(
                                modifier = Modifier.offset(x = totalKnightOffset.dp)
                            ) {
                                items(capturedBishop.size) {
                                    Image(
                                        painter = painterResource(id = getChessPieceImage(capturedBishop[it])),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .size(20.dp)
                                            .offset(x = (it * (-15)).dp)
                                    )
                                }
                            }
                            LazyRow(
                                modifier = Modifier.offset(x = totalBishopOffset.dp)
                            ) {
                                items(capturedRook.size) {
                                    Image(
                                        painter = painterResource(id = getChessPieceImage(capturedRook[it])),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .size(20.dp)
                                            .offset(x = (it * (-15)).dp)
                                    )
                                }
                            }
                            LazyRow(
                                modifier = Modifier.offset(x = totalRookOffset.dp)
                            ) {
                                items(capturedQueen.size) {
                                    Image(
                                        painter = painterResource(id = getChessPieceImage(capturedQueen[it])),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .size(20.dp)
                                            .offset(x = (it * (-15)).dp)
                                    )
                                }
                            }
                        }
                        Text(text = if (currentAdvantage >= 1) "+$currentAdvantage" else "")
                    }
                }
            }
        }
    }
}
