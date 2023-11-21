package com.example.hexagonalchess.data_layer.model.collection.piece

import com.example.hexagonalchess.data_layer.model.collection.Collectable
import com.example.hexagonalchess.domain_layer.ChessSkin

class ChessPieceInShop {
    private val chinesePiece = Collectable(
        name = ChessSkin.CHINESE.name
    )
    private val medievalPiece = Collectable(
        name = ChessSkin.MEDIEVAL.name
    )

    val allCollectablePiece = listOf(chinesePiece, medievalPiece)
}