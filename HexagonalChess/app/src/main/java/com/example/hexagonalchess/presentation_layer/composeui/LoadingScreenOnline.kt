package com.example.hexagonalchess.presentation_layer.composeui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.hexagonalchess.R
import com.example.hexagonalchess.data_layer.database.DatabaseGame
import com.example.hexagonalchess.data_layer.model.tile.Tile
import kotlinx.coroutines.delay
import java.net.URLDecoder

@Composable
fun LoadingScreenOnline(
    playerName: String,
    databaseGame: DatabaseGame,
    board: List<Tile>,
    navController: NavController
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Now Loading...",
            fontFamily = FontFamily(Font(R.font.menu_text)),
            fontSize = (LocalConfiguration.current.screenWidthDp / 10).sp,
            textAlign = TextAlign.Center,
            style = TextStyle(
                color = Color.White
            )
        )

        LaunchedEffect(Unit) {
            databaseGame.sendPlayerToOnlineWaitingRoom(playerName, board)
        }
    }
}