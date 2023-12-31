package com.hexchess.hexagonalchess.domain_layer.piecemove

import com.hexchess.hexagonalchess.data_layer.model.TilePair
import com.hexchess.hexagonalchess.domain_layer.BoardType
import com.hexchess.hexagonalchess.domain_layer.PieceColor
import com.hexchess.hexagonalchess.domain_layer.TileId

fun getForwardTwoPath(boardType: BoardType, color: PieceColor): List<TilePair> {
    return when(boardType){
        BoardType.DEFAULT ->
            when(color) {
                PieceColor.BLACK -> listOf(
                    TilePair(
                        startingPoint = TileId.A8,
                        endPoint = TileId.A6
                    ),
                    TilePair(
                        startingPoint = TileId.B8,
                        endPoint = TileId.B6
                    ),
                    TilePair(
                        startingPoint = TileId.C8,
                        endPoint = TileId.C6
                    ),
                    TilePair(
                        startingPoint = TileId.D8,
                        endPoint = TileId.D6
                    ),
                    TilePair(
                        startingPoint = TileId.E8,
                        endPoint = TileId.E6
                    ),
                    TilePair(
                        startingPoint = TileId.F8,
                        endPoint = TileId.F6
                    ),
                    TilePair(
                        startingPoint = TileId.G8,
                        endPoint = TileId.G6
                    ),
                    TilePair(
                        startingPoint = TileId.H8,
                        endPoint = TileId.H6
                    ),
                    TilePair(
                        startingPoint = TileId.I8,
                        endPoint = TileId.I6
                    )
                )
                PieceColor.WHITE -> listOf(
                    TilePair(
                        startingPoint = TileId.A1,
                        endPoint = TileId.A3
                    ),
                    TilePair(
                        startingPoint = TileId.B2,
                        endPoint = TileId.B4
                    ),
                    TilePair(
                        startingPoint = TileId.C3,
                        endPoint = TileId.C5
                    ),
                    TilePair(
                        startingPoint = TileId.D4,
                        endPoint = TileId.D6
                    ),
                    TilePair(
                        startingPoint = TileId.E5,
                        endPoint = TileId.E7
                    ),
                    TilePair(
                        startingPoint = TileId.F4,
                        endPoint = TileId.F6
                    ),
                    TilePair(
                        startingPoint = TileId.G3,
                        endPoint = TileId.G5
                    ),
                    TilePair(
                        startingPoint = TileId.H2,
                        endPoint = TileId.H4
                    ),
                    TilePair(
                        startingPoint = TileId.I1,
                        endPoint = TileId.I3
                    )
                )
            }
        BoardType.STAR_CHESS ->
            when(color) {
                PieceColor.BLACK -> listOf()
                PieceColor.WHITE -> listOf()
            }
        BoardType.SHAFRAN ->
            when(color) {
                PieceColor.BLACK -> listOf(
                    TilePair(
                        startingPoint = TileId.B7,
                        endPoint = TileId.B5
                    ),
                    TilePair(
                        startingPoint = TileId.C7,
                        endPoint = TileId.C5
                    ),
                    TilePair(
                        startingPoint = TileId.D7,
                        endPoint = TileId.D5
                    ),
                    TilePair(
                        startingPoint = TileId.E7,
                        endPoint = TileId.E5
                    ),
                    TilePair(
                        startingPoint = TileId.F7,
                        endPoint = TileId.F5
                    ),
                    TilePair(
                        startingPoint = TileId.G7,
                        endPoint = TileId.G5
                    ),
                    TilePair(
                        startingPoint = TileId.H7,
                        endPoint = TileId.H5
                    ),
                    TilePair(
                        startingPoint = TileId.I7,
                        endPoint = TileId.I5
                    ),
                    TilePair(
                        startingPoint = TileId.J7,
                        endPoint = TileId.J5
                    )
                )
                PieceColor.WHITE -> listOf(
                    TilePair(
                        startingPoint = TileId.B1,
                        endPoint = TileId.B3
                    ),
                    TilePair(
                        startingPoint = TileId.C2,
                        endPoint = TileId.C4
                    ),
                    TilePair(
                        startingPoint = TileId.D3,
                        endPoint = TileId.D5
                    ),
                    TilePair(
                        startingPoint = TileId.E4,
                        endPoint = TileId.E6
                    ),
                    TilePair(
                        startingPoint = TileId.F5,
                        endPoint = TileId.F7
                    ),
                    TilePair(
                        startingPoint = TileId.G4,
                        endPoint = TileId.G6
                    ),
                    TilePair(
                        startingPoint = TileId.H3,
                        endPoint = TileId.H5
                    ),
                    TilePair(
                        startingPoint = TileId.I2,
                        endPoint = TileId.I4
                    ),
                    TilePair(
                        startingPoint = TileId.J1,
                        endPoint = TileId.I3
                    )
                )
            }
        BoardType.BIG ->
            when(color) {
                PieceColor.BLACK -> listOf(
                    TilePair(
                        startingPoint = TileId.A8,
                        endPoint = TileId.A6
                    ),
                    TilePair(
                        startingPoint = TileId.B8,
                        endPoint = TileId.B6
                    ),
                    TilePair(
                        startingPoint = TileId.C8,
                        endPoint = TileId.C6
                    ),
                    TilePair(
                        startingPoint = TileId.D8,
                        endPoint = TileId.D6
                    ),
                    TilePair(
                        startingPoint = TileId.E8,
                        endPoint = TileId.E6
                    ),
                    TilePair(
                        startingPoint = TileId.F8,
                        endPoint = TileId.F6
                    ),
                    TilePair(
                        startingPoint = TileId.G8,
                        endPoint = TileId.G6
                    ),
                    TilePair(
                        startingPoint = TileId.H8,
                        endPoint = TileId.H6
                    ),
                    TilePair(
                        startingPoint = TileId.I8,
                        endPoint = TileId.I6
                    )
                )
                PieceColor.WHITE -> listOf(
                    TilePair(
                        startingPoint = TileId.A1,
                        endPoint = TileId.A3
                    ),
                    TilePair(
                        startingPoint = TileId.B2,
                        endPoint = TileId.B4
                    ),
                    TilePair(
                        startingPoint = TileId.C3,
                        endPoint = TileId.C5
                    ),
                    TilePair(
                        startingPoint = TileId.D4,
                        endPoint = TileId.D6
                    ),
                    TilePair(
                        startingPoint = TileId.E5,
                        endPoint = TileId.E7
                    ),
                    TilePair(
                        startingPoint = TileId.F4,
                        endPoint = TileId.F6
                    ),
                    TilePair(
                        startingPoint = TileId.G3,
                        endPoint = TileId.G5
                    ),
                    TilePair(
                        startingPoint = TileId.H2,
                        endPoint = TileId.H4
                    ),
                    TilePair(
                        startingPoint = TileId.I1,
                        endPoint = TileId.I3
                    )
                )
            }
    }
}