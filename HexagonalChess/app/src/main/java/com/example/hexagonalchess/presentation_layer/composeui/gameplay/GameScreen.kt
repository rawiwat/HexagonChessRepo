package com.example.hexagonalchess.presentation_layer.composeui.gameplay

import android.content.Context
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hexagonalchess.R
import com.example.hexagonalchess.data_layer.model.pieces.ChessPiece
import com.example.hexagonalchess.data_layer.model.tile.Tile
import com.example.hexagonalchess.domain_layer.BoardType
import com.example.hexagonalchess.domain_layer.ChessPieceKeyWord
import com.example.hexagonalchess.domain_layer.ChessGameState
import com.example.hexagonalchess.domain_layer.PieceColor
import com.example.hexagonalchess.domain_layer.PieceType
import com.example.hexagonalchess.domain_layer.TileTheme
import com.example.hexagonalchess.domain_layer.tile_ui_manager.TileUiManager
import com.example.hexagonalchess.domain_layer.getChessPieceFromKeyWord
import com.example.hexagonalchess.domain_layer.getChessPieceImage
import com.example.hexagonalchess.domain_layer.getPromotionKeyWordFromColor
import com.example.hexagonalchess.domain_layer.getTileImage
import com.example.hexagonalchess.domain_layer.theme_setting.ThemeSharedPrefs
import com.example.hexagonalchess.presentation_layer.viewmodel.ChessBoardViewModel

