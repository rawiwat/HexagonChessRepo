package com.example.hexagonalchess.presentation_layer.composeui.gameplay.local

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
import com.example.hexagonalchess.domain_layer.BoardType
import com.example.hexagonalchess.domain_layer.ChessSkin
import com.example.hexagonalchess.domain_layer.GameMode
import com.example.hexagonalchess.domain_layer.PieceColor
import com.example.hexagonalchess.domain_layer.TileTheme
import com.example.hexagonalchess.domain_layer.tile_ui_manager.TileUiManager
import com.example.hexagonalchess.presentation_layer.viewmodel.ChessBoardViewModel

@Composable
fun BigBoardUI(
    chessBoardData:List<Tile>,
    chessBoardViewModel: ChessBoardViewModel,
    theme: TileTheme,
    skin: ChessSkin,
    screenWidth: Int,
    tileUiManager: TileUiManager,
    boardType: BoardType,
    playerColor: PieceColor,
    gameMode: GameMode
) {
    var columnA = chessBoardData.subList(0,6)
    var columnB = chessBoardData.subList(6,13)
    var columnC = chessBoardData.subList(13,21)
    var columnD = chessBoardData.subList(21,30)
    var columnE = chessBoardData.subList(30,40)
    var columnF = chessBoardData.subList(40,51)
    var columnG = chessBoardData.subList(51,61)
    var columnH = chessBoardData.subList(61,70)
    var columnI = chessBoardData.subList(70,78)
    var columnJ = chessBoardData.subList(78,85)
    var columnK = chessBoardData.subList(85,91)

    if (gameMode != GameMode.LOCAL && playerColor == PieceColor.BLACK) {
        columnA = columnA.reversed()
        columnB = columnB.reversed()
        columnC = columnC.reversed()
        columnD = columnD.reversed()
        columnE = columnE.reversed()
        columnF = columnF.reversed()
        columnG = columnG.reversed()
        columnH = columnH.reversed()
        columnI = columnI.reversed()
        columnJ = columnJ.reversed()
        columnK = columnK.reversed()
    }
    Box(
        modifier = Modifier
            .wrapContentSize()
            .padding(start = (screenWidth / 60).dp)
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
                    theme = theme,
                    boardType = boardType,
                    skin = skin
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
                    theme = theme,
                    boardType = boardType,
                    skin = skin
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
                    theme = theme,
                    boardType = boardType,
                    skin = skin
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
                    theme = theme,
                    boardType = boardType,
                    skin = skin
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
                    theme = theme,
                    boardType = boardType,
                    skin = skin
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
                    theme = theme,
                    boardType = boardType,
                    skin = skin
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
                    theme = theme,
                    boardType = boardType,
                    skin = skin
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
                    theme = theme,
                    boardType = boardType,
                    skin = skin
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
                    theme = theme,
                    boardType = boardType,
                    skin = skin
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
                    theme = theme,
                    boardType = boardType,
                    skin = skin
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
                    theme = theme,
                    boardType = boardType,
                    skin = skin
                )
            }
        }
    }
}
