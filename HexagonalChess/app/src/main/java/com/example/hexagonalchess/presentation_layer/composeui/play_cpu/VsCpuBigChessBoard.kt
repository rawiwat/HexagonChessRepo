package com.example.hexagonalchess.presentation_layer.composeui.play_cpu

import androidx.compose.runtime.Composable
import com.example.hexagonalchess.data_layer.model.tile.Tile
import com.example.hexagonalchess.domain_layer.PieceColor
import com.example.hexagonalchess.domain_layer.TileTheme
import com.example.hexagonalchess.domain_layer.tile_ui_manager.TileUiManager
import com.example.hexagonalchess.presentation_layer.viewmodel.ChessBoardVsCPUViewModel

@Composable
fun BigChessBoardVsCpuUi(
    chessBoardData:List<Tile>,
    chessBoardViewModel: ChessBoardVsCPUViewModel,
    theme: TileTheme,
    playerColor: PieceColor,
    tileUiManager: TileUiManager,
    screenWidth: Int
) {

}