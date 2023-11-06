package com.example.hexagonalchess.domain_layer.player.manager

interface ManagePlayer {
    fun checkIfPlayerExists(inputName: String, callback: (Boolean) -> Unit)
}