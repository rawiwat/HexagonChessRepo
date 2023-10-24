package com.example.hexagonalchess.presentation_layer.composeui

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.hexagonalchess.domain_layer.Route
import com.example.hexagonalchess.presentation_layer.viewmodel.ChessBoardViewModel
import com.example.hexagonalchess.presentation_layer.viewmodel.SettingViewModel

@Composable
fun App(
    navController: NavHostController,
    chessBoardViewModel: ChessBoardViewModel,
    settingViewModel: SettingViewModel,
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
            route = Route.local
        ) {
            GameScreen(
                chessBoardViewModel = chessBoardViewModel,
                context = context
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
    }
}