package com.example.hexagonalchess.presentation_layer.composeui

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hexagonalchess.R
import com.example.hexagonalchess.data_layer.chess_board_data.base.ChessboardData
import com.example.hexagonalchess.data_layer.chess_board_data.starchess.ShurikenBoardData
import com.example.hexagonalchess.data_layer.model.pieces.ChessPiece
import com.example.hexagonalchess.data_layer.model.tile.Tile
import com.example.hexagonalchess.domain_layer.BoardType
import com.example.hexagonalchess.domain_layer.ChessPieceKeyWord
import com.example.hexagonalchess.domain_layer.GameStateVsCpu
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
import com.example.hexagonalchess.presentation_layer.viewmodel.ChessBoardVsCPUViewModel


@Composable
fun PlayerVsCpuScreen(
    chessBoardVsCpuViewModel: ChessBoardVsCPUViewModel,
    context: Context,
    playerColor: PieceColor,
    boardType: BoardType
) {
    val chessBoard by chessBoardVsCpuViewModel.chessBoard.collectAsState()
    val currentTurn by chessBoardVsCpuViewModel.currentTurn.collectAsState()
    val blackCaptured by chessBoardVsCpuViewModel.blackCaptured.collectAsState()
    val whiteCaptured by chessBoardVsCpuViewModel.whiteCaptured.collectAsState()
    val gameState by chessBoardVsCpuViewModel.gameState.collectAsState()
    val gameOverMessage by chessBoardVsCpuViewModel.gameOverMessage.collectAsState()
    val theme by remember { mutableStateOf(ThemeSharedPrefs(context).getTheme()) }
    val localConfiguration = LocalConfiguration.current
    val screenWidth by remember { mutableIntStateOf(localConfiguration.screenWidthDp) }
    val tileUiManager by remember { mutableStateOf(TileUiManager(screenWidth)) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(color = Color.DarkGray)
            .fillMaxSize()
    ) {
        PlayerVsCpuUI(
            currentTurn = currentTurn,
            color = PieceColor.WHITE,
            chessBoardVsCpuViewModel = chessBoardVsCpuViewModel,
            listOfCapturedPiece = whiteCaptured
        )

        PlayerVsCpuUI(
            currentTurn = currentTurn,
            color = PieceColor.BLACK,
            chessBoardVsCpuViewModel = chessBoardVsCpuViewModel,
            listOfCapturedPiece = blackCaptured
        )
        Box(
            modifier = Modifier
                .fillMaxWidth(),
            //contentAlignment = Alignment.TopCenter
        ) {
            when(boardType) {
                BoardType.DEFAULT -> {
                    ChessBoardUIVsCPU(
                        chessBoardData = chessBoard,
                        chessBoardVsCpuViewModel = chessBoardVsCpuViewModel,
                        theme = theme,
                        playerColor = playerColor,
                        tileUiManager = tileUiManager,
                        screenWidth = screenWidth
                    )
                }
                BoardType.STAR_CHESS -> {
                    StarBoardVsCpuUI(
                        chessBoardData = chessBoard,
                        chessBoardVsCpuViewModel = chessBoardVsCpuViewModel,
                        theme = theme,
                        playerColor = playerColor,
                        tileUiManager = tileUiManager,
                        screenWidth = screenWidth

                    )
                }

                BoardType.SHAFRAN -> {
                    ShafranChessBoardVsCpuUI(
                        chessBoardData = chessBoard,
                        chessBoardViewModel = chessBoardVsCpuViewModel,
                        theme = theme,
                        playerColor = playerColor,
                        tileUiManager = tileUiManager,
                        screenWidth = screenWidth
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
            visible = (gameState == GameStateVsCpu.GAME_OVER),
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
            visible = (gameState == GameStateVsCpu.PROMOTE),
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
                            PromotionIconVsCpu(
                                chessBoardVsCpuViewModel = chessBoardVsCpuViewModel,
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
fun TileUIVsCpu(
    tile: Tile,
    tileUiManager: TileUiManager,
    chessBoardVsCpuViewModel: ChessBoardVsCPUViewModel,
    theme: TileTheme
) {
    Box(
        modifier = Modifier.wrapContentSize(),
    ) {
        Image(
            painter =  painterResource(id = getTileImage(tile.color, theme)),
            contentDescription = null,
            modifier = Modifier
                .size(
                    width = tileUiManager.tileWidth.dp,
                    height = tileUiManager.tileHeight.dp
                )

        )

        if(tile.chessPiece != null) {
            Image(
                painter = painterResource(id = getChessPieceImage(tile.chessPiece!!)),
                contentDescription = null,
                modifier = Modifier
                    .size(
                        width = tileUiManager.tileWidth.dp,
                        height = tileUiManager.tileHeight.dp
                    )
                    .clickable { chessBoardVsCpuViewModel.onClickPieces(tile) }
            )
        }

        if (tile.isAPossibleMove) {
            Image(
                painter = painterResource(id = R.drawable.target_border_default),
                contentDescription = null,
                modifier = Modifier
                    .size(
                        width = tileUiManager.tileWidth.dp,
                        height = tileUiManager.tileHeight.dp
                    )
                    .clickable { chessBoardVsCpuViewModel.onClickTargeted(tile) }
            )
        }
    }
}

@Composable
fun ChessBoardUIVsCPU(
    chessBoardData:List<Tile>,
    chessBoardVsCpuViewModel: ChessBoardVsCPUViewModel,
    theme: TileTheme,
    playerColor: PieceColor,
    screenWidth: Int,
    tileUiManager: TileUiManager
) {
    var columnA = chessBoardData.subList(0,8)

    var columnB = chessBoardData.subList(8,17)

    var columnC = chessBoardData.subList(17,27)

    var columnD = chessBoardData.subList(27,38)

    var columnE = chessBoardData.subList(38,50)

    var columnF = chessBoardData.subList(50,61)

    var columnG = chessBoardData.subList(61,71)

    var columnH = chessBoardData.subList(71,80)

    var columnI = chessBoardData.subList(80,88)

    if (playerColor == PieceColor.BLACK) {
        columnA = columnA.reversed()
        columnB = columnB.reversed()
        columnC = columnC.reversed()
        columnD = columnD.reversed()
        columnE = columnE.reversed()
        columnF = columnF.reversed()
        columnG = columnG.reversed()
        columnH = columnH.reversed()
        columnI = columnI.reversed()
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = (screenWidth / 16).dp)
    ) {
        LazyColumn(
            modifier = Modifier
                .offset(
                    y = tileUiManager.columnAY.dp
                )
        ) {
            items(
                columnA,
                key = { it.id }
            ) {
                TileUIVsCpu(
                    tile = it,
                    tileUiManager = tileUiManager,
                    chessBoardVsCpuViewModel = chessBoardVsCpuViewModel,
                    theme = theme
                )
            }
        }

        LazyColumn(
            modifier = Modifier
                .offset(
                    x = tileUiManager.columnBX.dp,
                    y = tileUiManager.columnBY.dp
                )
        ) {
            items(
                columnB,
                key = { it.id }
            ) {
                TileUIVsCpu(
                    tile = it,
                    tileUiManager = tileUiManager,
                    chessBoardVsCpuViewModel = chessBoardVsCpuViewModel,
                    theme = theme
                )
            }
        }

        LazyColumn(
            modifier = Modifier
                .offset(
                    x = tileUiManager.columnCX.dp,
                    y = tileUiManager.columnCY.dp
                )
        ) {
            items(
                columnC,
                key = { it.id }
            ) {
                TileUIVsCpu(
                    tile = it,
                    tileUiManager = tileUiManager,
                    chessBoardVsCpuViewModel = chessBoardVsCpuViewModel,
                    theme = theme
                )
            }
        }

        LazyColumn(
            modifier = Modifier
                .offset(
                    x = tileUiManager.columnDX.dp,
                    y = tileUiManager.columnDY.dp
                )
        ) {
            items(
                columnD,
                key = { it.id }
            ) {
                TileUIVsCpu(
                    tile = it,
                    tileUiManager = tileUiManager,
                    chessBoardVsCpuViewModel = chessBoardVsCpuViewModel,
                    theme = theme
                )
            }
        }

        LazyColumn(
            modifier = Modifier
                .offset(
                    x = tileUiManager.columnEX.dp
                )
        ) {
            items(
                columnE,
                key = { it.id }
            ) {
                TileUIVsCpu(
                    tile = it,
                    tileUiManager = tileUiManager,
                    chessBoardVsCpuViewModel = chessBoardVsCpuViewModel,
                    theme = theme
                )
            }
        }

        LazyColumn(
            modifier = Modifier
                .offset(
                    x = tileUiManager.columnFX.dp,
                    y = tileUiManager.columnFY.dp
                )
        ) {
            items(
                columnF,
                key = { it.id }
            ) {
                TileUIVsCpu(
                    tile = it,
                    tileUiManager = tileUiManager,
                    chessBoardVsCpuViewModel = chessBoardVsCpuViewModel,
                    theme = theme
                )
            }
        }

        LazyColumn(
            modifier = Modifier
                .offset(
                    x = tileUiManager.columnGX.dp,
                    y = tileUiManager.columnGY.dp
                )
        ) {
            items(
                columnG,
                key = { it.id }
            ) {
                TileUIVsCpu(
                    tile = it,
                    tileUiManager = tileUiManager,
                    chessBoardVsCpuViewModel = chessBoardVsCpuViewModel,
                    theme = theme
                )
            }
        }

        LazyColumn(
            modifier = Modifier
                .offset(
                    x = tileUiManager.columnHX.dp,
                    y = tileUiManager.columnHY.dp
                )
        ) {
            items(
                columnH,
                key = { it.id }
            ) {
                TileUIVsCpu(
                    tile = it,
                    tileUiManager = tileUiManager,
                    chessBoardVsCpuViewModel = chessBoardVsCpuViewModel,
                    theme = theme
                )
            }
        }

        LazyColumn(
            modifier = Modifier
                .offset(
                    x = tileUiManager.columnIX.dp,
                    y = tileUiManager.columnIY.dp
                )
        ) {
            items(
                columnI,
                key = { it.id }
            ) {
                TileUIVsCpu(
                    tile = it,
                    tileUiManager = tileUiManager,
                    chessBoardVsCpuViewModel = chessBoardVsCpuViewModel,
                    theme = theme
                )
            }
        }
    }
}

@Composable
fun StarBoardVsCpuUI(
    chessBoardData:List<Tile>,
    chessBoardVsCpuViewModel: ChessBoardVsCPUViewModel,
    theme: TileTheme,
    playerColor: PieceColor,
    screenWidth: Int,
    tileUiManager: TileUiManager
) {
    var columnA = chessBoardData.subList(0,1)
    var columnB = chessBoardData.subList(1,3)
    var columnC = chessBoardData.subList(3,10)
    var columnD = chessBoardData.subList(10,16)
    var columnE = chessBoardData.subList(16,21)
    var columnF = chessBoardData.subList(21,27)
    var columnG = chessBoardData.subList(27,34)
    var columnH = chessBoardData.subList(34,36)
    var columnI = chessBoardData.subList(36,37)

    if (playerColor == PieceColor.BLACK) {
        columnA = columnA.reversed()
        columnB = columnB.reversed()
        columnC = columnC.reversed()
        columnD = columnD.reversed()
        columnE = columnE.reversed()
        columnF = columnF.reversed()
        columnG = columnG.reversed()
        columnH = columnH.reversed()
        columnI = columnI.reversed()
    }

    Box(
        modifier = Modifier
            .wrapContentSize()
            .padding(start = (screenWidth / 15).dp)
    ) {
        LazyColumn(
            modifier = Modifier
                .offset(y = tileUiManager.columnAYShuriken.dp)
        ) {
            items(
                columnA,
                key = { it.id }
            ) {
                TileUIVsCpu(
                    tile = it,
                    tileUiManager = tileUiManager,
                    chessBoardVsCpuViewModel = chessBoardVsCpuViewModel,
                    theme = theme
                )
            }
        }

        LazyColumn(
            modifier = Modifier
                .offset(
                    x = tileUiManager.columnBX.dp,
                    y = tileUiManager.columnBYShuriken.dp
                )
        ) {
            items(
                columnB,
                key = { it.id }
            ) {
                TileUIVsCpu(
                    tile = it,
                    tileUiManager = tileUiManager,
                    chessBoardVsCpuViewModel = chessBoardVsCpuViewModel,
                    theme = theme
                )
            }
        }

        LazyColumn(
            modifier = Modifier
                .offset(
                    x = tileUiManager.columnCX.dp
                )
        ) {
            items(
                columnC,
                key = { it.id }
            ) {
                TileUIVsCpu(
                    tile = it,
                    tileUiManager = tileUiManager,
                    chessBoardVsCpuViewModel = chessBoardVsCpuViewModel,
                    theme = theme
                )
            }
        }

        LazyColumn(
            modifier = Modifier
                .offset(
                    x = tileUiManager.columnDX.dp,
                    y = tileUiManager.columnDYShuriken.dp
                )
        ) {
            items(
                columnD,
                key = { it.id }
            ) {
                TileUIVsCpu(
                    tile = it,
                    tileUiManager = tileUiManager,
                    chessBoardVsCpuViewModel = chessBoardVsCpuViewModel,
                    theme = theme
                )
            }
        }

        LazyColumn(
            modifier = Modifier
                .offset(
                    x = tileUiManager.columnEX.dp,
                    y = tileUiManager.columnEYShuriken.dp
                )
        ) {
            items(
                columnE,
                key = { it.id }
            ) {
                TileUIVsCpu(
                    tile = it,
                    tileUiManager = tileUiManager,
                    chessBoardVsCpuViewModel = chessBoardVsCpuViewModel,
                    theme = theme
                )
            }
        }

        LazyColumn(
            modifier = Modifier
                .offset(
                    x = tileUiManager.columnFX.dp,
                    y = tileUiManager.columnFYShuriken.dp
                )
        ) {
            items(
                columnF,
                key = { it.id }
            ) {
                TileUIVsCpu(
                    tile = it,
                    tileUiManager = tileUiManager,
                    chessBoardVsCpuViewModel = chessBoardVsCpuViewModel,
                    theme = theme
                )
            }
        }

        LazyColumn(
            modifier = Modifier
                .offset(
                    x = tileUiManager.columnGX.dp
                )
        ) {
            items(
                columnG,
                key = { it.id }
            ) {
                TileUIVsCpu(
                    tile = it,
                    tileUiManager = tileUiManager,
                    chessBoardVsCpuViewModel = chessBoardVsCpuViewModel,
                    theme = theme
                )
            }
        }

        LazyColumn(
            modifier = Modifier
                .offset(
                    x = tileUiManager.columnHX.dp,
                    y = tileUiManager.columnHYShuriken.dp
                )
        ) {
            items(
                columnH,
                key = { it.id }
            ) {
                TileUIVsCpu(
                    tile = it,
                    tileUiManager = tileUiManager,
                    chessBoardVsCpuViewModel = chessBoardVsCpuViewModel,
                    theme = theme
                )
            }
        }

        LazyColumn(
            modifier = Modifier
                .offset(
                    x = tileUiManager.columnIX.dp,
                    y = tileUiManager.columnIYShuriken.dp
                )
        ) {
            items(
                columnI,
                key = { it.id }
            ) {
                TileUIVsCpu(
                    tile = it,
                    tileUiManager = tileUiManager,
                    chessBoardVsCpuViewModel = chessBoardVsCpuViewModel,
                    theme = theme
                )
            }
        }
    }
}

@Composable
fun PlayerVsCpuUI(
    currentTurn: PieceColor,
    color: PieceColor,
    chessBoardVsCpuViewModel: ChessBoardVsCPUViewModel,
    listOfCapturedPiece: List<ChessPiece>
) {
    val borderWidth = if (currentTurn == color) { 6.dp } else { 0.dp }

    val currentAdvantage by if (color == PieceColor.BLACK) {
        chessBoardVsCpuViewModel.blackAdvantage.collectAsState()
    } else {
        chessBoardVsCpuViewModel.whiteAdvantage.collectAsState()
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
fun ShafranChessBoardVsCpuUI(
    chessBoardData:List<Tile>,
    chessBoardViewModel: ChessBoardVsCPUViewModel,
    theme: TileTheme,
    playerColor: PieceColor,
    tileUiManager: TileUiManager,
    screenWidth: Int
) {
    var columnA = chessBoardData.subList(0,6)

    var columnB = chessBoardData.subList(6,13)

    var columnC = chessBoardData.subList(13,21)

    var columnD = chessBoardData.subList(21,30)

    var columnE = chessBoardData.subList(30,40)

    var columnF = chessBoardData.subList(40,49)

    var columnG = chessBoardData.subList(49,57)

    var columnH = chessBoardData.subList(57,64)

    var columnI = chessBoardData.subList(64,70)
    if (playerColor == PieceColor.BLACK) {
        columnA = columnA.reversed()
        columnB = columnB.reversed()
        columnC = columnC.reversed()
        columnD = columnD.reversed()
        columnE = columnE.reversed()
        columnF = columnF.reversed()
        columnG = columnG.reversed()
        columnH = columnH.reversed()
        columnI = columnI.reversed()
    }
    Box(
        modifier = Modifier
            .wrapContentSize()
            .padding(start = (screenWidth / 15).dp)
    ) {
        LazyColumn(
            modifier = Modifier
                .offset(y = tileUiManager.columnAY.dp)
        ) {
            items(
                columnA,
                key = { it.id }
            ) {
                TileUIVsCpu(
                    tile = it,
                    tileUiManager = tileUiManager,
                    chessBoardVsCpuViewModel = chessBoardViewModel,
                    theme = theme
                )
            }
        }

        LazyColumn(
            modifier = Modifier
                .offset(
                    x = tileUiManager.columnBX.dp,
                    y = tileUiManager.columnBY.dp
                )
        ) {
            items(
                columnB,
                key = { it.id }
            ) {
                TileUIVsCpu(
                    tile = it,
                    tileUiManager = tileUiManager,
                    chessBoardVsCpuViewModel = chessBoardViewModel,
                    theme = theme
                )
            }
        }

        LazyColumn(
            modifier = Modifier
                .offset(
                    x = tileUiManager.columnCX.dp,
                    y = tileUiManager.columnCY.dp
                )
        ) {
            items(
                columnC,
                key = { it.id }
            ) {
                TileUIVsCpu(
                    tile = it,
                    tileUiManager = tileUiManager,
                    chessBoardVsCpuViewModel = chessBoardViewModel,
                    theme = theme
                )
            }
        }

        LazyColumn(
            modifier = Modifier
                .offset(
                    x = tileUiManager.columnDX.dp,
                    y = tileUiManager.columnDY.dp
                )
        ) {
            items(
                columnD,
                key = { it.id }
            ) {
                TileUIVsCpu(
                    tile = it,
                    tileUiManager = tileUiManager,
                    chessBoardVsCpuViewModel = chessBoardViewModel,
                    theme = theme
                )
            }
        }

        LazyColumn(
            modifier = Modifier
                .offset(
                    x = tileUiManager.columnEX.dp
                )
        ) {
            items(
                columnE,
                key = { it.id }
            ) {
                TileUIVsCpu(
                    tile = it,
                    tileUiManager = tileUiManager,
                    chessBoardVsCpuViewModel = chessBoardViewModel,
                    theme = theme
                )
            }
        }

        LazyColumn(
            modifier = Modifier
                .offset(
                    x = tileUiManager.columnFX.dp,
                    y = tileUiManager.columnFY.dp
                )
        ) {
            items(
                columnF,
                key = { it.id }
            ) {
                TileUIVsCpu(
                    tile = it,
                    tileUiManager = tileUiManager,
                    chessBoardVsCpuViewModel = chessBoardViewModel,
                    theme = theme
                )
            }
        }

        LazyColumn(
            modifier = Modifier
                .offset(
                    x = tileUiManager.columnGX.dp,
                    y = tileUiManager.columnGY.dp
                )
        ) {
            items(
                columnG,
                key = { it.id }
            ) {
                TileUIVsCpu(
                    tile = it,
                    tileUiManager = tileUiManager,
                    chessBoardVsCpuViewModel = chessBoardViewModel,
                    theme = theme
                )
            }
        }

        LazyColumn(
            modifier = Modifier
                .offset(
                    x = tileUiManager.columnHX.dp,
                    y = tileUiManager.columnHY.dp
                )
        ) {
            items(
                columnH,
                key = { it.id }
            ) {
                TileUIVsCpu(
                    tile = it,
                    tileUiManager = tileUiManager,
                    chessBoardVsCpuViewModel = chessBoardViewModel,
                    theme = theme
                )
            }
        }

        LazyColumn(
            modifier = Modifier
                .offset(
                    x = tileUiManager.columnIX.dp,
                    y = tileUiManager.columnIY.dp
                )
        ) {
            items(
                columnI,
                key = { it.id }
            ) {
                TileUIVsCpu(
                    tile = it,
                    tileUiManager = tileUiManager,
                    chessBoardVsCpuViewModel = chessBoardViewModel,
                    theme = theme
                )
            }
        }
    }
}

@Composable
fun PromotionIconVsCpu(
    chessBoardVsCpuViewModel: ChessBoardVsCPUViewModel,
    keyWord: ChessPieceKeyWord
) {
    Image(
        painter = painterResource(id = getChessPieceImage(getChessPieceFromKeyWord(keyWord))),
        contentDescription = null,
        modifier = Modifier
            .clickable {
                chessBoardVsCpuViewModel.playerPromotePawn(keyWord)
            }
            .size(60.dp)
    )
}

@Preview
@Composable
fun ChessBoardPreviewPovWhite() {
    val board = ChessboardData().allTiles
    PlayerVsCpuScreen(
        chessBoardVsCpuViewModel = ChessBoardVsCPUViewModel(
            PieceColor.WHITE,
            board,BoardType.DEFAULT,LocalContext.current
        ),
        context = LocalContext.current,
        playerColor = PieceColor.WHITE,
        boardType = BoardType.DEFAULT
    )
}

@Preview
@Composable
fun ChessBoardPreviewPovBlack() {
    val board = ChessboardData().allTiles
    PlayerVsCpuScreen(
        chessBoardVsCpuViewModel = ChessBoardVsCPUViewModel(
            PieceColor.BLACK,
            board,BoardType.DEFAULT,LocalContext.current
        ),
        context = LocalContext.current,
        playerColor = PieceColor.BLACK,
        boardType = BoardType.DEFAULT
    )
}

@Preview
@Composable
fun ShurikenBoardPreviewPovWhite() {
    val board = ShurikenBoardData().allTiles
    PlayerVsCpuScreen(
        chessBoardVsCpuViewModel = ChessBoardVsCPUViewModel(
            PieceColor.WHITE,
            board,BoardType.STAR_CHESS,LocalContext.current
        ),
        context = LocalContext.current,
        playerColor = PieceColor.WHITE,
        boardType = BoardType.STAR_CHESS
    )
}

@Preview
@Composable
fun ShurikenBoardPreviewPovBlack() {
    val board = ShurikenBoardData().allTiles
    PlayerVsCpuScreen(
        chessBoardVsCpuViewModel = ChessBoardVsCPUViewModel(
            PieceColor.BLACK,
            board,BoardType.STAR_CHESS,LocalContext.current
            ),
        context = LocalContext.current,
        playerColor = PieceColor.BLACK,
        boardType = BoardType.STAR_CHESS
    )
}