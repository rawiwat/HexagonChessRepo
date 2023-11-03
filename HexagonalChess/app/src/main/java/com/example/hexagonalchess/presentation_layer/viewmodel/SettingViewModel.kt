package com.example.hexagonalchess.presentation_layer.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.hexagonalchess.domain_layer.SettingState
import com.example.hexagonalchess.domain_layer.TileTheme
import com.example.hexagonalchess.domain_layer.theme_setting.ThemeSharedPrefs
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SettingViewModel(
    context: Context
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
        _settingState.value = SettingState.THEME
        println(_settingState.value)
    }

    fun turnOffSettingMenu() {
        _settingState.value = SettingState.NONE
    }
}