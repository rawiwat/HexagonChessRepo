package com.example.hexagonalchess.presentation_layer.composeui.gameplay

import android.content.Context
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.hexagonalchess.R
import com.example.hexagonalchess.data_layer.model.tile.Tile
import com.example.hexagonalchess.domain_layer.BoardType
import com.example.hexagonalchess.domain_layer.ChessPieceKeyWord
import com.example.hexagonalchess.domain_layer.ChessGameState
import com.example.hexagonalchess.domain_layer.PieceColor
import com.example.hexagonalchess.domain_layer.Route
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
    chessViewModel: ChessBoardViewModel,
    context: Context,
    boardType: BoardType,
    navController: NavController
) {
    val chessBoardViewModel = remember { chessViewModel}
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
    val turnOnBack by chessBoardViewModel.backMenu.collectAsState()

    DisposableEffect(Unit) {
        chessBoardViewModel.cpuStart()
        onDispose { }
    }
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
            listOfCapturedPiece = whiteCaptured,
            screenWidth = screenWidth.dp
        )

        PlayerUI(
            currentTurn = currentTurn,
            color = PieceColor.BLACK,
            chessBoardViewModel = chessBoardViewModel,
            listOfCapturedPiece = blackCaptured,
            screenWidth = screenWidth.dp
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
        contentAlignment = Alignment.Center
    ) {
        val popUpBoxWidth by remember { mutableStateOf(250.dp) }
        val popUpBoxHeight by remember { mutableStateOf(130.dp) }

        AnimatedVisibility(
            visible = (gameState == ChessGameState.GAME_OVER),
            enter = scaleIn(
                animationSpec = tween(150, 150)
            )
        ) {
            GameOverMenu(
                popUpBoxWidth =popUpBoxWidth,
                popUpBoxHeight = popUpBoxHeight,
                gameOverMessage = gameOverMessage,
                navController = navController
            )
        }

        AnimatedVisibility(
            visible = (
                    gameState == ChessGameState.PLAYER1_PROMOTE ||
                    gameState == ChessGameState.PLAYER2_PROMOTE
                    ),
        ) {
            PromoteMenu(
                width = popUpBoxWidth,
                height = popUpBoxHeight,
                chessBoardViewModel = chessBoardViewModel,
                currentTurn = currentTurn
            )
        }

        AnimatedVisibility(visible = turnOnBack) {
            BackMenu(
                navController = navController,
                chessBoardViewModel = chessBoardViewModel,
                width = popUpBoxWidth,
                height = popUpBoxHeight
            )
        }
    }

    BackHandler(
        onBack = {
            if (!turnOnBack) {
                chessBoardViewModel.turnOnBackMenu(true)
            }
        }
    )
}

@Composable
fun BackMenu(
    navController: NavController,
    chessBoardViewModel: ChessBoardViewModel,
    width: Dp,
    height: Dp
) {
    val buttonWidth by remember { mutableStateOf(width / 2) }
    val buttonHeight by remember { mutableStateOf(height / 3) }
    val textBoxHeight by remember { mutableStateOf(height * 2 / 3) }
    
    Box(
        modifier = Modifier
            .size(
                width = width,
                height = height
            )
    ) {
        Image(
            painter = painterResource(id = R.drawable.menu_button),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(
                    width = width,
                    height = height
                )
        )
        Column {
            Text(
                text ="Go back to the Main Menu?",
                modifier = Modifier
                    .size(
                        height = textBoxHeight,
                        width = width
                    ),
                textAlign = TextAlign.Center,
                lineHeight = (textBoxHeight/2).value.sp
            )
            Row {
                Box(
                    modifier = Modifier
                        .size(
                            width = buttonWidth,
                            height = buttonHeight
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.menu_button),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(
                                width = buttonWidth,
                                height = buttonHeight
                            )
                            .clickable {
                                navController.navigate(Route.main)
                            }
                    )

                    Text(
                        text = "Yes"
                    )
                }
                Canvas(
                    modifier = Modifier,
                    onDraw = {
                        drawLine(
                            color = Color.Black,
                            start = Offset(0f,0f),
                            end = Offset(0f, height.value),
                        )
                    }
                )
                Box(
                    modifier = Modifier
                        .size(
                            width = buttonWidth,
                            height = buttonHeight
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.menu_button),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(
                                width = buttonWidth,
                                height = buttonHeight
                            )
                            .clickable {
                                chessBoardViewModel.turnOnBackMenu(false)
                            }
                    )

                    Text(
                        text = "No"
                    )
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

@Composable
fun GameOverMenu(
    popUpBoxWidth: Dp,
    popUpBoxHeight: Dp,
    gameOverMessage: String,
    navController: NavController
) {
    val textBoxHeight = popUpBoxHeight / 4 * 3
    val backButtonHeight = popUpBoxHeight / 4

    Box(
        modifier = Modifier
            .size(
                width = popUpBoxWidth,
                height = popUpBoxHeight
            ),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.menu_button),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(
                    width = popUpBoxWidth,
                    height = popUpBoxHeight
                )
        )

        Column {
            Text(
                text = gameOverMessage,
                fontSize = 20.sp,
                modifier = Modifier
                    .size(
                        width = popUpBoxWidth,
                        height = textBoxHeight
                    )
            )

            Box(
                modifier = Modifier
                    .size(
                        width = popUpBoxWidth,
                        height = backButtonHeight
                    )
                    .clickable {
                        navController.navigate(Route.main)
                    },
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.menu_button),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(
                            width = popUpBoxWidth,
                            height = backButtonHeight
                        )
                )
                Text(text = "Back to Main Menu")
            }
        }
    }
}

@Composable
fun PromoteMenu(
    width: Dp,
    height: Dp,
    chessBoardViewModel: ChessBoardViewModel,
    currentTurn: PieceColor
) {

    val color by rememberSaveable { mutableStateOf(if (currentTurn == PieceColor.BLACK) PieceColor.WHITE else PieceColor.BLACK) }
    val listOfPromotion = getPromotionKeyWordFromColor(color)

    Box(
        modifier = Modifier
            .size(
                width = width,
                height = height
            )
            .background(color = Color.DarkGray),
        contentAlignment = Alignment.Center,
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