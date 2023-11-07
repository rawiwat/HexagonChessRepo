package com.example.hexagonalchess.presentation_layer.viewmodel

import android.content.Context
import androidx.navigation.NavController
import com.example.hexagonalchess.data_layer.database.DatabasePlayer
import com.example.hexagonalchess.domain_layer.Route
import com.example.hexagonalchess.domain_layer.player.manager.PlayerNameSharedPref
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

    private var nameReady = false

    private var passwordReady = false

    private fun checkIfPlayerExists(name:String, resolve:(Boolean) ->Unit) {
        databasePlayer.checkIfPlayerExists(name, resolve)
    }

    private fun loginAndGotoMain(name: String, navController: NavController, context: Context) {
        if (nameReady && passwordReady) {
            PlayerNameSharedPref(context).savePlayer(name)
            navController.navigate(Route.main)
        }
    }

    private fun passwordIncorrect() {
        _passwordMessage.value = "incorrect password"
        passwordReady = false
    }

    private fun nameIsReady() {
        nameReady = true
        _nameMessage.value = "name Valid"
    }

    private fun nameNotFind() {
        nameReady = false
        _nameMessage.value = "name Invalid"
    }

    private fun passwordIsReady() {
        passwordReady = true
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