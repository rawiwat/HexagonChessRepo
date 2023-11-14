package com.example.hexagonalchess.presentation_layer.composeui

import android.content.Context
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.hexagonalchess.MainActivity
import com.example.hexagonalchess.R
import com.example.hexagonalchess.domain_layer.Route
import com.example.hexagonalchess.domain_layer.SettingState
import com.example.hexagonalchess.domain_layer.TileColor
import com.example.hexagonalchess.domain_layer.TileTheme
import com.example.hexagonalchess.domain_layer.getTileImage
import com.example.hexagonalchess.domain_layer.player.manager.PlayerNameSharedPref
import com.example.hexagonalchess.presentation_layer.viewmodel.SettingViewModel

@Composable
fun SettingScreen(
    navController: NavController,
    settingViewModel: SettingViewModel,
    context: Context
) {
    val settingState by settingViewModel.settingState.collectAsState()

    val themeSettingTurnOn by rememberUpdatedState(newValue = settingState == SettingState.THEME)

    val logOutSettingTurnOn by rememberUpdatedState(newValue = settingState == SettingState.LOG_OUT)

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
            SettingButton(
                text = "Theme",
                onClick = {
                    settingViewModel.turnOnTheme()
                    println(themeSettingTurnOn)
                    println(settingState)
                },
            )

            SettingButton(
                text = "Change Image",
                onClick = {
                    (context as? MainActivity)?.fetchImage()
                },
            )

            SettingButton(
                text = "Log Out",
                onClick = {
                    settingViewModel.turnOnLogOutMenu()
                },
            )

            SettingButton(
                text = "go back",
                onClick = {
                    settingViewModel.backToMain()
                },
            )
        }

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            AnimatedVisibility(visible = themeSettingTurnOn) {
                ThemeSetting(settingViewModel)
            }

            AnimatedVisibility(visible = logOutSettingTurnOn) {
                LogOutMenu(
                    settingViewModel = settingViewModel,
                    width = 250.dp,
                    height = 130.dp
                )
            }
        }

    }
    BackHandler(
        onBack = {
            settingViewModel.turnOffSettingMenu()
            navController.navigate(route = Route.main)
        }
    )
}

@Composable
fun SettingButton(
    text: String,
    onClick:()->Unit
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
fun ChangeTheme(
    theme: TileTheme,
    viewModel: SettingViewModel,
    themeName: String,
    themeColor: Color
) {
    val currentTheme by viewModel.currentTheme.collectAsState()
    val thisTheme by rememberSaveable { mutableStateOf(theme) }
    val tileWidth by remember { mutableIntStateOf(55) }
    val tileHeight by remember { mutableIntStateOf(47) }
    val scrollState = rememberScrollState()
    Box(
        modifier = Modifier
            .size(width = 272.dp, height = 96.dp)
            .clickable { viewModel.changeTheme(thisTheme) }
            .border(width = if (thisTheme == currentTheme) 5.dp else 0.dp, color = themeColor)
            .background(color = Color.DarkGray)
    ) {
        Image(
            painter = painterResource(id = R.drawable.menu_template_dark),
            contentDescription = null,
            modifier = Modifier.size(width = 272.dp, height = 96.dp),
            contentScale = ContentScale.FillBounds
        )

        Column(
            Modifier.verticalScroll(scrollState),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Spacer(modifier = Modifier.height(15.dp))

            Text(
                text = themeName,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = TextStyle(
                    color = themeColor
                ),
                fontFamily = FontFamily(Font(R.font.menu_text)),
                fontSize = 25.sp
            )
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.Transparent)
            ) {
                Image(
                    painter = painterResource(id = getTileImage(TileColor.DARK, theme)),
                    contentDescription = null,
                    modifier = Modifier
                        .size(height = tileHeight.dp, width = tileWidth.dp)
                )
                Image(
                    painter = painterResource(id = getTileImage(TileColor.MID, theme)),
                    contentDescription = null,
                    modifier = Modifier
                        .size(height = tileHeight.dp, width = tileWidth.dp)
                )
                Image(
                    painter = painterResource(id = getTileImage(TileColor.LIGHT, theme)),
                    contentDescription = null,
                    modifier = Modifier
                        .size(height = tileHeight.dp, width = tileWidth.dp)
                )
            }
        }

    }
}

@Composable
fun ThemeSetting(
    settingViewModel: SettingViewModel
) {

    Column(
        modifier = Modifier
    ) {
        ChangeTheme(
            theme = TileTheme.DEFAULT,
            viewModel = settingViewModel,
            themeName = "Default Theme",
            themeColor = Color.White
        )

        ChangeTheme(
            theme = TileTheme.RED,
            viewModel = settingViewModel,
            themeName = "Red Theme",
            themeColor = Color.Red
        )

        ChangeTheme(
            theme = TileTheme.YELLOW,
            viewModel = settingViewModel,
            themeName = "Yellow Theme",
            themeColor = Color.Yellow
        )

        ChangeTheme(
            theme = TileTheme.BLUE,
            viewModel = settingViewModel,
            themeName = "Blue Theme",
            themeColor = Color.Blue
        )

        ChangeTheme(
            theme = TileTheme.PURPLE,
            viewModel = settingViewModel,
            themeName = "Purple Theme",
            themeColor = Color.Magenta
        )

        ChangeTheme(
            theme = TileTheme.GREEN,
            viewModel = settingViewModel,
            themeName = "Green Theme",
            themeColor = Color.Green
        )

        ChangeTheme(
            theme = TileTheme.ORANGE,
            viewModel = settingViewModel,
            themeName = "Orange Theme",
            themeColor = Color(0xFFFFA500)
        )

        SettingButton(text = "Done") {
            settingViewModel.turnOffSettingMenu()
        }
    }
}


@Composable
fun LogOutMenu(
    settingViewModel: SettingViewModel,
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
        Column {
            Box(
                modifier = Modifier
                    .size(width, textBoxHeight),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text ="are you sure you want to log out?"
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
                                settingViewModel.logOut()
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
                                settingViewModel.turnOffSettingMenu()
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
fun ChangeThemePreview() {
    val context = LocalContext.current
    ChangeTheme(
        theme = TileTheme.PURPLE,
        viewModel = SettingViewModel(
            context,
            NavController(context),
            PlayerNameSharedPref(context)
        ),
        themeName = "Purple Theme",
        themeColor = Color.Magenta
    )
}

@Preview
@Composable
fun SettingScreenPreview() {
    val context = LocalContext.current
    SettingScreen(
        navController = NavController(context),
        settingViewModel = SettingViewModel(
            context,
            NavController(context),
            PlayerNameSharedPref(context)
        ),
        context
    )
}

@Preview
@Composable
fun ThemeSettingPreview() {
    val context = LocalContext.current
    ThemeSetting(SettingViewModel(
        context,
        NavController(context),
        PlayerNameSharedPref(context)
    ))
}