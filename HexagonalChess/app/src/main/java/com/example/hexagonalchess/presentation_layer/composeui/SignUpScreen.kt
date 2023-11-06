package com.example.hexagonalchess.presentation_layer.composeui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.hexagonalchess.R
import com.example.hexagonalchess.domain_layer.Route
import com.example.hexagonalchess.presentation_layer.viewmodel.SignUpViewModel

@Composable
fun SignUpScreen(
    navController: NavController,
    signUpViewModel: SignUpViewModel
) {
    var nameInput by rememberSaveable { mutableStateOf("") }

    var passwordInput by rememberSaveable { mutableStateOf("") }

    val screenWidth = LocalConfiguration.current.screenWidthDp

    val boxSize = remember { screenWidth / 2 }

    val nameMessage by signUpViewModel.nameMessage.collectAsState()

    val passwordMessage by signUpViewModel.passwordMessage.collectAsState()

    Surface {
        Column {
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
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    BasicTextField(
                        nameInput ,
                        onValueChange = { nameInput = it }
                    )

                    Text(text = nameMessage)

                    BasicTextField(
                        passwordInput ,
                        onValueChange = { passwordInput = it }
                    )

                    Text(text = passwordMessage)

                    MenuButton(text = "Sign Up") {
                        signUpViewModel.checkIfPlayerExists(nameInput) { playerExisted ->
                            if (playerExisted) {
                                signUpViewModel.nameAlreadyExisted(nameInput)
                            } else {
                                signUpViewModel.nameIsReady()
                            }
                            if (passwordInput.length >= 8) {
                                signUpViewModel.passwordIsReady()
                            } else {
                                signUpViewModel.passwordTooShort()
                            }
                            signUpViewModel.addNewPlayer(nameInput, passwordInput)
                        }
                    }

                    MenuButton(
                        text = "Sign In",
                        onClick = { navController.navigate(Route.signIn) }
                    )
                }
            }
        }
    }
}
