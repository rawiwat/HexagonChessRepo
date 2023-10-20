package com.example.hexagonalchess.presentation_layer

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
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
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hexagonalchess.R
import com.example.hexagonalchess.data_layer.chess_board_data.ChessboardData
import com.example.hexagonalchess.data_layer.model.pieces.ChessPiece
import com.example.hexagonalchess.data_layer.model.tile.Tile
import com.example.hexagonalchess.domain_layer.ChessPieceKeyWord
import com.example.hexagonalchess.domain_layer.PieceColor
import com.example.hexagonalchess.domain_layer.TileUiManager
import com.example.hexagonalchess.domain_layer.getChessPieceFromKeyWord
import com.example.hexagonalchess.domain_layer.getChessPieceImage
import com.example.hexagonalchess.domain_layer.getTileImage
import com.example.hexagonalchess.presentation_layer.viewmodel.ChessBoardViewModel

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun GameScreen(chessBoardViewModel: ChessBoardViewModel) {

    val chessBoard by chessBoardViewModel.chessBoard.collectAsState()
    val currentTurn by chessBoardViewModel.currentTurn.collectAsState()
    val blackCaptured by chessBoardViewModel.blackCaptured.collectAsState()
    val whiteCaptured by chessBoardViewModel.whiteCaptured.collectAsState()
    val gameOverState by chessBoardViewModel.gameOverState.collectAsState()
    val gameOverMessage by chessBoardViewModel.gameOverMessage.collectAsState()

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        PlayerUI(
            alignment = Alignment.CenterStart,
            currentTurn = currentTurn,
            color = PieceColor.WHITE,
            chessBoardViewModel = chessBoardViewModel,
            listOfCapturedPiece = whiteCaptured
        )

        PlayerUI(
            alignment = Alignment.CenterEnd,
            currentTurn = currentTurn,
            color = PieceColor.BLACK,
            chessBoardViewModel = chessBoardViewModel,
            listOfCapturedPiece = blackCaptured
        )

        ChessBoardUI(
            chessBoardData = chessBoard,
            chessBoardViewModel = chessBoardViewModel
        )

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
        AnimatedVisibility(
            visible = gameOverState,
            enter = scaleIn(
                animationSpec = tween(150, 150)
            )
        ) {

            Box(
                modifier = Modifier
                    .size(
                        width = 250.dp,
                        height = 130.dp
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
    }

}

@Composable
fun TileUI(
    tile: Tile,
    tileUiManager: TileUiManager,
    chessBoardViewModel: ChessBoardViewModel
) {
    Box(
        modifier = Modifier.wrapContentSize(),
    ) {
        Image(
            painter = painterResource(id = getTileImage(tile.color)),
            contentDescription = null,
            modifier = Modifier
                .size(
                    width = tileUiManager.tileWidth.dp,
                    height = tileUiManager.tileHeight.dp
                )

        )

        /*Text(
            text = tile.id.toString(),
            style = TextStyle(
                color = Color.Red
            )
        )*/

        if(tile.chessPiece != null) {
            Image(
                painter = painterResource(id = getChessPieceImage(tile.chessPiece!!)),
                contentDescription = null,
                modifier = Modifier
                    .size(
                        width = tileUiManager.tileWidth.dp,
                        height = tileUiManager.tileHeight.dp
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
                        width = tileUiManager.tileWidth.dp,
                        height = tileUiManager.tileHeight.dp
                    )
                    .clickable { chessBoardViewModel.onClickTargeted(tile) }
            )
        }
    }
}

@Composable
fun ChessBoardUI(
    chessBoardData:List<Tile>,
    chessBoardViewModel: ChessBoardViewModel
) {
    val columnA = chessBoardData.subList(0,8)

    val columnB = chessBoardData.subList(8,17)

    val columnC = chessBoardData.subList(17,27)

    val columnD = chessBoardData.subList(27,38)

    val columnE = chessBoardData.subList(38,50)

    val columnF = chessBoardData.subList(50,61)

    val columnG = chessBoardData.subList(61,71)

    val columnH = chessBoardData.subList(71,80)

    val columnI = chessBoardData.subList(80,88)

    val tileUiManager = TileUiManager()

    Box(
        modifier = Modifier.fillMaxWidth(),
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
                TileUI(
                    tile = it,
                    tileUiManager = tileUiManager,
                    chessBoardViewModel = chessBoardViewModel
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
                TileUI(
                    tile = it,
                    tileUiManager = tileUiManager,
                    chessBoardViewModel = chessBoardViewModel
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
                TileUI(
                    tile = it,
                    tileUiManager = tileUiManager,
                    chessBoardViewModel = chessBoardViewModel
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
                TileUI(
                    tile = it,
                    tileUiManager = tileUiManager,
                    chessBoardViewModel = chessBoardViewModel

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
                TileUI(
                    tile = it,
                    tileUiManager = tileUiManager,
                    chessBoardViewModel = chessBoardViewModel
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
                TileUI(
                    tile = it,
                    tileUiManager = tileUiManager,
                    chessBoardViewModel = chessBoardViewModel
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
                TileUI(
                    tile = it,
                    tileUiManager = tileUiManager,
                    chessBoardViewModel = chessBoardViewModel
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
                TileUI(
                    tile = it,
                    tileUiManager = tileUiManager,
                    chessBoardViewModel = chessBoardViewModel
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
                TileUI(
                    tile = it,
                    tileUiManager = tileUiManager,
                    chessBoardViewModel = chessBoardViewModel
                )
            }
        }
    }
}

@Composable
fun PlayerUI(
    alignment: Alignment,
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

    Surface(
        border = BorderStroke(borderWidth,color = Color.White),
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
    ) {
        Box(
            contentAlignment = alignment,
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
                        LazyRow {
                            items(listOfCapturedPiece) {
                                Image(
                                    painter = painterResource(id = getChessPieceImage(it)),
                                    contentDescription = null,
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                        }
                        Text(text = if (currentAdvantage >= 1) "+$currentAdvantage" else "")
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun GameScreenPreview() {
    val allTiles = ChessboardData().allTiles
    val chessBoardViewModel = ChessBoardViewModel(
        allTiles,
        //FirebaseRealtimeDatabase()
    )
    GameScreen(chessBoardViewModel)
}

@Preview
@Composable
fun PlayerPreview() {
    PlayerUI(
        alignment = Alignment.CenterStart,
        currentTurn = PieceColor.BLACK,
        color = PieceColor.BLACK,
        chessBoardViewModel = ChessBoardViewModel(ChessboardData().allTiles),
        listOfCapturedPiece = listOf(
            getChessPieceFromKeyWord(ChessPieceKeyWord.WHITE_PAWN),
            getChessPieceFromKeyWord(ChessPieceKeyWord.WHITE_PAWN),
            getChessPieceFromKeyWord(ChessPieceKeyWord.WHITE_BISHOP),
            getChessPieceFromKeyWord(ChessPieceKeyWord.WHITE_ROOK)
        )
    )
}