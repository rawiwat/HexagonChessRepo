package com.example.hexagonalchess.presentation_layer.composeui.gameplay.online

import android.content.Context
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
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
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.hexagonalchess.R
import com.example.hexagonalchess.data_layer.model.tile.Tile
import com.example.hexagonalchess.domain_layer.BoardType
import com.example.hexagonalchess.domain_layer.ChessGameStateOnline
import com.example.hexagonalchess.domain_layer.ChessPieceKeyWord
import com.example.hexagonalchess.domain_layer.PieceColor
import com.example.hexagonalchess.domain_layer.Route
import com.example.hexagonalchess.domain_layer.TileTheme
import com.example.hexagonalchess.domain_layer.getChessPieceFromKeyWord
import com.example.hexagonalchess.domain_layer.getChessPieceImage
import com.example.hexagonalchess.domain_layer.getPromotionKeyWordFromColor
import com.example.hexagonalchess.domain_layer.getTileImage
import com.example.hexagonalchess.domain_layer.theme_setting.ThemeSharedPrefs
import com.example.hexagonalchess.domain_layer.tile_ui_manager.TileUiManager
import com.example.hexagonalchess.presentation_layer.composeui.gameplay.local.GameOverMenu
import com.example.hexagonalchess.presentation_layer.viewmodel.ChessMultiPlayerViewModel

@Composable
fun MultiplayerGameScreen(
    navController: NavController,
    boardType: BoardType,
    chessBoardViewModel: ChessMultiPlayerViewModel,
    context: Context
) {

    val chessBoard by chessBoardViewModel.chessBoard.collectAsState()
    val currentTurn by chessBoardViewModel.currentTurn.collectAsState()
    val playerCaptured by chessBoardViewModel.playerCaptured.collectAsState()
    val opponentCaptured by chessBoardViewModel.opponentCaptured.collectAsState()
    val gameState by chessBoardViewModel.gameState.collectAsState()
    val gameOverMessage by chessBoardViewModel.gameOverMessage.collectAsState()
    val theme by remember { mutableStateOf(ThemeSharedPrefs(context).getTheme()) }
    val localConfiguration = LocalConfiguration.current
    val screenWidth by remember { mutableIntStateOf(localConfiguration.screenWidthDp) }
    val tileUiManager by remember { mutableStateOf(TileUiManager(screenWidth)) }
    val playerColor by chessBoardViewModel.playerColor.collectAsState()
    val opponentColor by chessBoardViewModel.opponentColor.collectAsState()
    val turnOnBack by chessBoardViewModel.backMenu.collectAsState()
    val playerName by chessBoardViewModel.playerName.collectAsState()
    val opponentName by chessBoardViewModel.opponentName.collectAsState()

    LaunchedEffect(Unit) {
        chessBoardViewModel.updateNameAndColor()
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(color = Color.DarkGray)
            .fillMaxSize()
    ) {
        PlayerUIOnline(
            name = playerName,
            currentTurn = currentTurn,
            color = playerColor,
            chessBoardViewModel = chessBoardViewModel,
            listOfCapturedPiece = playerCaptured,
            screenWidth = screenWidth.dp
        )

        OpponentUIOnline(
            name = opponentName,
            currentTurn = currentTurn,
            color = opponentColor,
            chessBoardViewModel = chessBoardViewModel,
            listOfCapturedPiece = opponentCaptured,
            screenWidth = screenWidth.dp
        )

        Box(
            modifier = Modifier
                .fillMaxWidth(),
            //contentAlignment = Alignment.TopCenter
        ) {
            when(boardType) {
                BoardType.DEFAULT -> {
                    ChessBoardUIOnline(
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
                    StarBoardUIOnline(
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
                    ShafranChessBoardUIOnline(
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
                    BigBoardUIOnline(
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
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        val popUpBoxWidth by remember { mutableStateOf(250.dp) }
        val popUpBoxHeight by remember { mutableStateOf(130.dp) }

        AnimatedVisibility(
            visible = (gameState == ChessGameStateOnline.GAME_OVER),
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
                    gameState == ChessGameStateOnline.WHITE_PROMOTE ||
                            gameState == ChessGameStateOnline.BLACK_PROMOTE
                    ),
        ) {
            PromoteMenuOnline(
                width = popUpBoxWidth,
                height = popUpBoxHeight,
                chessBoardViewModel = chessBoardViewModel,
                color = when(gameState) {
                    ChessGameStateOnline.WHITE_PROMOTE -> PieceColor.WHITE
                    ChessGameStateOnline.BLACK_PROMOTE -> PieceColor.BLACK
                    ChessGameStateOnline.GAME_OVER -> PieceColor.BLACK
                    ChessGameStateOnline.OPEN -> PieceColor.BLACK
                }
            )
        }

        AnimatedVisibility(visible = turnOnBack) {
            BackMenuOnline(
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
fun BackMenuOnline(
    navController: NavController,
    chessBoardViewModel: ChessMultiPlayerViewModel,
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
            Box(
                modifier = Modifier
                    .size(width, textBoxHeight),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text ="Go back to the Main Menu?"
                )
            }

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
fun TileUIOnline(
    tile: Tile,
    tileUiManager: TileUiManager,
    chessBoardViewModel: ChessMultiPlayerViewModel,
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
fun PromotionIconOnline(
    chessBoardViewModel: ChessMultiPlayerViewModel,
    keyWord: ChessPieceKeyWord
) {
    Image(
        painter = painterResource(id = getChessPieceImage(getChessPieceFromKeyWord(keyWord))),
        contentDescription = null,
        modifier = Modifier
            .clickable {
                chessBoardViewModel.selectingTile?.let { chessBoardViewModel.promotePawn(keyWord, it.id) }
            }
            .size(60.dp)
    )
}


@Composable
fun PromoteMenuOnline(
    width: Dp,
    height: Dp,
    chessBoardViewModel: ChessMultiPlayerViewModel,
    color: PieceColor
) {
    val listOfPromotion = remember { getPromotionKeyWordFromColor(color) }

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
            Box {
                Text(
                    text = "PROMOTION",
                    modifier = Modifier
                        .padding(5.dp)
                        .fillMaxWidth(),
                    fontSize = 25.sp,
                    style = TextStyle(
                        color = Color.Yellow
                    ),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.ExtraBold
                )

                Text(
                    text = "PROMOTION",
                    modifier = Modifier
                        .padding(5.dp)
                        .fillMaxWidth(),
                    fontSize = 25.sp,
                    style = TextStyle(
                        color = Color.Black,
                        drawStyle = Stroke(
                            width = 1.5f,
                        )
                    ),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.ExtraBold
                )
            }

            LazyRow {
                items(listOfPromotion) { promotionOption ->
                    PromotionIconOnline(
                        chessBoardViewModel = chessBoardViewModel,
                        keyWord = promotionOption
                    )
                }
            }
        }
    }
}