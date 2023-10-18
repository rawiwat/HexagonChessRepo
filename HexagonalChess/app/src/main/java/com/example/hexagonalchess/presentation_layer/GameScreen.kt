package com.example.hexagonalchess.presentation_layer

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hexagonalchess.R
import com.example.hexagonalchess.data_layer.model.tile.ChessboardData
import com.example.hexagonalchess.data_layer.model.tile.Tile
import com.example.hexagonalchess.domain_layer.TileUiManager
import com.example.hexagonalchess.domain_layer.getChessPieceImage
import com.example.hexagonalchess.domain_layer.getTileImage
import com.example.hexagonalchess.presentation_layer.viewmodel.ChessBoardViewModel

@Composable
fun GameScreen(chessBoardViewModel: ChessBoardViewModel) {

    val chessBoard by chessBoardViewModel.chessBoard.collectAsState()

    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        ChessBoardUI(
            chessBoardData = chessBoard,
            chessBoardViewModel = chessBoardViewModel
        )

        Button(
            onClick = {
                for (tile in chessBoard) {
                    println("${tile.id} : ${ tile.isAPossibleMove }")
                }
            }
        ) {
            Text(text = "check board")
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
        Text(
            text = tile.id.toString(),
            style = TextStyle(
                color = Color.Red
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
    val columnA by remember { mutableStateOf(chessBoardData.subList(0,8)) }

    val columnB by remember { mutableStateOf(chessBoardData.subList(8,17)) }

    val columnC by remember { mutableStateOf(chessBoardData.subList(17,27)) }

    val columnD by remember { mutableStateOf(chessBoardData.subList(27,38)) }

    val columnE by remember { mutableStateOf(chessBoardData.subList(38,50)) }

    val columnF by remember { mutableStateOf(chessBoardData.subList(50,61)) }

    val columnG by remember { mutableStateOf(chessBoardData.subList(61,71)) }

    val columnH by remember { mutableStateOf(chessBoardData.subList(71,80)) }

    val columnI by remember { mutableStateOf(chessBoardData.subList(80,88)) }

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