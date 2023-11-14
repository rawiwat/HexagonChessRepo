package com.example.hexagonalchess.data_layer.model.tile

import com.example.hexagonalchess.domain_layer.TileColor
import com.example.hexagonalchess.domain_layer.TileId
import com.example.hexagonalchess.data_layer.model.pieces.ChessPiece
import com.google.errorprone.annotations.Immutable

@Immutable
open class Tile(
    val id: TileId = TileId.A1,
    val color: TileColor = TileColor.MID,
    var topTile: TileId? = null,
    var upperRightTile: TileId? = null,
    var underRightTile: TileId? = null,
    var bottomTile: TileId? = null,
    var underLeftTile: TileId? = null,
    var upperLeftTile: TileId? = null,
    var isAPossibleMove: Boolean = false,
    var chessPiece: ChessPiece? = null
) {
    fun copy(): Tile {
        return Tile(
            this.id,
            this.color,
            this.topTile,
            this.upperRightTile,
            this.underRightTile,
            this.bottomTile,
            this.underLeftTile,
            this.upperLeftTile,
            this.isAPossibleMove,
            this.chessPiece
        )
    }
}