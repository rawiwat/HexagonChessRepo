package com.example.hexagonalchess.presentation_layer.composeui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.hexagonalchess.R

@Composable
fun MainMenu(
    navController: NavController
) {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {

        Image(
            painter = painterResource(id = R.drawable.menu_background),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Hexagonal\nChess\nOnline",
                fontFamily = FontFamily(Font(R.font.game_title)),
                fontSize = 60.sp,
                textAlign = TextAlign.Center
            )

            MenuButton(
                text = "Play Online",
                route = "",
                navController = navController
            )

            MenuButton(
                text = "Play Local",
                route = "",
                navController = navController
            )

            MenuButton(
                text = "Quit",
                route = "",
                navController = navController
            )
        }
    }
}

@Composable
fun MenuButton(
    text: String,
    route: String,
    navController:NavController
) {
    Box(
        modifier = Modifier,
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.menu_button),
            contentDescription = null,
            modifier = Modifier.clickable {
                navController.navigate(route)
            }
        )

        Text(
            text = text,
            fontFamily = FontFamily(Font(R.font.menu_text))
        )
    }
}

@Preview
@Composable
fun PreviewMainMenu() {
    MainMenu(NavController(LocalContext.current))
}