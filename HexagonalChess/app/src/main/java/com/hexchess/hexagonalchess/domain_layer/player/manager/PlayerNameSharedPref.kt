package com.hexchess.hexagonalchess.domain_layer.player.manager

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences

class PlayerNameSharedPref(val context: Context) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("MyPlayer", Context.MODE_PRIVATE)

    fun savePlayer(name: String) {
        sharedPreferences
            .edit()
            .putString("player_name", name)
            .apply()
        val sendPlayer = Intent("CurrentPlayer")
        sendPlayer.putExtra("CurrentPlayer", name)
        context.sendBroadcast(sendPlayer)
    }

    fun getPlayerName():String? {
        return sharedPreferences
            .getString("player_name", null)
    }

    fun logOutPlayer() {
        sharedPreferences
            .edit()
            .putString("player_name", null)
            .apply()
    }
}