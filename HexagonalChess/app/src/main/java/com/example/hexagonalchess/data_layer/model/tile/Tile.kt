package com.example.hexagonalchess.data_layer.model.tile

import com.example.hexagonalchess.domain_layer.TileColor
import com.example.hexagonalchess.domain_layer.TileId
import com.example.hexagonalchess.data_layer.model.pieces.ChessPiece
import com.google.errorprone.annotations.Immutable

@Immutable
open class Tile(
    val id: TileId,
    val color: TileColor,
    var topTile: TileId?,
    var upperRightTile: TileId?,
    var underRightTile: TileId?,
    var bottomTile: TileId?,
    var underLeftTile: TileId?,
    var upperLeftTile: TileId?,
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