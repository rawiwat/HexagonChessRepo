package com.example.hexagonalchess.presentation_layer.composeui

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
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

    val showPassword by signInViewModel.showPassword.collectAsState()

    val textBoxWidth = remember { (boxSize * 9 /10).dp }
    val textBoxHeight = remember { (boxSize / 10).dp }

    val sizeModifierBigButton = remember { Modifier.size(width = textBoxWidth / 4 * 3 , height = textBoxHeight * 3 / 2) }
    val sizeModifierSmallButton = remember { Modifier.size(width = textBoxWidth / 8 * 3 , height = textBoxHeight * 3 / 4) }
    val fontSize = remember { 20 }
    val fontSizeSmall = remember { 10 }

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
                            value = passwordInput,
                            onValueChange = { passwordInput = it },
                            modifier = Modifier
                                .size(
                                    width = textBoxWidth,
                                    height = textBoxHeight
                                ),
                            visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
                        )
                    }

                    Text(text = passwordMessage)

                    MenuButton(
                        text = "Show Password",
                        modifier = sizeModifierSmallButton,
                        fontSize = fontSizeSmall
                    ) {
                        signInViewModel.showPassword()
                    }

                    MenuButton(
                        text = "Sign In",
                        modifier = sizeModifierBigButton,
                        fontSize = fontSize
                    ) {
                        signInViewModel.onClickSignIn(
                            name = nameInput,
                            password = passwordInput,
                            context = context,
                            navController = navController
                        )
                    }

                    MenuButton(
                        text = "Sign Up",
                        modifier = sizeModifierBigButton,
                        fontSize = fontSize,
                        onClick = { navController.navigate(Route.signUp) }
                    )
                }
            }
        }
    }
}
