package com.example.hexagonalchess.presentation_layer.composeui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.hexagonalchess.R
import com.example.hexagonalchess.domain_layer.BoardType
import com.example.hexagonalchess.domain_layer.GameMode
import com.example.hexagonalchess.domain_layer.Route
import com.example.hexagonalchess.domain_layer.getImageIdFromBoardType
import com.example.hexagonalchess.presentation_layer.viewmodel.BoardSelectionViewModel
import java.net.URLEncoder

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BoardSelectionScreen(
    boardSelectionViewModel: BoardSelectionViewModel,
    navController: NavController,
    gameMode: GameMode
) {
    val currentBoard by boardSelectionViewModel.currentBoard.collectAsState()
    /*val gameMode = when(gameMode) {
        GameMode.LOCAL -> Route.game
        GameMode.ONLINE -> Route.online
        GameMode.CPU -> Route.vsCpu
    }*/

    val listOfBoardType = BoardType.values().toList()
    val screenWidth = LocalConfiguration.current.screenWidthDp
    val previewHeight = screenWidth * 4 / 3
    val encodedRoute = URLEncoder.encode("${Route.game}/$gameMode/$currentBoard","UTF-8")

    Scaffold(
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp),
                contentAlignment = Alignment.Center
            ) {
                val fontSize by remember { mutableIntStateOf(24) }
                Image(
                    painter = painterResource(id = R.drawable.menu_button),
                    contentDescription = null,
                    modifier = Modifier
                        .clickable {
                            try {
                                if (gameMode == GameMode.LOCAL || gameMode == GameMode.CPU) {
                                    navController.navigate("${Route.loading}/$encodedRoute")
                                } else {
                                    navController.navigate("")
                                }
                            } catch (e: Exception) {
                                println(e)
                            }
                        }
                        .size(width = 237.dp, height = 69.dp)
                )

                Text(
                    text = "Confirm",
                    fontFamily = FontFamily(Font(R.font.menu_text)),
                    fontSize = fontSize.sp,
                    modifier = Modifier
                        .padding(start = 5.dp, end = 5.dp),
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        color = Color.Black
                    )
                )
            }
        }
    ) {
        Box(modifier = Modifier
            .padding(top = it.calculateTopPadding())
        ) {
            Image(
                painter = painterResource(id = R.drawable.menu_background),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = getImageIdFromBoardType(currentBoard)),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(previewHeight.dp)
                )
                LazyVerticalGrid(columns = GridCells.Fixed(2)) {
                    items(listOfBoardType) { boardType ->
                        BoardInSelection(
                            boardType = boardType,
                            boardName = boardType.nameInSelection,
                            boardSelectionViewModel = boardSelectionViewModel,
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun BoardInSelection(
    boardType: BoardType,
    boardName: String,
    boardSelectionViewModel: BoardSelectionViewModel
) {
    Box(
        modifier = Modifier
            .size(width = 215.dp, height = 63.dp)
            .clickable {
                boardSelectionViewModel.changeBoard(boardType)
            },
        contentAlignment = Alignment.Center
    ) {
        var fontSize by rememberSaveable { mutableDoubleStateOf(22.0) }
        Image(
            painter = painterResource(id = R.drawable.menu_button),
            contentDescription = null,
            modifier = Modifier
                .size(width = 205.dp, height = 55.dp)
        )

        Box(
            modifier = Modifier
                .size(width = 160.dp, height = 50.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = boardName,
                fontFamily = FontFamily(Font(R.font.menu_text)),
                fontSize = fontSize.sp,
                textAlign = TextAlign.Center,
                style = TextStyle(
                    color = Color.Black
                ),
                onTextLayout = { result ->
                    if(result.didOverflowWidth) {
                        fontSize *= 0.9
                    }
                },
                maxLines = 1
            )
        }
    }
}

@Preview
@Composable
fun BoardSelectionPreview() {
    BoardSelectionScreen(
        boardSelectionViewModel = BoardSelectionViewModel(),
        navController = NavController(LocalContext.current),
        gameMode = GameMode.ONLINE
    )
}