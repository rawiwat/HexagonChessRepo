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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    val borderWidth = if (currentTurn == color) { 4.dp } else { 0.dp }

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

    val playerImage by remember {
        mutableIntStateOf(
            when(color) {
                PieceColor.WHITE -> R.drawable.white_player_icon
                PieceColor.BLACK -> R.drawable.black_player_icon
            }
        )
    }

    val playerText by remember {
        mutableStateOf(
            when(color) {
                PieceColor.WHITE -> "Player White"
                PieceColor.BLACK -> "Player Black"
            }
        )
    }

    val isWhite = remember { color == PieceColor.WHITE }

    val isBlack = remember { color == PieceColor.BLACK }

    val whiteOfferedDraw by chessBoardViewModel.whiteOfferedDraw.collectAsState()

    val blackOfferedDraw by chessBoardViewModel.blackOfferedDraw.collectAsState()

    val whiteOfferedActive by rememberSaveable {
        mutableStateOf(
            isWhite && blackOfferedDraw
        )
    }
    val blackOfferedActive by rememberSaveable {
        mutableStateOf(
            isBlack && whiteOfferedDraw
        )
    }
    val drawOffered by rememberSaveable {
        mutableStateOf(
            whiteOfferedActive || blackOfferedActive
        )
    }

    Surface(
        border = BorderStroke(borderWidth,color = Color.DarkGray),
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.menu_template),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
        )
        Box(
            contentAlignment = Alignment.CenterStart,
            modifier = Modifier.padding(5.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = playerImage),
                    contentDescription = null
                )
                Column {
                    Text(
                        text = playerText,
                        modifier = Modifier
                            .padding(start = 4.dp)
                    )

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
                        Text(text = if (currentAdvantage >= 1) " | +$currentAdvantage" else "")
                    }
                }

                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    Row {
                        if (drawOffered) {
                            Column {
                                val fontSize = remember { 10.sp }
                                Box(
                                    modifier = Modifier
                                        .size(width = 80.dp,height = 25.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.menu_template),
                                        contentDescription = null,
                                        contentScale = ContentScale.FillBounds,
                                        modifier = Modifier
                                            .clickable {
                                                chessBoardViewModel.drawAccepted(color)
                                            }
                                    )
                                    Text(
                                        text = "Accept draw",
                                        fontSize = fontSize
                                    )
                                }
                                Box(
                                    modifier = Modifier
                                        .size(width = 80.dp,height = 25.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.menu_template),
                                        contentDescription = null,
                                        contentScale = ContentScale.FillBounds,
                                        modifier = Modifier
                                            .clickable {
                                                chessBoardViewModel.drawRejected(color)
                                            }
                                    )
                                    Text(
                                        text = "Refuse Draw",
                                        fontSize = fontSize
                                    )
                                }
                            }
                        }

                        Column {
                            val sizeModifierForIcon = Modifier
                                .size(25.dp)
                            Image(
                                painter = painterResource(id = R.drawable.draw_offer_icon),
                                contentDescription = null,
                                modifier = sizeModifierForIcon
                                    .clickable {
                                        chessBoardViewModel.drawOffered(color)
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
}

@Composable
fun CapturedPieceUi(
    listOfCapturedPiece: List<ChessPiece>
) {
    Row {
        Image(
            painter = painterResource(id = getChessPieceImage(listOfCapturedPiece[0])),
            contentDescription = null,
            modifier = Modifier
                .size(20.dp)
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

    PlayerUI(
        currentTurn = PieceColor.BLACK,
        color = PieceColor.BLACK,
        chessBoardViewModel = viewModel,
        listOfCapturedPiece = listOf(
            getChessPieceFromKeyWord(ChessPieceKeyWord.WHITE_BISHOP),
            getChessPieceFromKeyWord(ChessPieceKeyWord.WHITE_BISHOP),
            getChessPieceFromKeyWord(ChessPieceKeyWord.WHITE_QUEEN),
            getChessPieceFromKeyWord(ChessPieceKeyWord.WHITE_QUEEN),
            getChessPieceFromKeyWord(ChessPieceKeyWord.WHITE_ROOK),
            getChessPieceFromKeyWord(ChessPieceKeyWord.WHITE_ROOK),
            getChessPieceFromKeyWord(ChessPieceKeyWord.WHITE_KNIGHT),
            getChessPieceFromKeyWord(ChessPieceKeyWord.WHITE_KNIGHT),
            getChessPieceFromKeyWord(ChessPieceKeyWord.WHITE_KNIGHT),
            getChessPieceFromKeyWord(ChessPieceKeyWord.WHITE_KNIGHT),
            getChessPieceFromKeyWord(ChessPieceKeyWord.WHITE_KNIGHT),
            getChessPieceFromKeyWord(ChessPieceKeyWord.WHITE_KNIGHT),
            getChessPieceFromKeyWord(ChessPieceKeyWord.WHITE_PAWN),
            getChessPieceFromKeyWord(ChessPieceKeyWord.WHITE_PAWN),
            getChessPieceFromKeyWord(ChessPieceKeyWord.WHITE_PAWN),
            getChessPieceFromKeyWord(ChessPieceKeyWord.WHITE_PAWN),
            getChessPieceFromKeyWord(ChessPieceKeyWord.WHITE_PAWN),
            getChessPieceFromKeyWord(ChessPieceKeyWord.WHITE_PAWN),
        )
    )
}