package com.hexchess.hexagonalchess.domain_layer.skin_setting

import android.content.Context
import android.content.SharedPreferences
import com.hexchess.hexagonalchess.domain_layer.ChessSkin

class SkinSharedPrefs(context: Context) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("MyChessSkin", Context.MODE_PRIVATE)

    fun saveSkin(chosenSkin: ChessSkin) {
        sharedPreferences.edit().putString("chessSkin", chosenSkin.name).apply()
    }

    fun getSkin(): ChessSkin {
        val savedValue = sharedPreferences.getString("chessSkin", ChessSkin.DEFAULT.name)
        return ChessSkin.valueOf(savedValue ?: ChessSkin.DEFAULT.name)
    }
}
