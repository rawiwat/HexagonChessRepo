package com.example.hexagonalchess.presentation_layer.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
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

    private val _settingOpen = MutableStateFlow(false)
    val settingThemOpen: StateFlow<Boolean> = _settingOpen

    fun changeTheme(chosenTheme: TileTheme) {
        themeSharedPref.saveTheme(chosenTheme)
    }

    fun turnOnTheme() {
        _settingOpen.value = !_settingOpen.value
    }

    fun turnOffTheme() {
        _settingOpen.value = !_settingOpen.value
    }

}