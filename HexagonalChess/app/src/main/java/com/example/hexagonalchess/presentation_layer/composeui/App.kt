package com.example.hexagonalchess.presentation_layer.composeui

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.hexagonalchess.domain_layer.BoardType
import com.example.hexagonalchess.domain_layer.PieceColor
import com.example.hexagonalchess.domain_layer.Route
import com.example.hexagonalchess.presentation_layer.viewmodel.ChessBoardViewModel
import com.example.hexagonalchess.presentation_layer.viewmodel.ChessBoardVsCPUViewModel
import com.example.hexagonalchess.presentation_layer.viewmodel.SettingViewModel

@Composable
fun App(
    navController: NavHostController,
    chessBoardViewModel: ChessBoardViewModel,
    settingViewModel: SettingViewModel,
    chessBoardVsCPUViewModel: ChessBoardVsCPUViewModel,
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
            route = Route.local,
            arguments = listOf(navArgument("boardType") { type = NavType.EnumType(BoardType::class.java) })
        ) {
            GameScreen(
                chessBoardViewModel = chessBoardViewModel,
                context = context,
                boardType = it.arguments?.getSerializable("boardType") as BoardType
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
        composable(route = Route.vsCpu) {
            PlayerVsCpuScreen(
                chessBoardVsCpuViewModel = chessBoardVsCPUViewModel,
                context = context,
                playerColor = playerColor
            )
        }
    }
}