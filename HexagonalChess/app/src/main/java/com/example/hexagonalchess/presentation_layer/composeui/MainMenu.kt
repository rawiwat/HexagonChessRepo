package com.example.hexagonalchess.presentation_layer.composeui

import android.content.Context
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
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
import coil.compose.rememberImagePainter
import com.example.hexagonalchess.R
import com.example.hexagonalchess.data_layer.database.DatabasePlayer
import com.example.hexagonalchess.data_layer.model.player.Player
import com.example.hexagonalchess.domain_layer.GameMode
import com.example.hexagonalchess.domain_layer.Route
import com.example.hexagonalchess.domain_layer.getPlayerImageBitmap
import com.example.hexagonalchess.presentation_layer.viewmodel.MainMenuViewModel
import kotlin.math.roundToInt

@Composable
fun MainMenu(
    navController: NavController,
    playerName: String,
    databasePlayer: DatabasePlayer,
    context: Context,
    closeApp: () -> Unit
) {
    val titleSize by rememberSaveable { mutableDoubleStateOf(60.00) }
    val viewModel = remember { MainMenuViewModel(databasePlayer, playerName) }
    val quitMenuOn by viewModel.quitMenuActive.collectAsState()
    val screenWidth = LocalConfiguration.current.screenWidthDp
    val sizeModifier = remember {
        Modifier.size(
            width = (screenWidth / 2).dp,
            height = (screenWidth / 6).dp
        )
    }
    val fontSize = remember { screenWidth / 16 }
    val playerModel by viewModel.player.collectAsState()

    println(playerName)
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
            PlayerOnMain(
                player = playerModel,
                screenWidth = screenWidth.dp,
                context = context
            )
            GameTitle(titleSize = titleSize)

            MenuButton(
                text = "Coming\nSoon",
                onClick = {
                    /*navController.navigate("${Route.boardSelection}/${GameMode.ONLINE}")*/
                },
                modifier = sizeModifier,
                fontSize = fontSize
            )

            MenuButton(
                text = "Play\nLocal",
                onClick = {
                    navController.navigate("${Route.boardSelection}/${GameMode.LOCAL}")
                },
                modifier = sizeModifier,
                fontSize = fontSize
            )

            MenuButton(
                text = "Play Cpu",
                onClick = {
                    navController.navigate("${Route.boardSelection}/${GameMode.CPU}")
                },
                modifier = sizeModifier,
                fontSize = fontSize
            )

            MenuButton(
                text = "Shop",
                onClick = {
                    navController.navigate(Route.shop)
                },
                modifier = sizeModifier,
                fontSize = fontSize
            )

            MenuButton(
                text = "Setting",
                onClick = {
                    navController.navigate(Route.setting)
                },
                modifier = sizeModifier,
                fontSize = fontSize
            )

            MenuButton(
                text = "Quit",
                onClick = {
                    viewModel.turnOnQuitMenu()
                },
                modifier = sizeModifier,
                fontSize = fontSize
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

    BackHandler(onBack = { viewModel.turnOnQuitMenu() })
}

@Composable
fun MenuButton(
    text: String,
    modifier: Modifier,
    fontSize: Int,
    onClick: () -> Unit,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {

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

@Composable
fun GameTitle(titleSize:Double) {
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
}
/*
@Preview
@Composable
fun PreviewMainMenu() {
    CompositionLocalProvider(LocalTitleSize provides 60.0) {
        MainMenu(NavController(LocalContext.current),"pete" , databasePlayer = FireBaseDatabasePlayer(
            LocalContext.current), {})
    }
}

@Preview
@Composable
fun PreviewQuit() {
    QuitMenu(
        viewModel = MainMenuViewModel(
            databasePlayer = FireBaseDatabasePlayer(
                LocalContext.current
            )
        ),
        closeApp = {  },
        width = 200.dp, height = 150.dp)
}*/

@Composable
fun PlayerOnMain(
    player:Player,
    screenWidth: Dp,
    context: Context
) {
    val playerImageBitmap = getPlayerImageBitmap(
        encodedString = player.encodedImageBitmap,
        context = context
    )
    val imageSize = remember { screenWidth / 4 }
    val frameHeight = remember { screenWidth / 3 }
    val frameWidth = remember { frameHeight * 86 / 100 }

    val idGap = remember { screenWidth / 3 }

    val painter = rememberImagePainter(data = playerImageBitmap)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(frameHeight)
        ,
        contentAlignment = Alignment.CenterStart
    ) {
        Row {
            TextWithStroke(
                text = "id : ${player.playerId}",
                modifier = Modifier.width(idGap),
                textColor = Color.Black,
                strokeColor = Color.White,
                10
            )

            Box(
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painter,
                    contentDescription = null,
                    modifier = Modifier
                        .size(imageSize)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )

                Image(
                    painter = painterResource(id = R.drawable.player_image_frame),
                    contentDescription = null,
                    modifier = Modifier
                        .size(
                            height = frameHeight,
                            width = frameWidth
                        )
                )
            }

            Box(
                modifier = Modifier
                    .height(frameHeight)
            ) {
                Box(
                    modifier = Modifier
                        .height(frameHeight),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.player_name_and_rating_display),
                        contentDescription = null,
                        modifier = Modifier
                            .height(frameHeight / 2 * 3)
                    )

                    Column {
                        Text(
                            text = player.name,
                            style = TextStyle(
                                color = Color.White
                            )
                        )
                        Text(
                            text = "Rank Rating : ${player.rating}",
                            style = TextStyle(
                                color = Color.White
                            )
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .height(frameHeight),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.coin_label),
                        contentDescription = null,
                        modifier = Modifier
                            .height(frameHeight / 7 * 2)
                    )
                    Text(
                        text = player.coin.toString(),
                        fontSize = (frameHeight.value / 4).sp,
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Composable
fun TextWithStroke(
    text: String,
    modifier: Modifier,
    textColor: Color,
    strokeColor: Color,
    fontSize: Int
) {
    Box {
        Text(
            text = text,
            textAlign = TextAlign.Start,
            modifier = modifier,
            style = TextStyle(
                color = textColor
            ),
            fontSize = fontSize.sp
        )

        Text(
            text = text,
            textAlign = TextAlign.Start,
            modifier = modifier,
            style = TextStyle(
                color = strokeColor,
                drawStyle = Stroke(
                    width = 1f
                )
            ),
            fontSize = fontSize.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PlayerPreview() {
    PlayerOnMain(
        player = Player(
            name = "Jeb zebos",
            encodedImageBitmap = ""
        ),
        context = LocalContext.current,
        screenWidth = LocalConfiguration.current.screenWidthDp.dp
    )
}
