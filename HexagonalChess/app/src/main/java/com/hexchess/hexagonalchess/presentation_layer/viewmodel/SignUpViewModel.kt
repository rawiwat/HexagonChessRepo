package com.hexchess.hexagonalchess.presentation_layer.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.hexchess.hexagonalchess.data_layer.database.DatabasePlayer
import com.hexchess.hexagonalchess.domain_layer.AuthenticationState
import com.hexchess.hexagonalchess.domain_layer.Route
import com.hexchess.hexagonalchess.domain_layer.player.manager.PlayerNameSharedPref
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SignUpViewModel(
    private val databasePlayer: DatabasePlayer,
    val context: Context
):ViewModel() {
    private val _nameMessage = MutableStateFlow("enter name")
    val userNameMessage:StateFlow<String> = _nameMessage
    private val _passwordMessage = MutableStateFlow("enter password")
    val passwordMessage:StateFlow<String> = _passwordMessage

    private val _userNameState = MutableStateFlow(AuthenticationState.NEUTRAL)
    val userNameState:StateFlow<AuthenticationState> = _userNameState

    private val _passwordState = MutableStateFlow(AuthenticationState.NEUTRAL)
    val passwordState:StateFlow<AuthenticationState> = _passwordState

    private fun checkIfPlayerExists(name:String, resolve:(Boolean) ->Unit) {
        databasePlayer.checkIfPlayerExists(name, resolve)
    }

    private fun addNewPlayer(name: String, password: String, navController: NavController) {
        if (_userNameState.value == AuthenticationState.VALID && _passwordState.value == AuthenticationState.VALID) {
            PlayerNameSharedPref(context).savePlayer(name)
            databasePlayer.addNewPlayer(name, password)
            navController.navigate(Route.main)
        }
    }

    private fun nameAlreadyExisted(name: String) {
        _userNameState.value = AuthenticationState.INVALID
        _nameMessage.value = "$name was already used"
    }

    private fun nameContainForbidden() {
        _userNameState.value = AuthenticationState.INVALID
        _nameMessage.value = "blank space / and _ is not allow in player name"
    }

    private fun passwordTooShort() {
        _passwordState.value = AuthenticationState.INVALID
        _passwordMessage.value = "password has to contains at least 8 letters"
    }

    private fun nameIsReady() {
        _userNameState.value = AuthenticationState.VALID
        _nameMessage.value = "name Valid"
    }

    private fun passwordIsReady() {
        _passwordState.value = AuthenticationState.VALID
        _passwordMessage.value = "password Valid"
    }

    fun onClickSignUp(
        name: String,
        password: String,
        navController: NavController
    ) {
        checkIfPlayerExists(name) { playerExisted ->
            if (playerExisted) {
                nameAlreadyExisted(name)
            } else if (databasePlayer.nameHasForbiddenChar(name)) {
                nameContainForbidden()
            } else {
                nameIsReady()
            }

            if (password.length >= 8) {
                passwordIsReady()
            } else {
                passwordTooShort()
            }

            addNewPlayer(
                name,
                password,
                navController
            )
        }
    }
}