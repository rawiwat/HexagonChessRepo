package com.example.hexagonalchess.domain_layer.player.manager

import android.content.Context
import android.content.SharedPreferences
import com.example.hexagonalchess.domain_layer.TileTheme

class PlayerNameSharedPref(context: Context) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("MyPlayer", Context.MODE_PRIVATE)

    fun savePlayer(name: String) {
        sharedPreferences
            .edit()
            .putString("player_name", name)
            .apply()
    }
    fun getPlayerName():String? {
        return sharedPreferences
            .getString("player_name", null)
    }
}