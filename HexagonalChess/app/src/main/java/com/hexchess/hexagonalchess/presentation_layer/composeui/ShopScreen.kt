package com.hexchess.hexagonalchess.presentation_layer.composeui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.hexchess.hexagonalchess.R
import com.hexchess.hexagonalchess.data_layer.model.collection.Collectable
import com.hexchess.hexagonalchess.domain_layer.ChessPieceKeyWord
import com.hexchess.hexagonalchess.domain_layer.ChessSkin
import com.hexchess.hexagonalchess.domain_layer.Route
import com.hexchess.hexagonalchess.domain_layer.TileColor
import com.hexchess.hexagonalchess.domain_layer.TileTheme
import com.hexchess.hexagonalchess.domain_layer.getChessPieceImage
import com.hexchess.hexagonalchess.domain_layer.getColorFromTheme
import com.hexchess.hexagonalchess.domain_layer.getTileImage
import com.hexchess.hexagonalchess.presentation_layer.viewmodel.ShopViewModel

@Composable
fun ShopScreen(
    shopViewModel: ShopViewModel,
    navController: NavController
) {
    //val collection by shopViewModel.currentCollection.collectAsState()
    val price by shopViewModel.itemPrice.collectAsState()
    val backMenu by shopViewModel.backMenu.collectAsState()
    val playerCoin by shopViewModel.playerCoin.collectAsState()

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
        ) {
            Spacer(modifier = Modifier.height(12.dp))

            Box(
                contentAlignment = Alignment.CenterStart,
                modifier = Modifier
                    .height(36.dp)
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.coin_label),
                    contentDescription = null,
                    modifier = Modifier
                        .width(105.dp)
                )
                Text(
                    text = playerCoin.toString(),
                    color = Color.White,
                    modifier = Modifier
                        .size(67.dp)
                        .padding(start = 30.dp, top = 6.dp),
                    textAlign = TextAlign.Center,
                )
            }

            LazyColumn {
                items(shopViewModel.getPieceInShop()) {
                    ChessShop(
                        item = it,
                        price = price,
                        shopViewModel = shopViewModel
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                }
            }
            LazyColumn {
                items(shopViewModel.getTileInShop()) {
                    TileShop(
                        shopViewModel = shopViewModel,
                        collectableTile = it,
                        tileHeight = 47,
                        tileWidth = 55,
                        price = price
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                }
            }

            if (shopViewModel.getPieceInShop().isNullOrEmpty() && shopViewModel.getTileInShop().isNullOrEmpty()) {
                TextWithStroke(
                    text = "There's nothing left in the shop",
                    modifier = Modifier,
                    textColor = Color.Black,
                    strokeColor = Color.White,
                    fontSize = 80
                )
            }
        }

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomEnd
        ) {
            MenuButton(
                text = "Back",
                modifier = Modifier
                    .width(100.dp),
                fontSize = 20
            ) {
                shopViewModel.backMenu(true)
            }
        }
    }

    if (backMenu) {
        ShopBackMenu(
            navController = navController,
            shopViewModel = shopViewModel,
            width = 250.dp,
            height = 130.dp
        )
    }

    BackHandler {
        shopViewModel.backMenu(true)
    }
}

@Composable
fun TileShop(
    shopViewModel: ShopViewModel,
    collectableTile: Collectable,
    tileHeight:Int,
    tileWidth: Int,
    price: Int
) {
    val theme = try {
        TileTheme.valueOf(collectableTile.name)
    } catch (e: IllegalArgumentException) {
        TileTheme.DEFAULT
    }

    Box(
        modifier = Modifier
            .size(width = 272.dp, height = 120.dp)
            .background(color = Color.DarkGray)
    ) {
        Image(
            painter = painterResource(id = R.drawable.menu_template_dark),
            contentDescription = null,
            modifier = Modifier.size(width = 272.dp, height = 120.dp),
            contentScale = ContentScale.FillBounds
        )

        Column(
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Spacer(modifier = Modifier.height(15.dp))

            Text(
                text = collectableTile.name,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = TextStyle(
                    color = getColorFromTheme(theme)
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

            Row (
                modifier = Modifier
                    .width(272.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.height(24.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.coin_label),
                        contentDescription = null,
                        modifier = Modifier
                            .width(70.dp)
                    )
                    Text(
                        text = price.toString(),
                        color = Color.White,
                        modifier = Modifier
                            .size(45.dp)
                            .padding(start = 8.dp, top = 2.dp),
                        textAlign = TextAlign.Center,
                    )
                }
                BuyButton(
                    shopViewModel = shopViewModel,
                    item = collectableTile
                )
            }
        }
    }
}

@Composable
fun ChessShop(
    item: Collectable,
    price: Int,
    shopViewModel: ShopViewModel
) {
    val listOfAllPiece = listOf(
        ChessPieceKeyWord.WHITE_KING, ChessPieceKeyWord.BLACK_KING, ChessPieceKeyWord.WHITE_QUEEN,
        ChessPieceKeyWord.BLACK_QUEEN, ChessPieceKeyWord.WHITE_BISHOP, ChessPieceKeyWord.BLACK_BISHOP,
        ChessPieceKeyWord.WHITE_KNIGHT, ChessPieceKeyWord.BLACK_KNIGHT, ChessPieceKeyWord.WHITE_ROOK,
        ChessPieceKeyWord.BLACK_ROOK,ChessPieceKeyWord.WHITE_PAWN, ChessPieceKeyWord.BLACK_PAWN
    )

    val chessSkin = try {
        ChessSkin.valueOf(item.name)
    } catch (e: IllegalArgumentException) {
        ChessSkin.DEFAULT
    }
    val height = remember { 120.dp }
    val width = remember { 272.dp }

    Box(
        modifier = Modifier.size(width = width, height = height)
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

            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .height(24.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Box(
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.coin_label),
                        contentDescription = null,
                        modifier = Modifier
                            .width(70.dp)
                    )
                    Text(
                        text = price.toString(),
                        color = Color.White,
                        modifier = Modifier
                            .size(45.dp)
                            .padding(start = 8.dp, top = 2.dp),
                        textAlign = TextAlign.Center,
                    )
                }
                BuyButton(
                    shopViewModel = shopViewModel,
                    item = item
                )
            }

        }
    }
}

@Composable
fun BuyButton(
    shopViewModel: ShopViewModel,
    item:Collectable)
{
    MenuButton(
        text = "Buy",
        modifier = Modifier
            .size(width = 70.dp, height = 28.dp),
            //.padding(top = 10.dp, bottom = 10.dp),
        fontSize = 15
    ) {
        shopViewModel.buy(item)
    }
}

@Composable
fun ShopBackMenu(
    navController: NavController,
    shopViewModel: ShopViewModel,
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
                    text ="Go back to the Main Menu?"
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
                                navController.navigate(Route.main)
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
                                shopViewModel.backMenu(false)
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
