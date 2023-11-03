package com.example.hexagonalchess

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.hexagonalchess.domain_layer.PieceColor
import com.example.hexagonalchess.presentation_layer.composeui.App
import com.example.hexagonalchess.presentation_layer.viewmodel.SettingViewModel
import com.example.hexagonalchess.ui.theme.HexagonalChessTheme

class MainActivity : ComponentActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //val allTiles = ChessboardData().allTiles
        //val chessBoardViewModel = ChessBoardViewModel(allTiles)
        //val chessBoardVsCPUViewModel = ChessBoardVsCPUViewModel(playerColor, allTiles)
        val settingViewModel = SettingViewModel(this@MainActivity)

        setContent {
            HexagonalChessTheme {
                navController = rememberNavController()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    App(
                        navController = navController as NavHostController,
                        settingViewModel = settingViewModel,
                        context = this@MainActivity
                    ) { finish() }
                }
            }
        }
    }
}
