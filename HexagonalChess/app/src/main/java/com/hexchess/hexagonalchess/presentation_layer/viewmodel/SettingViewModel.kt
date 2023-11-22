package com.hexchess.hexagonalchess.presentation_layer.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.hexchess.hexagonalchess.data_layer.database.DatabasePlayer
import com.hexchess.hexagonalchess.data_layer.database.PlayerSearchCallback
import com.hexchess.hexagonalchess.data_layer.model.player.Player
import com.hexchess.hexagonalchess.domain_layer.ChessSkin
import com.hexchess.hexagonalchess.domain_layer.CollectableType
import com.hexchess.hexagonalchess.domain_layer.Route
import com.hexchess.hexagonalchess.domain_layer.SettingState
import com.hexchess.hexagonalchess.domain_layer.TileTheme
import com.hexchess.hexagonalchess.domain_layer.player.manager.PlayerNameSharedPref
import com.hexchess.hexagonalchess.domain_layer.skin_setting.SkinSharedPrefs
import com.hexchess.hexagonalchess.domain_layer.theme_setting.ThemeSharedPrefs
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SettingViewModel(
    context: Context,
    private val navController: NavController,
    private val playerNameSharedPref: PlayerNameSharedPref,
    private val databasePlayer: DatabasePlayer
): ViewModel() {
    private val themeSharedPref = ThemeSharedPrefs(context)
    private val skinSharedPrefs = SkinSharedPrefs(context)

    private val _currentTheme = MutableStateFlow(themeSharedPref.getTheme())
    val currentTheme: StateFlow<TileTheme> = _currentTheme

    private val _currentSkin = MutableStateFlow(skinSharedPrefs.getSkin())
    val currentSkin:StateFlow<ChessSkin> = _currentSkin

    private val _settingState = MutableStateFlow(SettingState.NONE)
    val settingState: StateFlow<SettingState> = _settingState

    val playersTiles = mutableListOf<TileTheme>()
    val playerChessSkin = mutableListOf<ChessSkin>()

    fun changeTheme(chosenTheme: TileTheme) {
        themeSharedPref.saveTheme(chosenTheme)
        _currentTheme.value = chosenTheme
    }

    fun turnOnTheme() {
        if (_settingState.value == SettingState.NONE) {
            _settingState.value = SettingState.THEME
        }
     }

    fun changeSkin(chosenSkin: ChessSkin) {
        skinSharedPrefs.saveSkin(chosenSkin)
        _currentSkin.value = chosenSkin
    }

    fun turnOnSkin() {
        if (_settingState.value == SettingState.NONE) {
            _settingState.value = SettingState.SKIN
        }
     }

    fun turnOnLogOutMenu() {
        if (_settingState.value == SettingState.NONE) {
            _settingState.value = SettingState.LOG_OUT
        }
    }

    fun logOut() {
        playerNameSharedPref.logOutPlayer()
        //navController.navigate(Route.signIn)
    }

    fun turnOffSettingMenu() {
        if (_settingState.value != SettingState.NONE) {
            _settingState.value = SettingState.NONE
        }
    }

    fun backToMain() {
        navController.navigate(Route.main)
    }

    init {
        val playerName = playerNameSharedPref.getPlayerName().toString()
        println("init setting : playername is $playerName")
        databasePlayer.searchPlayerByName(playerName, object : PlayerSearchCallback {
            override fun onPlayerFound(player: Player) {
                val collection = player.collection
                for (collectable in collection) {
                    when(collectable.type) {
                        CollectableType.TILE_THEME ->
                            playersTiles.add(TileTheme.valueOf(collectable.name))
                        CollectableType.PIECE_THEME ->
                            playerChessSkin.add(ChessSkin.valueOf(collectable.name))
                    }
                }
            }
            override fun onPlayerNotFound() {
                // Handle error
            }

        })
    }
}