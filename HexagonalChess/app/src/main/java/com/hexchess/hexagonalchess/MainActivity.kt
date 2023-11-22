package com.hexchess.hexagonalchess

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.hexchess.hexagonalchess.data_layer.database.FireBaseDatabasePlayer
import com.hexchess.hexagonalchess.domain_layer.encodeBitmapToString
import com.hexchess.hexagonalchess.domain_layer.getBitmapFromUri
import com.hexchess.hexagonalchess.domain_layer.player.manager.PlayerNameSharedPref
import com.hexchess.hexagonalchess.presentation_layer.composeui.App
import com.hexchess.hexagonalchess.ui.theme.HexagonalChessTheme

class MainActivity : ComponentActivity() {

    private lateinit var navController: NavController

    private lateinit var fetchImageResultLauncher: ActivityResultLauncher<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //val allTiles = ChessboardData().allTiles
        //val chessBoardViewModel = ChessBoardViewModel(allTiles)
        //val chessBoardVsCPUViewModel = ChessBoardVsCPUViewModel(playerColor, allTiles)
        val playerNameSharedPref = PlayerNameSharedPref(this@MainActivity)


        val playerName = playerNameSharedPref.getPlayerName()
        fetchImageResultLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            if (uri != null) {
                val imageBitmap = getBitmapFromUri(this@MainActivity, uri)
                imageBitmap?.let { bitmap ->
                    val encodedString = encodeBitmapToString(bitmap)
                    FireBaseDatabasePlayer(this@MainActivity)
                        .updatePlayerImage(
                            playerName.toString(),
                            encodedImage = encodedString
                        )
                }
            }
        }

        setContent {
            HexagonalChessTheme {
                navController = rememberNavController()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    App(
                        navController = navController as NavHostController,
                        playerNameSharedPref = playerNameSharedPref,
                        context = this@MainActivity,
                        closeAppFunction = { finish() },
                        playerName = playerName
                    )
                }
            }
        }
    }

    fun fetchImage() {
        fetchImageResultLauncher.launch("image/*")
    }
}
