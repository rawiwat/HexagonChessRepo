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
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
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
import com.example.hexagonalchess.data_layer.database.FireBaseDatabasePlayer
import com.example.hexagonalchess.domain_layer.ChessPieceKeyWord
import com.example.hexagonalchess.domain_layer.ChessSkin
import com.example.hexagonalchess.domain_layer.Route
import com.example.hexagonalchess.domain_layer.SettingState
import com.example.hexagonalchess.domain_layer.TileColor
import com.example.hexagonalchess.domain_layer.TileTheme
import com.example.hexagonalchess.domain_layer.getChessPieceImage
import com.example.hexagonalchess.domain_layer.getColorFromTheme
import com.example.hexagonalchess.domain_layer.getTileImage
import com.example.hexagonalchess.domain_layer.player.manager.PlayerNameSharedPref
import com.example.hexagonalchess.presentation_layer.viewmodel.SettingViewModel

@Composable
fun SettingScreen(
    navController: NavController,
    settingViewModel: SettingViewModel,
    closeAppFunction:()-> Unit,
    context: Context
) {
    val settingState by settingViewModel.settingState.collectAsState()

    val themeSettingTurnOn by rememberUpdatedState(newValue = settingState == SettingState.THEME)

    val logOutSettingTurnOn by rememberUpdatedState(newValue = settingState == SettingState.LOG_OUT)

    val skinSettingTurnOn by rememberUpdatedState(newValue = settingState == SettingState.SKIN)

    val currentSkin by settingViewModel.currentSkin.collectAsState()

    val playerTileCollection = remember { settingViewModel.playersTiles }

    val playerSkinCollection = remember { settingViewModel.playerChessSkin }

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
                }
            )

            SettingButton(text = "Chess Skin") {
                settingViewModel.turnOnSkin()
            }
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
                ThemeSetting(
                    settingViewModel,
                    tilesCollection = playerTileCollection
                )
            }

            AnimatedVisibility(visible = logOutSettingTurnOn) {
                LogOutMenu(
                    settingViewModel = settingViewModel,
                    width = 250.dp,
                    height = 130.dp,
                    closeAppFunction = closeAppFunction
                )
            }
            AnimatedVisibility(visible = skinSettingTurnOn) {
                ChessSkinSetting(
                    settingViewModel = settingViewModel,
                    skinCollection = playerSkinCollection,
                    listOfAllPiece = listOf(
                        ChessPieceKeyWord.WHITE_KING, ChessPieceKeyWord.BLACK_KING, ChessPieceKeyWord.WHITE_QUEEN,
                        ChessPieceKeyWord.BLACK_QUEEN, ChessPieceKeyWord.WHITE_BISHOP, ChessPieceKeyWord.BLACK_BISHOP,
                        ChessPieceKeyWord.WHITE_KNIGHT, ChessPieceKeyWord.BLACK_KNIGHT, ChessPieceKeyWord.WHITE_ROOK,
                        ChessPieceKeyWord.BLACK_ROOK,ChessPieceKeyWord.WHITE_PAWN, ChessPieceKeyWord.BLACK_PAWN
                    ),
                    currentSkin = currentSkin
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
    settingViewModel: SettingViewModel,
    tilesCollection: List<TileTheme>
) {
    Column(
        modifier = Modifier
    ) {
        for (tileTheme in tilesCollection) {
            ChangeTheme(
                theme = tileTheme,
                viewModel = settingViewModel,
                themeName = tileTheme.name,
                themeColor = getColorFromTheme(tileTheme)
            )
        }

        SettingButton(text = "Done") {
            settingViewModel.turnOffSettingMenu()
        }
    }
}

@Composable
fun ChessSkinSetting(
    settingViewModel: SettingViewModel,
    skinCollection: List<ChessSkin>,
    listOfAllPiece: List<ChessPieceKeyWord>,
    currentSkin: ChessSkin
) {
    Column {
        for (chessSkin in skinCollection) {
            ChangeChessSkin(
                chessSkin = chessSkin,
                settingViewModel = settingViewModel,
                listOfAllPiece = listOfAllPiece,
                currentSkin = currentSkin
            )
        }
        SettingButton(text = "Done") {
            settingViewModel.turnOffSettingMenu()
        }
    }
}

@Composable
fun ChangeChessSkin(
    chessSkin: ChessSkin,
    settingViewModel: SettingViewModel,
    listOfAllPiece: List<ChessPieceKeyWord>,
    currentSkin: ChessSkin
) {
    val height = remember { 96.dp }
    val width = remember { 272.dp }

    Box(
        modifier = Modifier
            .size(width = width, height = height)
            .border(width = if (chessSkin == currentSkin) 5.dp else 0.dp, color = Color.White)
            .clickable {
                settingViewModel.changeSkin(chessSkin)
            }
    ) {
        Image(
            painter = painterResource(id = R.drawable.menu_template_dark),
            contentDescription = null,
            modifier = Modifier.size(width = width, height = height),
            contentScale = ContentScale.FillBounds
        )
        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = chessSkin.name,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = TextStyle(
                    color = Color.White
                ),
                fontFamily = FontFamily(Font(R.font.menu_text)),
                fontSize = 25.sp
            )

            LazyHorizontalGrid(
                rows = GridCells.Fixed(2),
                modifier = Modifier
                    .height(70.dp)
            ) {
                items(listOfAllPiece) {
                    Image(
                        painter = painterResource(id = getChessPieceImage(it,chessSkin)),
                        contentDescription = null,
                        modifier = Modifier
                            .size(35.dp)
                    )
                }
            }
        }
    }
}
@Composable
fun LogOutMenu(
    settingViewModel: SettingViewModel,
    width: Dp,
    height: Dp,
    closeAppFunction: () -> Unit
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
                    text = "are you sure you want to log out and Quit the Game?"
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
                                closeAppFunction()
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

/*@Preview
@Composable
fun ChangeThemePreview() {
    val context = LocalContext.current
    ChangeTheme(
        theme = TileTheme.PURPLE,
        viewModel = SettingViewModel(
            context,
            NavController(context),
            PlayerNameSharedPref(context),
            databasePlayer = FireBaseDatabasePlayer(context)
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
            PlayerNameSharedPref(context),
            databasePlayer = FireBaseDatabasePlayer(context)
        ),{},
        context
    )
}

@Preview
@Composable
fun ThemeSettingPreview() {
    val context = LocalContext.current
    ThemeSetting(
        SettingViewModel(
            context,
            NavController(context),
            PlayerNameSharedPref(context),
            databasePlayer = FireBaseDatabasePlayer(context)
        ),
        listOf()
    )
}*/