package com.example.hexagonalchess.presentation_layer.viewmodel

import android.service.quicksettings.Tile
import androidx.lifecycle.ViewModel
import com.example.hexagonalchess.domain_layer.PieceColor

class ChessMultiPlayerViewModel(
    playerColor: PieceColor,
    opponentColor: PieceColor,
    board: List<Tile>
): ViewModel() {
}