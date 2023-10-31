package com.example.hexagonalchess.data_layer.chess_board_data.big

import com.example.hexagonalchess.data_layer.model.tile.Tile
import com.example.hexagonalchess.domain_layer.TileColor
import com.example.hexagonalchess.domain_layer.TileId

class BigColumnA {
    private val a1 = Tile(
        id = TileId.A1,
        color = TileColor.DARK,
        topTile = TileId.A2,
        upperRightTile = TileId.B2,
        underRightTile = TileId.B1,
        bottomTile = null,
        underLeftTile = null,
        upperLeftTile = null
    )

    private val a2 = Tile(
        id = TileId.A2,
        color = TileColor.MID,
        topTile = TileId.A3,
        upperRightTile = TileId.B3,
        underRightTile = TileId.B2,
        bottomTile = TileId.A1,
        underLeftTile = null,
        upperLeftTile = null
    )

    private val a3 = Tile(
        id = TileId.A3,
        color = TileColor.LIGHT,
        topTile = TileId.A4,
        upperRightTile = TileId.B4,
        underRightTile = TileId.B3,
        bottomTile = TileId.A2,
        underLeftTile = null,
        upperLeftTile = null
    )

    private val a4 = Tile(
        id = TileId.A4,
        color = TileColor.DARK,
        topTile = TileId.A5,
        upperRightTile = TileId.B5,
        underRightTile = TileId.B4,
        bottomTile = TileId.A3,
        underLeftTile = null,
        upperLeftTile = null
    )

    private val a5 = Tile(
        id = TileId.A5,
        color = TileColor.MID,
        topTile = TileId.A6,
        upperRightTile = TileId.B6,
        underRightTile = TileId.B5,
        bottomTile = TileId.A4,
        underLeftTile = null,
        upperLeftTile = null
    )

    private val a6 = Tile(
        id = TileId.A6,
        color = TileColor.LIGHT,
        topTile = null,
        upperRightTile = TileId.B7,
        underRightTile = TileId.B6,
        bottomTile = TileId.A5,
        underLeftTile = null,
        upperLeftTile = null
    )

    val columnA = listOf(a6,a5,a4,a3,a2,a1)
}