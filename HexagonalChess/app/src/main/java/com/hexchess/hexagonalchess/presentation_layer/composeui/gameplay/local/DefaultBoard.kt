package com.hexchess.hexagonalchess.presentation_layer.composeui.gameplay.local

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hexchess.hexagonalchess.data_layer.model.tile.Tile
import com.hexchess.hexagonalchess.domain_layer.BoardType
import com.hexchess.hexagonalchess.domain_layer.ChessSkin
import com.hexchess.hexagonalchess.domain_layer.GameMode
import com.hexchess.hexagonalchess.domain_layer.PieceColor
import com.hexchess.hexagonalchess.domain_layer.TileTheme
import com.hexchess.hexagonalchess.domain_layer.tile_ui_manager.TileUiManager
import com.hexchess.hexagonalchess.presentation_layer.viewmodel.ChessBoardViewModel


@Composable
fun ChessBoardUI(
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
    var columnA = chessBoardData.subList(0,8)
    var columnB = chessBoardData.subList(8,17)
    var columnC = chessBoardData.subList(17,27)
    var columnD = chessBoardData.subList(27,38)
    var columnE = chessBoardData.subList(38,50)
    var columnF = chessBoardData.subList(50,61)
    var columnG = chessBoardData.subList(61,71)
    var columnH = chessBoardData.subList(71,80)
    var columnI = chessBoardData.subList(80,88)

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
                    chessBoardViewModel = chessBoardViewModel,
                    theme = theme,
                    boardType = boardType,
                    skin = skin
                )
            }
        }
    }
}
