package com.example.hexagonalchess.presentation_layer.composeui

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.hexagonalchess.R
import com.example.hexagonalchess.domain_layer.GameMode
import com.example.hexagonalchess.domain_layer.Route
import com.example.hexagonalchess.presentation_layer.viewmodel.MainMenuViewModel
import kotlin.math.roundToInt

@Composable
fun MainMenu(
    navController: NavController,
    closeApp: () -> Unit
) {
    val titleSize by rememberSaveable { mutableDoubleStateOf(60.00) }

    val viewModel = remember { MainMenuViewModel() }
    val quitMenuOn by viewModel.quitMenuActive.collectAsState()

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.menu_background),
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
                onClick = {
                    navController.navigate("${Route.boardSelection}/${GameMode.ONLINE}")
                }
            )

            MenuButton(
                text = "Play\nLocal",
                onClick = {
                    navController.navigate("${Route.boardSelection}/${GameMode.LOCAL}")
                }
            )

            MenuButton(
                text = "Play Cpu",
                onClick = {
                    navController.navigate("${Route.boardSelection}/${GameMode.CPU}")
                }
            )

            MenuButton(
                text = "Setting",
                onClick = {
                    navController.navigate(Route.setting)
                }
            )

            MenuButton(
                text = "Quit",
                onClick = {
                    viewModel.turnOnQuitMenu()
                }
            )
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        AnimatedVisibility(visible = quitMenuOn) {
            QuitMenu(
                viewModel = viewModel,
                closeApp = closeApp,
                width = 200.dp,
                height = 150.dp
            )
        }
    }

    BackHandler(onBack = { })
}

@Composable
fun MenuButton(
    text: String,
    onClick: () -> Unit
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
                        onClick()
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

@Composable
fun QuitMenu(
    viewModel: MainMenuViewModel,
    closeApp: () -> Unit,
    width: Dp,
    height: Dp
) {
    val buttonWidth by remember { mutableStateOf(width / 2) }
    val buttonHeight by remember { mutableStateOf(height / 3) }
    val textBoxHeight by remember { mutableStateOf(height * 2 / 3) }

    Box(
        modifier = Modifier
            .size(
                width = width,
                height = height
            )
    ) {
        Image(
            painter = painterResource(id = R.drawable.menu_button),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(
                    width = width,
                    height = height
                )
        )
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(width, textBoxHeight),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Quit the game?"
                )
            }

            Row {
                Box(
                    modifier = Modifier
                        .size(
                            width = buttonWidth,
                            height = buttonHeight
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.menu_button),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(
                                width = buttonWidth,
                                height = buttonHeight
                            )
                            .clickable {
                                closeApp()
                            }
                    )

                    Text(
                        text = "Yes"
                    )
                }
                Canvas(
                    modifier = Modifier,
                    onDraw = {
                        drawLine(
                            color = Color.Black,
                            start = Offset(0f,0f),
                            end = Offset(0f, height.value),
                        )
                    }
                )
                Box(
                    modifier = Modifier
                        .size(
                            width = buttonWidth,
                            height = buttonHeight
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.menu_button),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(
                                width = buttonWidth,
                                height = buttonHeight
                            )
                            .clickable {
                                viewModel.turnOffQuitMenu()
                            }
                    )

                    Text(
                        text = "No"
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewMainMenu() {
    MainMenu(NavController(LocalContext.current), {})
}

@Preview
@Composable
fun PreviewQuit() {
    QuitMenu(
        viewModel = MainMenuViewModel(),
        closeApp = { /*TODO*/ },
        width = 200.dp, height = 150.dp)
}