package com.hexchess.hexagonalchess.presentation_layer.viewmodel

import androidx.lifecycle.ViewModel
import com.hexchess.hexagonalchess.data_layer.database.DatabasePlayer
import com.hexchess.hexagonalchess.data_layer.database.PlayerSearchCallback
import com.hexchess.hexagonalchess.data_layer.model.player.Player
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainMenuViewModel(
    private val databasePlayer: DatabasePlayer,
    playerName:String
) : ViewModel() {
    private val _quitMenuActive = MutableStateFlow(false)
    val quitMenuActive:StateFlow<Boolean> = _quitMenuActive

    private val _player = MutableStateFlow(Player())
    val player:StateFlow<Player> = _player

    fun turnOnQuitMenu() {
        _quitMenuActive.value = true
    }

    fun turnOffQuitMenu() {
        _quitMenuActive.value = false
    }

    init {
        databasePlayer.searchPlayerByName(
            playerName , object : PlayerSearchCallback {
                override fun onPlayerFound(player: Player) {
                   _player.value = player
                }

                override fun onPlayerNotFound() {
                    // Handle the case when player is not found
                }
            }
        )
    }
}