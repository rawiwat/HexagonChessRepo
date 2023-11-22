package com.hexchess.hexagonalchess.presentation_layer.viewmodel

import android.content.Context
import androidx.navigation.NavController
import com.hexchess.hexagonalchess.data_layer.database.DatabasePlayer
import com.hexchess.hexagonalchess.domain_layer.AuthenticationState
import com.hexchess.hexagonalchess.domain_layer.Route
import com.hexchess.hexagonalchess.domain_layer.player.manager.PlayerNameSharedPref
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SignInViewModel(
    private val databasePlayer: DatabasePlayer
) {
    private val _nameMessage = MutableStateFlow("enter name")
    val nameMessage: StateFlow<String> = _nameMessage
    private val _passwordMessage = MutableStateFlow("enter password")
    val passwordMessage: StateFlow<String> = _passwordMessage

    private val _showPassword = MutableStateFlow(false)
    val showPassword: StateFlow<Boolean> = _showPassword

    private val _userNameState = MutableStateFlow(AuthenticationState.NEUTRAL)
    val userNameState:StateFlow<AuthenticationState> = _userNameState

    private val _passwordState = MutableStateFlow(AuthenticationState.NEUTRAL)
    val passwordState:StateFlow<AuthenticationState> = _passwordState

    private fun checkIfPlayerExists(name:String, resolve:(Boolean) ->Unit) {
        databasePlayer.checkIfPlayerExists(name, resolve)
    }

    private fun loginAndGotoMain(name: String, navController: NavController, context: Context) {
        if (_userNameState.value == AuthenticationState.VALID && _passwordState.value == AuthenticationState.VALID) {
            PlayerNameSharedPref(context).savePlayer(name)
            navController.navigate(Route.main)
        }
    }

    private fun passwordIncorrect() {
        _passwordState.value = AuthenticationState.INVALID
        _passwordMessage.value = "incorrect password"
    }

    private fun nameIsReady() {
        _userNameState.value = AuthenticationState.VALID
        _nameMessage.value = "name Valid"
    }

    private fun nameNotFind() {
        _userNameState.value = AuthenticationState.INVALID
        _nameMessage.value = "name Invalid"
    }

    private fun passwordIsReady() {
        _passwordState.value = AuthenticationState.VALID
        _passwordMessage.value = "password Valid"
    }

    private fun checkPassword(
        name: String,
        password: String,
        context: Context,
        navController: NavController
    ) {
        databasePlayer.checkPassword(
            name , password
        ) { passwordCorrect ->
            if (passwordCorrect) {
                passwordIsReady()
                loginAndGotoMain(
                    name,
                    navController,
                    context
                )
            } else {
                passwordIncorrect()
            }
        }
    }

    fun onClickSignIn(
        name: String,
        password: String,
        context: Context,
        navController: NavController
    ) {
        checkIfPlayerExists(name) {
                playerExisted ->
            if (playerExisted) {
                nameIsReady()
            } else {
                nameNotFind()
            }
        }
        checkPassword(
            name, password , context, navController
        )
    }

    fun showPassword() {
        _showPassword.value = !_showPassword.value
    }
}