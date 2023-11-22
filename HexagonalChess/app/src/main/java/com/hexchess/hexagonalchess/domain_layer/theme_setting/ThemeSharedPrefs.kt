package com.hexchess.hexagonalchess.domain_layer.theme_setting

import android.content.Context
import android.content.SharedPreferences
import com.hexchess.hexagonalchess.domain_layer.TileTheme

class ThemeSharedPrefs(context: Context) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("MyTheme", Context.MODE_PRIVATE)

    fun saveTheme(chosenTheme: TileTheme) {
        sharedPreferences.edit().putString("theme", chosenTheme.name).apply()
    }

    fun getTheme(): TileTheme {
        val savedValue = sharedPreferences.getString("theme",TileTheme.DEFAULT.name)
        return TileTheme.valueOf(savedValue ?: TileTheme.DEFAULT.name)
    }
}




