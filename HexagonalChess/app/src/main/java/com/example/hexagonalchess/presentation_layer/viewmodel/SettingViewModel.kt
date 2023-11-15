package com.example.hexagonalchess.presentation_layer.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.hexagonalchess.domain_layer.Route
import com.example.hexagonalchess.domain_layer.SettingState
import com.example.hexagonalchess.domain_layer.TileTheme
import com.example.hexagonalchess.domain_layer.player.manager.PlayerNameSharedPref
import com.example.hexagonalchess.domain_layer.theme_setting.ThemeSharedPrefs
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SettingViewModel(
    context: Context,
    private val navController: NavController,
    private val playerNameSharedPref: PlayerNameSharedPref
): ViewModel() {
    private val themeSharedPref = ThemeSharedPrefs(context)

    private val _currentTheme = MutableStateFlow(themeSharedPref.getTheme())
    val currentTheme: StateFlow<TileTheme> = _currentTheme

    private val _settingState = MutableStateFlow(SettingState.NONE)
    val settingState: StateFlow<SettingState> = _settingState

    fun changeTheme(chosenTheme: TileTheme) {
        themeSharedPref.saveTheme(chosenTheme)
        _currentTheme.value = chosenTheme
    }

    fun turnOnTheme() {
        if (_settingState.value == SettingState.NONE) {
            _settingState.value = SettingState.THEME
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
        if (_settingState.value == SettingState.NONE) {
            _settingState.value = SettingState.NONE
        }
    }

    fun backToMain() {
        navController.navigate(Route.main)
    }
}