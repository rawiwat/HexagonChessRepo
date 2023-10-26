package com.example.hexagonalchess.presentation_layer.composeui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.hexagonalchess.R
import com.example.hexagonalchess.domain_layer.Route
import kotlin.math.roundToInt

@Composable
fun MainMenu(
    navController: NavController
) {
    val titleSize by rememberSaveable { mutableDoubleStateOf(60.00) }

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.menu_background),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )

        Image(
            painter = painterResource(id = R.drawable.menu_background_2),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Box {
                Text(
                    text = "Hexagonal Chess Online",
                    fontFamily = FontFamily(Font(R.font.game_title)),
                    fontSize = titleSize.roundToInt().sp,
                    textAlign = TextAlign.Center,
                    style = TextStyle( color = Color.Black ),
                    fontWeight = FontWeight.ExtraBold
                )
                Text(
                    text = "Hexagonal Chess Online",
                    fontFamily = FontFamily(Font(R.font.game_title)),
                    fontSize = titleSize.roundToInt().sp,
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        color = Color.White,
                        drawStyle = Stroke(
                            width = 1.5f,
                        )
                    ),
                    fontWeight = FontWeight.ExtraBold
                )
            }


            MenuButton(
                text = "Play\nOnline",
                route = "",
                navController = navController
            )

            MenuButton(
                text = "Play\nLocal",
                route = Route.local,
                navController = navController
            )

            MenuButton(
                text = "Play Cpu",
                route = Route.vsCpu,
                navController = navController
            )

            MenuButton(
                text = "Setting",
                route = Route.setting,
                navController = navController
            )

            MenuButton(
                text = "Quit",
                route = Route.setting,
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
        modifier = Modifier
            .size(width = 215.dp, height = 63.dp),
        contentAlignment = Alignment.Center
    ) {
        val fontSize by remember { mutableIntStateOf(24) }
        Image(
            painter = painterResource(id = R.drawable.menu_button),
            contentDescription = null,
            modifier = Modifier
                .clickable {
                    try {
                        navController.navigate(route)
                    } catch (e: Exception) {
                        println(e)
                    }
                }
                .fillMaxSize()
        )

        Text(
            text = text,
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

@Preview
@Composable
fun PreviewMainMenu() {
    MainMenu(NavController(LocalContext.current))
}