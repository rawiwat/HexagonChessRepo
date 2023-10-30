package com.example.hexagonalchess.presentation_layer.composeui

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.hexagonalchess.data_layer.chess_board_data.base.ChessboardData
import com.example.hexagonalchess.data_layer.chess_board_data.shuriken.ShurikenBoardData
import com.example.hexagonalchess.domain_layer.BoardType
import com.example.hexagonalchess.domain_layer.GameMode
import com.example.hexagonalchess.domain_layer.PieceColor
import com.example.hexagonalchess.domain_layer.Route
import com.example.hexagonalchess.presentation_layer.viewmodel.BoardSelectionViewModel
import com.example.hexagonalchess.presentation_layer.viewmodel.ChessBoardViewModel
import com.example.hexagonalchess.presentation_layer.viewmodel.ChessBoardVsCPUViewModel
import com.example.hexagonalchess.presentation_layer.viewmodel.SettingViewModel

@Composable
fun App(
    navController: NavHostController,
    settingViewModel: SettingViewModel,
    playerColor: PieceColor,
    context: Context
) {
    NavHost(
        navController = navController,
        startDestination = "Main"
    ) {
        composable(
            route = Route.main
        ) {
            MainMenu(navController = navController)
        }
        composable(
            route = "${Route.local}/{boardType}",
            arguments = listOf(navArgument("boardType") { type = NavType.EnumType(BoardType::class.java) })
        ) {
            val boardType = it.arguments?.getSerializable("boardType") as BoardType
            GameScreen(
                chessBoardViewModel = ChessBoardViewModel(
                    allTiles = when(boardType) {
                        BoardType.DEFAULT -> ChessboardData().allTiles
                        BoardType.STAR_CHESS -> ShurikenBoardData().allTiles
                    },
                    boardType, context
                ),
                context = context,
                boardType = boardType
            )
        }
        composable(
            route = Route.setting
        ) {
            SettingScreen(
                navController = navController,
                settingViewModel = settingViewModel
            )
        }
        composable(
            route = "${Route.vsCpu}/{boardType}",
            arguments = listOf(navArgument("boardType") { type = NavType.EnumType(BoardType::class.java) })
        ) {
            val boardType = it.arguments?.getSerializable("boardType") as BoardType
            PlayerVsCpuScreen(
                chessBoardVsCpuViewModel = ChessBoardVsCPUViewModel(
                    playerColor,
                    board = when(boardType){
                        BoardType.DEFAULT -> ChessboardData().allTiles
                        BoardType.STAR_CHESS -> ShurikenBoardData().allTiles
                    },
                    boardType, context
                ),
                context = context,
                playerColor = playerColor,
                boardType = boardType
            )
        }

        composable(
            route = "${Route.boardSelection}/{gameMode}",
            arguments = listOf(navArgument("gameMode") { type = NavType.EnumType(GameMode::class.java) })
        ) {
            BoardSelectionScreen(
                boardSelectionViewModel = BoardSelectionViewModel(),
                navController = navController,
                gameMode = it.arguments?.getSerializable("gameMode") as GameMode
            )
        }
    }
}