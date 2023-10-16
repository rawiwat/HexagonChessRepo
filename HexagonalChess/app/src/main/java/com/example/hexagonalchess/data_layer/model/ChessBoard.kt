package com.example.hexagonalchess.data_layer.model

import androidx.lifecycle.ViewModel
import com.example.hexagonalchess.TileColor
import com.example.hexagonalchess.TileId
import com.example.hexagonalchess.data_layer.model.tile.Tile

class ChessBoard:ViewModel() {
    val a1 = Tile(TileId.A1,TileColor.DARK)
    val a2 = Tile(TileId.A2,TileColor.MID)
    val a3 = Tile(TileId.A3,TileColor.LIGHT)
    val a4 = Tile(TileId.A4,TileColor.DARK)
    val a5 = Tile(TileId.A5,TileColor.LIGHT)
    val a6 = Tile(TileId.A6,TileColor.MID)
    val a7 = Tile(TileId.A7,TileColor.DARK)
    val a8 = Tile(TileId.A8,TileColor.LIGHT)

    val b1 = Tile(TileId.B1,TileColor.DARK)
    val b2 = Tile(TileId.B2,TileColor.MID)
    val b3 = Tile(TileId.B3,TileColor.LIGHT)
    val b4 = Tile(TileId.B4,TileColor.DARK)
    val b5 = Tile(TileId.B5,TileColor.LIGHT)
    val b6 = Tile(TileId.B6,TileColor.MID)
    val b7 = Tile(TileId.B7,TileColor.DARK)
    val b8 = Tile(TileId.B8,TileColor.LIGHT)
    val b9 = Tile(TileId.B9,TileColor.LIGHT)

    val c1 = Tile(TileId.C1,TileColor.DARK)
    val c2 = Tile(TileId.C2,TileColor.MID)
    val c3 = Tile(TileId.C3,TileColor.LIGHT)
    val c4 = Tile(TileId.C4,TileColor.DARK)
    val c5 = Tile(TileId.C5,TileColor.LIGHT)
    val c6 = Tile(TileId.C6,TileColor.MID)
    val c7 = Tile(TileId.C7,TileColor.DARK)
    val c8 = Tile(TileId.C8,TileColor.LIGHT)
    val c9 = Tile(TileId.C9,TileColor.LIGHT)
    val c10 = Tile(TileId.C10,TileColor.DARK)

    val d1 = Tile(TileId.D1,TileColor.DARK)
    val d2 = Tile(TileId.D2,TileColor.MID)
    val d3 = Tile(TileId.D3,TileColor.LIGHT)
    val d4 = Tile(TileId.D4,TileColor.DARK)
    val d5 = Tile(TileId.D5,TileColor.LIGHT)
    val d6 = Tile(TileId.D6,TileColor.MID)
    val d7 = Tile(TileId.D7,TileColor.DARK)
    val d8 = Tile(TileId.D8,TileColor.LIGHT)
    val d9 = Tile(TileId.D9,TileColor.LIGHT)
    val d10 = Tile(TileId.D10,TileColor.DARK)
    val d11 = Tile(TileId.D11,TileColor.DARK)

    val e1 = Tile(TileId.E1,TileColor.DARK)
    val e2 = Tile(TileId.E2,TileColor.MID)
    val e3 = Tile(TileId.E3,TileColor.LIGHT)
    val e4 = Tile(TileId.E4,TileColor.DARK)
    val e5 = Tile(TileId.E5,TileColor.LIGHT)
    val e6 = Tile(TileId.E6,TileColor.MID)
    val e7 = Tile(TileId.E7,TileColor.DARK)
    val e8 = Tile(TileId.E8,TileColor.LIGHT)
    val e9 = Tile(TileId.E9,TileColor.LIGHT)
    val e10 = Tile(TileId.E10,TileColor.DARK)
    val e11 = Tile(TileId.E11,TileColor.DARK)
    val e12 = Tile(TileId.E12,TileColor.MID)

    val f1 = Tile(TileId.F1,TileColor.DARK)
    val f2 = Tile(TileId.F2,TileColor.MID)
    val f3 = Tile(TileId.F3,TileColor.LIGHT)
    val f4 = Tile(TileId.F4,TileColor.DARK)
    val f5 = Tile(TileId.F5,TileColor.LIGHT)
    val f6 = Tile(TileId.F6,TileColor.MID)
    val f7 = Tile(TileId.F7,TileColor.DARK)
    val f8 = Tile(TileId.F8,TileColor.LIGHT)
    val f9 = Tile(TileId.F9,TileColor.LIGHT)
    val f10 = Tile(TileId.F10,TileColor.DARK)
    val f11 = Tile(TileId.F11,TileColor.DARK)

    val g1 = Tile(TileId.G1,TileColor.DARK)
    val g2 = Tile(TileId.G2,TileColor.MID)
    val g3 = Tile(TileId.G3,TileColor.LIGHT)
    val g4 = Tile(TileId.G4,TileColor.DARK)
    val g5 = Tile(TileId.G5,TileColor.LIGHT)
    val g6 = Tile(TileId.G6,TileColor.MID)
    val g7 = Tile(TileId.G7,TileColor.DARK)
    val g8 = Tile(TileId.G8,TileColor.LIGHT)
    val g9 = Tile(TileId.G9,TileColor.LIGHT)
    val g10 = Tile(TileId.G10,TileColor.DARK)

