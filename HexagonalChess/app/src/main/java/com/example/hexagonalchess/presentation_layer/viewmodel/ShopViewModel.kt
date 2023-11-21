package com.example.hexagonalchess.presentation_layer.viewmodel

import androidx.lifecycle.ViewModel
import com.example.hexagonalchess.data_layer.database.DatabasePlayer
import com.example.hexagonalchess.data_layer.database.PlayerSearchCallback
import com.example.hexagonalchess.data_layer.model.collection.Collectable
import com.example.hexagonalchess.data_layer.model.collection.piece.ChessPieceInShop
import com.example.hexagonalchess.data_layer.model.collection.tile.TileThemeInShop
import com.example.hexagonalchess.data_layer.model.player.Player
import com.example.hexagonalchess.domain_layer.ChessSkin
import com.example.hexagonalchess.domain_layer.TileTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ShopViewModel(
    private val databasePlayer: DatabasePlayer,
    val playerName: String
): ViewModel() {
    private val _itemPrice = MutableStateFlow(0)
    val itemPrice: StateFlow<Int> = _itemPrice
    private val _currentCollection = MutableStateFlow(listOf<Collectable>())
    val currentCollection: StateFlow<List<Collectable>> = _currentCollection
    private val allCollectableTile = TileThemeInShop().allCollectableTile
    private val allCollectableSkin = ChessPieceInShop().allCollectablePiece
    private val _backMenu = MutableStateFlow(false)
    val backMenu:StateFlow<Boolean> = _backMenu

    private fun getCollection() {
        databasePlayer.searchPlayerByName(
            playerName, object : PlayerSearchCallback {
                override fun onPlayerFound(player: Player) {
                    _currentCollection.value = player.collection
                }

                override fun onPlayerNotFound() {
                    // Handle the case when player is not found
                }
            })
    }

    private fun calculatePrice() {
        databasePlayer.calculatePrice()
    }

    fun buy(chosenItems: Collectable) {
        databasePlayer.updatePlayerCoin(playerName, (_itemPrice.value * -1).toLong())
        databasePlayer.addToCollection(chosenItems)
    }

    fun getTileInShop(): List<Collectable> {
        return allCollectableTile - _currentCollection.value.toSet()
    }
    fun getPieceInShop(): List<Collectable> {
        return allCollectableSkin - _currentCollection.value.toSet()
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