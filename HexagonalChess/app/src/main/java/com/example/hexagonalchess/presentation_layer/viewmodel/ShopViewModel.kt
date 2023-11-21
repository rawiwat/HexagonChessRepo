package com.example.hexagonalchess.presentation_layer.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.hexagonalchess.R
import com.example.hexagonalchess.data_layer.database.DatabasePlayer
import com.example.hexagonalchess.data_layer.database.PlayerSearchCallback
import com.example.hexagonalchess.data_layer.model.collection.Collectable
import com.example.hexagonalchess.data_layer.model.collection.piece.ChessPieceInShop
import com.example.hexagonalchess.data_layer.model.collection.tile.TileThemeInShop
import com.example.hexagonalchess.data_layer.model.player.Player
import com.example.hexagonalchess.domain_layer.playSoundEffect
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ShopViewModel(
    private val databasePlayer: DatabasePlayer,
    val playerName: String,
    val context:Context
): ViewModel() {
    private val _itemPrice = MutableStateFlow(0)
    val itemPrice: StateFlow<Int> = _itemPrice
    private val _playerCoin = MutableStateFlow(0)
    val playerCoin: StateFlow<Int> = _playerCoin
    private var currentCollection = mutableListOf<Collectable>()
    private val allCollectableTile = TileThemeInShop().allCollectableTile
    private val allCollectableSkin = ChessPieceInShop().allCollectablePiece
    private val _backMenu = MutableStateFlow(false)
    val backMenu:StateFlow<Boolean> = _backMenu

    private fun getCollection() {
        databasePlayer.searchPlayerByName(
            playerName, object : PlayerSearchCallback {
                override fun onPlayerFound(player: Player) {
                    currentCollection = player.collection.toMutableList()
                    _playerCoin.value = player.coin
                }

                override fun onPlayerNotFound() {
                    // Handle the case when player is not found
                }
            })
    }

    private fun calculatePrice() {
        databasePlayer.calculatePrice()
    }

    fun buy(chosenItem: Collectable) {
        if (_playerCoin.value >= _itemPrice.value) {
            _playerCoin.value -= _itemPrice.value
            databasePlayer.buy(playerName, item = chosenItem) {
                getCollection()
                calculatePrice()
            }
            currentCollection.add(chosenItem)
        } else {
            playSoundEffect(
                context = context,
                R.raw.beep
            )
        }
    }

    fun getTileInShop(): List<Collectable> {
        return allCollectableTile - currentCollection.toSet()
    }
    fun getPieceInShop(): List<Collectable> {
        return allCollectableSkin - currentCollection.toSet()
    }

    fun backMenu(input:Boolean) {
        _backMenu.value = input
    }
    init {
        getCollection()
        databasePlayer.updatePrice = {
            _itemPrice.value = databasePlayer.currentPrice
        }
        calculatePrice()
    }
}