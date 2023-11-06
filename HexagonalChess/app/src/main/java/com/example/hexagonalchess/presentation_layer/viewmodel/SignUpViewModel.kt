package com.example.hexagonalchess.presentation_layer.viewmodel

import androidx.lifecycle.ViewModel
import com.example.hexagonalchess.data_layer.database.DatabasePlayer
import com.example.hexagonalchess.domain_layer.player.manager.ManagePlayer
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SignUpViewModel(
    private val playerManager: ManagePlayer,
    private val databasePlayer: DatabasePlayer
):ViewModel() {
    //val playerManager: ManagePlayer = FirebasePlayerManager()
    private val _nameMessage = MutableStateFlow("enter name")
    val nameMessage:StateFlow<String> = _nameMessage
    private val _passwordMessage = MutableStateFlow("enter password")
    val passwordMessage:StateFlow<String> = _passwordMessage

    private var nameReady = false

    private var passwordReady = false

    fun checkIfPlayerExists(name:String, resolve:(Boolean) ->Unit) {
        playerManager.checkIfPlayerExists(name, resolve)
    }

    fun addNewPlayer(name: String ,password: String) {
        if (nameReady && passwordReady) {
            databasePlayer.addNewPlayer(name, password)
        }
    }

    fun nameAlreadyExisted(name: String) {
        _nameMessage.value = "$name was already used"
    }

    fun passwordTooShort() {
        _passwordMessage.value = "password too short"
    }

    fun nameIsReady() {
        nameReady = true
        _nameMessage.value = "name Valid"
    }

    fun passwordIsReady() {
        passwordReady = true
        _passwordMessage.value = "password Valid"
    }
}