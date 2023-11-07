package com.example.hexagonalchess.presentation_layer.viewmodel

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.hexagonalchess.data_layer.database.DatabasePlayer
import com.example.hexagonalchess.domain_layer.Route
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SignUpViewModel(
    private val databasePlayer: DatabasePlayer
):ViewModel() {
    //val playerManager: ManagePlayer = FirebasePlayerManager()
    private val _nameMessage = MutableStateFlow("enter name")
    val nameMessage:StateFlow<String> = _nameMessage
    private val _passwordMessage = MutableStateFlow("enter password")
    val passwordMessage:StateFlow<String> = _passwordMessage

    private val _showPassword = MutableStateFlow(false)
    val showPassword: StateFlow<Boolean> = _showPassword

    private var nameReady = false

    private var passwordReady = false

    private fun checkIfPlayerExists(name:String, resolve:(Boolean) ->Unit) {
        databasePlayer.checkIfPlayerExists(name, resolve)
    }

    private fun addNewPlayer(name: String, password: String, navController: NavController) {
        if (nameReady && passwordReady) {
            databasePlayer.addNewPlayer(name, password)
            navController.navigate(Route.main)
        }
    }

    private fun nameAlreadyExisted(name: String) {
        _nameMessage.value = "$name was already used"
        nameReady = false
    }

    private fun passwordTooShort() {
        _passwordMessage.value = "password too short"
        passwordReady = false
    }

    private fun nameIsReady() {
        nameReady = true
        _nameMessage.value = "name Valid"
    }

    private fun passwordIsReady() {
        passwordReady = true
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

    fun showPassword() {
        _showPassword.value = !_showPassword.value
    }
}