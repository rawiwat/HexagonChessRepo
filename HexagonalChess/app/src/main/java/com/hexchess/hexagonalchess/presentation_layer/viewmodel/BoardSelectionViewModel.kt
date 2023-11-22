package com.hexchess.hexagonalchess.presentation_layer.viewmodel

import androidx.lifecycle.ViewModel
import com.hexchess.hexagonalchess.domain_layer.BoardType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class BoardSelectionViewModel : ViewModel() {
    private val _currentBoard = MutableStateFlow(BoardType.DEFAULT)
    val currentBoard:StateFlow<BoardType> = _currentBoard

    fun changeBoard(chosenBoard: BoardType) {
        _currentBoard.value = chosenBoard
    }
}