    val h1 = Tile(TileId.H1,TileColor.DARK)
    val h2 = Tile(TileId.H2,TileColor.MID)
    val h3 = Tile(TileId.H3,TileColor.LIGHT)
    val h4 = Tile(TileId.H4,TileColor.DARK)
    val h5 = Tile(TileId.H5,TileColor.LIGHT)
    val h6 = Tile(TileId.H6,TileColor.MID)
    val h7 = Tile(TileId.H7,TileColor.DARK)
    val h8 = Tile(TileId.H8,TileColor.LIGHT)
    val h9 = Tile(TileId.H9,TileColor.LIGHT)

    val i1 = Tile(TileId.I1,TileColor.DARK)
    val i2 = Tile(TileId.I2,TileColor.MID)
    val i3 = Tile(TileId.I3,TileColor.LIGHT)
    val i4 = Tile(TileId.I4,TileColor.DARK)
    val i5 = Tile(TileId.I5,TileColor.LIGHT)
    val i6 = Tile(TileId.I6,TileColor.MID)
    val i7 = Tile(TileId.I7,TileColor.DARK)
    val i8 = Tile(TileId.I8,TileColor.LIGHT)

    init {
        a1.topTile = a2
        a1.upperRightTile = b2
        a1.underRightTile = b1

        a2.topTile = a3
        a2.upperRightTile = b3
        a2.underRightTile = b2
        a2.bottomTile = a1

        a3.topTile = a4
        a3.upperRightTile = b4
        a3.underRightTile = b3
        a3.bottomTile = a2

        a4.topTile = a5
        a4.upperRightTile = b5
        a4.underLeftTile = b4
        a4.bottomTile = a6

        a5.topTile = a6
        a5.upperRightTile = b6
        a5.underRightTile = b5
        a5.bottomTile = a4

        a6.topTile = a7
        a6.upperRightTile = b7
        a6.underRightTile = b6
        a6.bottomTile = a5

        a7.topTile = a8
        a7.upperRightTile = b8
        a7.underRightTile = b7
        a7.bottomTile = a6

        a8.upperRightTile = b9
        a8.underRightTile = b8
        a8.bottomTile = a7

        b1.topTile = b2
        b1.upperRightTile = c2
        b1.underRightTile = c1
        b1.upperLeftTile = a1

        b2.topTile = b3
        b2.upperRightTile = c3
        b2.underRightTile = c2
        b2.bottomTile = b1
        b2.underLeftTile = a1
        b2.upperLeftTile = a2

        b3.topTile = b4
        b3.upperRightTile = c4
        b3.underRightTile = c3
        b3.bottomTile = b2
        b3.underLeftTile = a2
        b3.upperLeftTile = a3

        b4.topTile = b5
        b4.upperRightTile = c5
        b4.underRightTile = c4
        b4.bottomTile = b3
        b4.underLeftTile = a3
        b4.upperLeftTile = a4

        b5.topTile = b6
        b5.upperRightTile = c6
        b5.underRightTile = c5
        b5.bottomTile = b4
        b5.underLeftTile = a4
        b5.upperLeftTile = a5

        b6.topTile = b7
        b6.upperRightTile = c7
        b6.underRightTile = c6
        b6.bottomTile = b5
        b6.underLeftTile = a5
        b6.upperLeftTile = a6

        b7.topTile = b8
        b7.upperRightTile = c8
        b7.underRightTile = c7
        b7.bottomTile = b6
        b7.underLeftTile = a6
        b7.upperLeftTile = a7

        b8.topTile = b9
        b8.upperRightTile = c9
        b8.underRightTile = c8
        b8.bottomTile = b7
        b8.underLeftTile = a7
        b8.upperLeftTile = a4

        b9.upperRightTile = c10
        b9.underRightTile = c9
        b9.bottomTile = b8
        b9.underLeftTile = a8

        c1.topTile = c2
        c1.upperRightTile = d2
        c1.underRightTile = d1
        c1.upperLeftTile = b1

        c2.topTile = c3
        c2.upperRightTile = d3
        c2.underRightTile = d2
        c2.bottomTile = c1
        c2.underLeftTile = b1
        c2.upperLeftTile = b2

        c3.topTile = c4
        c3.upperRightTile = d4
        c3.underRightTile = d3
        c3.bottomTile = c2
        c3.underLeftTile = b2
        c3.upperLeftTile = b3

        c4.topTile = c5
        c4.upperRightTile = d5
        c4.underRightTile = d4
        c4.bottomTile = c3
        c4.underLeftTile = b3
        c4.upperLeftTile = b4

        c5.topTile = c6
        c5.upperRightTile = d6
        c5.underRightTile = d5
        c5.bottomTile = c4
        c5.underLeftTile = b4
        c5.upperLeftTile = b5

        c6.topTile = c7
        c6.upperRightTile = d7
        c6.underRightTile = d6
        c6.bottomTile = c5
        c6.underLeftTile = b5
        c6.upperLeftTile = b6

        c7.topTile = c8
        c7.upperRightTile = d8
        c7.underRightTile = d7
        c7.bottomTile = c6
        c7.underLeftTile = b6
        c7.upperLeftTile = b7

        c8.topTile = c9
        c8.upperRightTile = d9
        c8.underRightTile = d8
        c8.bottomTile = c7
        c8.underLeftTile = b7
        c8.upperLeftTile = b8

        c9.topTile = c10
        c9.upperRightTile = d10
        c9.underRightTile = d9
        c9.bottomTile = c8
        c9.underLeftTile = b8
        c9.upperLeftTile = b9

        c10.upperRightTile = d11
        c10.underRightTile = d10
        c10.underLeftTile = c9
        c10.bottomTile = c9


    }
}