@Composable
fun GameScreen(
    chessBoardViewModel: ChessBoardViewModel,
    context: Context,
    boardType: BoardType
) {
    val chessBoard by chessBoardViewModel.chessBoard.collectAsState()
    val currentTurn by chessBoardViewModel.currentTurn.collectAsState()
    val blackCaptured by chessBoardViewModel.blackCaptured.collectAsState()
    val whiteCaptured by chessBoardViewModel.whiteCaptured.collectAsState()
    val gameState by chessBoardViewModel.gameState.collectAsState()
    val gameOverMessage by chessBoardViewModel.gameOverMessage.collectAsState()
    val theme by remember { mutableStateOf(ThemeSharedPrefs(context).getTheme()) }
    val localConfiguration = LocalConfiguration.current
    val screenWidth by remember { mutableIntStateOf(localConfiguration.screenWidthDp) }
    val tileUiManager by remember { mutableStateOf(TileUiManager(screenWidth)) }
    val playerColor by remember { mutableStateOf(chessBoardViewModel.playerColor) }
    //val gameMode by remember { mutableStateOf(chessBoardViewModel.gameMode) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(color = Color.DarkGray)
            .fillMaxSize()
    ) {
        PlayerUI(
            currentTurn = currentTurn,
            color = PieceColor.WHITE,
            chessBoardViewModel = chessBoardViewModel,
            listOfCapturedPiece = whiteCaptured
        )

        PlayerUI(
            currentTurn = currentTurn,
            color = PieceColor.BLACK,
            chessBoardViewModel = chessBoardViewModel,
            listOfCapturedPiece = blackCaptured
        )

        Box(
            modifier = Modifier
                .fillMaxWidth(),
            //contentAlignment = Alignment.TopCenter
        ) {
            when(boardType) {
                BoardType.DEFAULT -> {
                    ChessBoardUI(
                        chessBoardData = chessBoard,
                        chessBoardViewModel = chessBoardViewModel,
                        theme = theme,
                        screenWidth = screenWidth,
                        tileUiManager = tileUiManager,
                        boardType = boardType,
                        playerColor = playerColor
                    )
                }
                BoardType.STAR_CHESS -> {
                    StarBoardUI(
                        chessBoardData = chessBoard,
                        chessBoardViewModel = chessBoardViewModel,
                        theme = theme,
                        screenWidth = screenWidth,
                        tileUiManager = tileUiManager,
                        boardType = boardType,
                        playerColor = playerColor
                    )
                }

                BoardType.SHAFRAN -> {
                    ShafranChessBoardUI(
                        chessBoardData = chessBoard,
                        chessBoardViewModel = chessBoardViewModel,
                        theme = theme,
                        screenWidth = screenWidth,
                        tileUiManager = tileUiManager,
                        boardType = boardType,
                        playerColor = playerColor
                    )
                }

                BoardType.BIG -> {
                    BigBoardUI(
                        chessBoardData = chessBoard,
                        chessBoardViewModel = chessBoardViewModel,
                        theme = theme,
                        screenWidth = screenWidth,
                        tileUiManager = tileUiManager,
                        boardType = boardType,
                        playerColor = playerColor
                    )
                }
            }
        }

        /*Button(
            onClick = {
                for (tile in chessBoard) {
                    println("${tile.id} : ${ tile.isAPossibleMove }")
                }
            }
        ) {
            Text(text = "check board")
        }

        Button(
            onClick = {
                for (tile in chessBoard) {
                    println("${tile.id} : ${tile.chessPiece?.keyWord}")
                }
            }
        ) {
            Text(text = "check board2")
        }
        Button(
            onClick = {
                for (piece in blackCaptured) {
                    println("Black Captured : ${piece.keyWord}")
                }
                for (piece in whiteCaptured) {
                    println("White Captured : ${piece.keyWord}")
                }
            }
        ) {
        }*/
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        //contentAlignment = Alignment.Center
    ) {
        val popUpBoxWidth by remember { mutableStateOf(250.dp) }
        val popUpBoxHeight by remember { mutableStateOf(130.dp) }

        AnimatedVisibility(
            visible = (gameState == ChessGameState.GAME_OVER),
            enter = scaleIn(
                animationSpec = tween(150, 150)
            )
        ) {

            Box(
                modifier = Modifier
                    .size(
                        width = popUpBoxWidth,
                        height = popUpBoxHeight
                    )
                    .background(
                        color = Color.White
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = gameOverMessage,
                    fontSize = 20.sp
                )
            }
        }

        AnimatedVisibility(
            visible = (
                    gameState == ChessGameState.PLAYER1_PROMOTE ||
                    gameState == ChessGameState.PLAYER2_PROMOTE
                    ),
        ) {
            val color by rememberSaveable {
                mutableStateOf(if (currentTurn == PieceColor.BLACK) PieceColor.WHITE else PieceColor.BLACK)
            }
            val listOfPromotion = getPromotionKeyWordFromColor(color)
            Box(
                modifier = Modifier
                    .size(
                        width = popUpBoxWidth,
                        height = popUpBoxHeight
                    ),
                contentAlignment = Alignment.Center
            ) {
                Column {
                    Text(
                        text = "PROMOTION",
                        modifier = Modifier.padding(5.dp),
                        fontSize = 25.sp,
                        style = TextStyle(
                            color = Color.Yellow
                        )
                    )

                    LazyRow {
                        items(listOfPromotion) { promotionOption ->
                            PromotionIcon(
                                chessBoardViewModel = chessBoardViewModel,
                                keyWord = promotionOption
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TileUI(
    tile: Tile,
    tileUiManager: TileUiManager,
    chessBoardViewModel: ChessBoardViewModel,
    theme: TileTheme,
    boardType: BoardType
) {
    val width by remember {
        mutableDoubleStateOf(
            when (boardType) {
                BoardType.BIG -> tileUiManager.bigTileWidth
                else -> tileUiManager.tileWidth
            }
        )
    }

    val height by remember {
        mutableIntStateOf(
            when (boardType) {
                BoardType.BIG -> tileUiManager.bigTileHeight
                else -> tileUiManager.tileHeight
            }
        )
    }

    val imageId by remember {
        mutableIntStateOf(getTileImage(tile.color, theme))
    }
    Box(
        modifier = Modifier.wrapContentSize(),
    ) {
        Image(
            painter =  painterResource(id = imageId),
            contentDescription = null,
            modifier = Modifier
                .size(
                    width = width.dp,
                    height = height.toDouble().dp
                )

        )

        if(tile.chessPiece != null) {
            Image(
                painter = painterResource(id = getChessPieceImage(tile.chessPiece!!)),
                contentDescription = null,
                modifier = Modifier
                    .size(
                        width = width.dp,
                        height = height.toDouble().dp
                    )
                    .clickable { chessBoardViewModel.onClickPieces(tile) }
            )
        }

        if (tile.isAPossibleMove) {
            Image(
                painter = painterResource(id = R.drawable.target_border_default),
                contentDescription = null,
                modifier = Modifier
                    .size(
                        width = width.dp,
                        height = height.toDouble().dp
                    )
                    .clickable { chessBoardViewModel.onClickTargeted(tile) }
            )
        }
    }
}

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

@Composable
fun PromotionIcon(
    chessBoardViewModel: ChessBoardViewModel,
    keyWord: ChessPieceKeyWord
) {
    Image(
        painter = painterResource(id = getChessPieceImage(getChessPieceFromKeyWord(keyWord))),
        contentDescription = null,
        modifier = Modifier
            .clickable {
                chessBoardViewModel.promotePawn(keyWord)
            }
            .size(60.dp)
    )
}