package com.example.hexagonalchess.presentation_layer.viewmodel

import androidx.lifecycle.ViewModel
import com.example.hexagonalchess.domain_layer.BoardType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class BoardSelectionViewModel : ViewModel() {
    private val _currentBoard = MutableStateFlow(BoardType.DEFAULT)
    val currentBoard:StateFlow<BoardType> = _currentBoard

    private val _loadingNextScreen = MutableStateFlow(false)
    val loadingNextScreen:StateFlow<Boolean> = _loadingNextScreen

    fun changeBoard(chosenBoard: BoardType) {
        _currentBoard.value = chosenBoard
    }

    fun loadScreen() {
        _loadingNextScreen.value = true
    }
}