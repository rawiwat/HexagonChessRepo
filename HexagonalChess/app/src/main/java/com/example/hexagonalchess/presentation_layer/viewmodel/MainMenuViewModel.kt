package com.example.hexagonalchess.presentation_layer.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainMenuViewModel : ViewModel() {
    private val _quitMenuActive = MutableStateFlow(false)
    val quitMenuActive:StateFlow<Boolean> = _quitMenuActive

    fun turnOnQuitMenu() {
        _quitMenuActive.value = true
    }

    fun turnOffQuitMenu() {
        _quitMenuActive.value = false
    }
}