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

    private var nameReady = false

    private var passwordReady = false

    fun checkIfPlayerExists(name:String, resolve:(Boolean) ->Unit) {
        databasePlayer.checkIfPlayerExists(name, resolve)
    }

    fun loginAndGotoMain(name: String, navController: NavController,context: Context) {
        if (nameReady && passwordReady) {
            PlayerNameSharedPref(context).savePlayer(name)
            navController.navigate(Route.main)
        }
    }

    fun passwordIncorrect() {
        _passwordMessage.value = "incorrect password"
        passwordReady = false
    }

    fun nameIsReady() {
        nameReady = true
        _nameMessage.value = "name Valid"
    }

    fun nameNotFind() {
        nameReady = false
        _nameMessage.value = "name Invalid"
    }

    fun passwordIsReady() {
        passwordReady = true
        _passwordMessage.value = "password Valid"
    }

    fun checkPassword() {

    }
}