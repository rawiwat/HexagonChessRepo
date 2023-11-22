package com.hexchess.hexagonalchess.data_layer.chess_board_data.big

import com.hexchess.hexagonalchess.data_layer.model.tile.Tile
import com.hexchess.hexagonalchess.domain_layer.TileColor
import com.hexchess.hexagonalchess.domain_layer.TileId

class BigColumnK {
    private val k1 = Tile(
        id = TileId.K1,
        color = TileColor.DARK,
        topTile = TileId.K2,
        upperRightTile = null,
        underRightTile = null,
        bottomTile = null,
        underLeftTile = TileId.J1,
        upperLeftTile = TileId.J2
    )

    private val k2 = Tile(
        id = TileId.K2,
        color = TileColor.MID,
        topTile = TileId.K3,
        upperRightTile = null,
        underRightTile = null,
        bottomTile = TileId.K1,
        underLeftTile = TileId.J2,
        upperLeftTile = TileId.J3
    )

    private val k3 = Tile(
        id = TileId.K3,
        color = TileColor.LIGHT,
        topTile = TileId.K4,
        upperRightTile = null,
        underRightTile = null,
        bottomTile = TileId.K2,
        underLeftTile = TileId.J3,
        upperLeftTile = TileId.J4
    )

    private val k4 = Tile(
        id = TileId.K4,
        color = TileColor.DARK,
        topTile = TileId.K6,
        upperRightTile = null,
        underRightTile = null,
        bottomTile = TileId.K3,
        underLeftTile = TileId.J4,
        upperLeftTile = TileId.J5
    )

    private val k5 = Tile(
        id = TileId.K5,
        color = TileColor.MID,
        topTile = TileId.K6,
        upperRightTile = null,
        underRightTile = null,
        bottomTile = TileId.K4,
        underLeftTile = TileId.J5,
        upperLeftTile = TileId.J6
    )

    private val k6 = Tile(
        id = TileId.K6,
        color = TileColor.LIGHT,
        topTile = null,
        upperRightTile = null,
        underRightTile = null,
        bottomTile = TileId.K5,
        underLeftTile = TileId.J6,
        upperLeftTile = TileId.J7
    )

    val columnK = listOf(k6,k5,k4,k3,k2,k1)

}