package com.example.hexagonalchess.presentation_layer.composeui

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.hexagonalchess.R
import com.example.hexagonalchess.domain_layer.Route
import com.example.hexagonalchess.presentation_layer.viewmodel.SignInViewModel

@Composable
fun SignInScreen(
    navController: NavController,
    signInViewModel: SignInViewModel,
    context: Context
) {
    var nameInput by rememberSaveable { mutableStateOf("") }

    var passwordInput by rememberSaveable { mutableStateOf("") }

    val screenWidth = LocalConfiguration.current.screenWidthDp

    val boxSize = remember { screenWidth / 4 * 3 }

    val nameMessage by signInViewModel.nameMessage.collectAsState()

    val passwordMessage by signInViewModel.passwordMessage.collectAsState()

    val textBoxWidth = remember { (boxSize - 10).dp }
    val textBoxHeight = 25.dp

    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.menu_background),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            GameTitle(titleSize = 60.00)
            Box(
                modifier = Modifier
                    .size(boxSize.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.menu_template),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .size(boxSize.dp)
                )
                Column(
                    modifier = Modifier
                        .size(boxSize.dp),
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.menu_template_dark),
                            contentDescription = null,
                            modifier = Modifier
                                .size(
                                    width = textBoxWidth,
                                    height = textBoxHeight
                                ),
                            contentScale = ContentScale.FillBounds
                        )
                        BasicTextField(
                            nameInput ,
                            onValueChange = { nameInput = it },
                            modifier = Modifier
                                .size(
                                    width = textBoxWidth,
                                    height = textBoxHeight
                                )
                        )

                    }

                    Text(text = nameMessage)

                    Box(
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.menu_template_dark),
                            contentDescription = null,
                            modifier = Modifier
                                .size(
                                    width = textBoxWidth,
                                    height = textBoxHeight
                                ),
                            contentScale = ContentScale.FillBounds
                        )
                        BasicTextField(
                            passwordInput ,
                            onValueChange = { passwordInput = it },
                            modifier = Modifier
                                .size(
                                    width = textBoxWidth,
                                    height = textBoxHeight
                                )
                        )

                    }

                    Text(text = passwordMessage)

                    MenuButton(text = "Sign In") {
                        signInViewModel.checkIfPlayerExists(nameInput) { playerExisted ->
                            if (playerExisted) {
                                signInViewModel.nameIsReady()
                            } else {
                                signInViewModel.nameNotFind()
                            }


                        }

                        signInViewModel.checkPassword()

                        signInViewModel.loginAndGotoMain(
                                nameInput,
                        navController,
                        context
                        )
                    }

                    MenuButton(
                        text = "Sign Up",
                        onClick = { navController.navigate(Route.signUp) }
                    )
                }
            }
        }
    }
}