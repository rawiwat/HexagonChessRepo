package com.example.hexagonalchess.presentation_layer.composeui.play_local

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.hexagonalchess.data_layer.model.tile.Tile
import com.example.hexagonalchess.domain_layer.TileTheme
import com.example.hexagonalchess.domain_layer.tile_ui_manager.TileUiManager
import com.example.hexagonalchess.presentation_layer.viewmodel.ChessBoardViewModel

@Composable
fun BigBoardUI(
    chessBoardData:List<Tile>,
    chessBoardViewModel: ChessBoardViewModel,
    theme: TileTheme,
    screenWidth: Int,
    tileUiManager: TileUiManager
) {
    val columnA = chessBoardData.subList(0,6)

    val columnB = chessBoardData.subList(6,13)

    val columnC = chessBoardData.subList(13,21)

    val columnD = chessBoardData.subList(21,30)

    val columnE = chessBoardData.subList(30,40)

    val columnF = chessBoardData.subList(40,51)

    val columnG = chessBoardData.subList(51,61)

    val columnH = chessBoardData.subList(61,70)

    val columnI = chessBoardData.subList(70,78)

    val columnJ = chessBoardData.subList(78,85)

    val columnK = chessBoardData.subList(85,91)

    Box(
        modifier = Modifier
            .wrapContentSize()
    ) {
        LazyColumn(
            modifier = Modifier
                .offset(y = tileUiManager.bigColumnAY.dp)
        ) {
            items(
                columnA,
                key = { it.id }
            ) {
                TileUI(
                    tile = it,
                    tileUiManager = tileUiManager,
                    chessBoardViewModel = chessBoardViewModel,
                    theme = theme
                )
            }
        }

        LazyColumn(
            modifier = Modifier
                .offset(
                    x = tileUiManager.bigColumnBX.dp,
                    y = tileUiManager.bigColumnBY.dp
                )
        ) {
            items(
                columnB,
                key = { it.id }
            ) {
                TileUI(
                    tile = it,
                    tileUiManager = tileUiManager,
                    chessBoardViewModel = chessBoardViewModel,
                    theme = theme
                )
            }
        }

        LazyColumn(
            modifier = Modifier
                .offset(
                    x = tileUiManager.bigColumnCX.dp,
                    y = tileUiManager.bigColumnCY.dp
                )
        ) {
            items(
                columnC,
                key = { it.id }
            ) {
                TileUI(
                    tile = it,
                    tileUiManager = tileUiManager,
                    chessBoardViewModel = chessBoardViewModel,
                    theme = theme
                )
            }
        }

        LazyColumn(
            modifier = Modifier
                .offset(
                    x = tileUiManager.bigColumnDX.dp,
                    y = tileUiManager.bigColumnDY.dp
                )
        ) {
            items(
                columnD,
                key = { it.id }
            ) {
                TileUI(
                    tile = it,
                    tileUiManager = tileUiManager,
                    chessBoardViewModel = chessBoardViewModel,
                    theme = theme
                )
            }
        }

        LazyColumn(
            modifier = Modifier
                .offset(
                    x = tileUiManager.bigColumnEX.dp,
                    y = tileUiManager.bigColumnEY.dp
                )
        ) {
            items(
                columnE,
                key = { it.id }
            ) {
                TileUI(
                    tile = it,
                    tileUiManager = tileUiManager,
                    chessBoardViewModel = chessBoardViewModel,
                    theme = theme
                )
            }
        }

        LazyColumn(
            modifier = Modifier
                .offset(
                    x = tileUiManager.bigColumnFX.dp
                )
        ) {
            items(
                columnF,
                key = { it.id }
            ) {
                TileUI(
                    tile = it,
                    tileUiManager = tileUiManager,
                    chessBoardViewModel = chessBoardViewModel,
                    theme = theme
                )
            }
        }

        LazyColumn(
            modifier = Modifier
                .offset(
                    x = tileUiManager.bigColumnGX.dp,
                    y = tileUiManager.bigColumnGY.dp
                )
        ) {
            items(
                columnG,
                key = { it.id }
            ) {
                TileUI(
                    tile = it,
                    tileUiManager = tileUiManager,
                    chessBoardViewModel = chessBoardViewModel,
                    theme = theme
                )
            }
        }

        LazyColumn(
            modifier = Modifier
                .offset(
                    x = tileUiManager.bigColumnHX.dp,
                    y = tileUiManager.bigColumnHY.dp
                )
        ) {
            items(
                columnH,
                key = { it.id }
            ) {
                TileUI(
                    tile = it,
                    tileUiManager = tileUiManager,
                    chessBoardViewModel = chessBoardViewModel,
                    theme = theme
                )
            }
        }

        LazyColumn(
            modifier = Modifier
                .offset(
                    x = tileUiManager.bigColumnIX.dp,
                    y = tileUiManager.bigColumnIY.dp
                )
        ) {
            items(
                columnI,
                key = { it.id }
            ) {
                TileUI(
                    tile = it,
                    tileUiManager = tileUiManager,
                    chessBoardViewModel = chessBoardViewModel,
                    theme = theme
                )
            }
        }
        LazyColumn(
            modifier = Modifier
                .offset(
                    x = tileUiManager.bigColumnJX.dp,
                    y = tileUiManager.bigColumnJY.dp
                )
        ) {
            items(
                columnJ,
                key = { it.id }
            ) {
                TileUI(
                    tile = it,
                    tileUiManager = tileUiManager,
                    chessBoardViewModel = chessBoardViewModel,
                    theme = theme
                )
            }
        }
        LazyColumn(
            modifier = Modifier
                .offset(
                    x = tileUiManager.bigColumnKX.dp,
                    y = tileUiManager.bigColumnKY.dp
                )
        ) {
            items(
                columnK,
                key = { it.id }
            ) {
                TileUI(
                    tile = it,
                    tileUiManager = tileUiManager,
                    chessBoardViewModel = chessBoardViewModel,
                    theme = theme
                )
            }
        }
    }
}
