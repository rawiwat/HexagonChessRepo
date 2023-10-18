package com.example.hexagonalchess

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.hexagonalchess.data_layer.model.database.FirebaseRealtimeDatabase
import com.example.hexagonalchess.data_layer.chess_board_data.ChessboardData
import com.example.hexagonalchess.presentation_layer.GameScreen
import com.example.hexagonalchess.presentation_layer.viewmodel.ChessBoardViewModel
import com.example.hexagonalchess.ui.theme.HexagonalChessTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val allTiles = ChessboardData().allTiles
        val database = FirebaseRealtimeDatabase()
        val chessBoardViewModel = ChessBoardViewModel(
            allTiles,
            //database
        )
        setContent {
            HexagonalChessTheme {
                // ColumnA surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GameScreen(chessBoardViewModel)
                }
            }
        }
    }
}
