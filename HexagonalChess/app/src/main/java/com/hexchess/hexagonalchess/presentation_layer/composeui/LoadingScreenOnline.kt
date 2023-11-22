package com.hexchess.hexagonalchess.presentation_layer.composeui

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.hexchess.hexagonalchess.R
import com.hexchess.hexagonalchess.data_layer.database.DatabaseGame
import com.hexchess.hexagonalchess.data_layer.model.tile.Tile
import com.hexchess.hexagonalchess.domain_layer.BoardType
import com.hexchess.hexagonalchess.domain_layer.Route

@Composable
fun LoadingScreenOnline(
    playerName: String,
    databaseGame: DatabaseGame,
    boardType: BoardType,
    board: List<Tile>,
    navController: NavController,
    context: Context
) {
    var opponentFound by rememberSaveable {
        mutableStateOf(false)
    }

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
            databaseGame.sendPlayerToOnlineWaitingRoom(playerName, board, boardType)
        }

        DisposableEffect(opponentFound) {
            val gameStartReceiver = object : BroadcastReceiver() {
                override fun onReceive(p0: Context?, p1: Intent?) {
                    opponentFound = true
                    navController.navigate("${Route.online}/$boardType")
                }
            }
            val intentFilter = IntentFilter("GAME_START")
            ContextCompat.registerReceiver(context,gameStartReceiver, intentFilter, ContextCompat.RECEIVER_NOT_EXPORTED)
            onDispose {
                context.unregisterReceiver(gameStartReceiver)
            }
        }
    }
}