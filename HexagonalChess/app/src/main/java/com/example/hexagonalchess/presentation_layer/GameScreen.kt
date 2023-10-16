package com.example.hexagonalchess.presentation_layer

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hexagonalchess.data_layer.model.tile.A.A1
import com.example.hexagonalchess.data_layer.model.tile.A.A2
import com.example.hexagonalchess.data_layer.model.tile.A.A3
import com.example.hexagonalchess.data_layer.model.tile.A.A4
import com.example.hexagonalchess.data_layer.model.tile.A.A5
import com.example.hexagonalchess.data_layer.model.tile.A.A6
import com.example.hexagonalchess.data_layer.model.tile.A.A7
import com.example.hexagonalchess.data_layer.model.tile.A.A8
import com.example.hexagonalchess.data_layer.model.tile.B.B1
import com.example.hexagonalchess.data_layer.model.tile.B.B2
import com.example.hexagonalchess.data_layer.model.tile.B.B3
import com.example.hexagonalchess.data_layer.model.tile.B.B4
import com.example.hexagonalchess.data_layer.model.tile.B.B5
import com.example.hexagonalchess.data_layer.model.tile.B.B6
import com.example.hexagonalchess.data_layer.model.tile.B.B7
import com.example.hexagonalchess.data_layer.model.tile.B.B8
import com.example.hexagonalchess.data_layer.model.tile.B.B9
import com.example.hexagonalchess.data_layer.model.tile.C.C1
import com.example.hexagonalchess.data_layer.model.tile.C.C10
import com.example.hexagonalchess.data_layer.model.tile.C.C2
import com.example.hexagonalchess.data_layer.model.tile.C.C3
import com.example.hexagonalchess.data_layer.model.tile.C.C4
import com.example.hexagonalchess.data_layer.model.tile.C.C5
import com.example.hexagonalchess.data_layer.model.tile.C.C6
import com.example.hexagonalchess.data_layer.model.tile.C.C7
import com.example.hexagonalchess.data_layer.model.tile.C.C8
import com.example.hexagonalchess.data_layer.model.tile.C.C9
import com.example.hexagonalchess.data_layer.model.tile.D.D1
import com.example.hexagonalchess.data_layer.model.tile.D.D10
import com.example.hexagonalchess.data_layer.model.tile.D.D11
import com.example.hexagonalchess.data_layer.model.tile.D.D2
import com.example.hexagonalchess.data_layer.model.tile.D.D3
import com.example.hexagonalchess.data_layer.model.tile.D.D4
import com.example.hexagonalchess.data_layer.model.tile.D.D5
import com.example.hexagonalchess.data_layer.model.tile.D.D6
import com.example.hexagonalchess.data_layer.model.tile.D.D7
import com.example.hexagonalchess.data_layer.model.tile.D.D8
import com.example.hexagonalchess.data_layer.model.tile.D.D9
import com.example.hexagonalchess.data_layer.model.tile.E.E1
import com.example.hexagonalchess.data_layer.model.tile.E.E10
import com.example.hexagonalchess.data_layer.model.tile.E.E11
import com.example.hexagonalchess.data_layer.model.tile.E.E12
import com.example.hexagonalchess.data_layer.model.tile.E.E2
import com.example.hexagonalchess.data_layer.model.tile.E.E3
import com.example.hexagonalchess.data_layer.model.tile.E.E4
import com.example.hexagonalchess.data_layer.model.tile.E.E5
import com.example.hexagonalchess.data_layer.model.tile.E.E6
import com.example.hexagonalchess.data_layer.model.tile.E.E7
import com.example.hexagonalchess.data_layer.model.tile.E.E8
import com.example.hexagonalchess.data_layer.model.tile.E.E9
import com.example.hexagonalchess.data_layer.model.tile.F.F1
import com.example.hexagonalchess.data_layer.model.tile.F.F10
import com.example.hexagonalchess.data_layer.model.tile.F.F11
import com.example.hexagonalchess.data_layer.model.tile.F.F2
import com.example.hexagonalchess.data_layer.model.tile.F.F3
import com.example.hexagonalchess.data_layer.model.tile.F.F4
import com.example.hexagonalchess.data_layer.model.tile.F.F5
import com.example.hexagonalchess.data_layer.model.tile.F.F6
import com.example.hexagonalchess.data_layer.model.tile.F.F7
import com.example.hexagonalchess.data_layer.model.tile.F.F8
import com.example.hexagonalchess.data_layer.model.tile.F.F9
import com.example.hexagonalchess.data_layer.model.tile.G.G1
import com.example.hexagonalchess.data_layer.model.tile.G.G10
import com.example.hexagonalchess.data_layer.model.tile.G.G2
import com.example.hexagonalchess.data_layer.model.tile.G.G3
import com.example.hexagonalchess.data_layer.model.tile.G.G4
import com.example.hexagonalchess.data_layer.model.tile.G.G5
import com.example.hexagonalchess.data_layer.model.tile.G.G6
import com.example.hexagonalchess.data_layer.model.tile.G.G7
import com.example.hexagonalchess.data_layer.model.tile.G.G8
import com.example.hexagonalchess.data_layer.model.tile.G.G9
import com.example.hexagonalchess.data_layer.model.tile.H.H1
import com.example.hexagonalchess.data_layer.model.tile.H.H2
import com.example.hexagonalchess.data_layer.model.tile.H.H3
import com.example.hexagonalchess.data_layer.model.tile.H.H4
import com.example.hexagonalchess.data_layer.model.tile.H.H5
import com.example.hexagonalchess.data_layer.model.tile.H.H6
import com.example.hexagonalchess.data_layer.model.tile.H.H7
import com.example.hexagonalchess.data_layer.model.tile.H.H8
import com.example.hexagonalchess.data_layer.model.tile.H.H9
import com.example.hexagonalchess.data_layer.model.tile.I.I1
import com.example.hexagonalchess.data_layer.model.tile.I.I2
import com.example.hexagonalchess.data_layer.model.tile.I.I3
import com.example.hexagonalchess.data_layer.model.tile.I.I4
import com.example.hexagonalchess.data_layer.model.tile.I.I5
import com.example.hexagonalchess.data_layer.model.tile.I.I6
import com.example.hexagonalchess.data_layer.model.tile.I.I7
import com.example.hexagonalchess.data_layer.model.tile.I.I8
import com.example.hexagonalchess.data_layer.model.tile.Tile
import com.example.hexagonalchess.domain_layer.getTileImage

@Composable
fun GameScreen() {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        val columnA = listOf(
            A1(), A2(), A3(), A4(),
            A5(), A6(), A7(), A8()
        )

        val columnB = listOf(
            B1(), B2(), B3(), B4(), B5(),
            B6(), B7(), B8(), B9()
        )

        val columnC = listOf(
            C1(), C2(), C3(), C4(), C5(),
            C6(), C7(), C8(), C9(), C10()
        )

        val columnD = listOf(
            D1(), D2(), D3(), D4(), D5(), D6(),
            D7(), D8(), D9(), D10(), D11()
        )

        val columnE = listOf(
            E1(), E2(), E3(), E4(), E5(), E6(),
            E7(), E8(), E9(), E10(), E11(), E12()
        )

        val columnF = listOf(
            F1(), F2(), F3(), F4(), F5(),
            F6(), F7(), F8(), F9(),F10(),F11()
        )

        val columnG = listOf(
            G1(), G2(), G3(), G4(), G5(),
            G6(), G7(), G8(), G9(), G10()
        )

        val columnH = listOf(
            H1(), H2(), H3(), H4(), H5(), H6(),
            H7(), H8(), H9()
        )

        val columnI = listOf(
            I1(), I2(), I3(), I4(),
            I5(), I6(), I7(), I8()
        )

        Row(
            horizontalArrangement = Arrangement.Center
        ) {

            LazyColumn {
                items(
                    columnA,
                    key = { it.id }
                ) {
                    TileUI(tile = it)
                }
            }

            LazyColumn {
                items(
                    columnB,
                    key = { it.id }
                ) {
                    TileUI(tile = it)
                }
            }

            LazyColumn {
                items(
                    columnC,
                    key = { it.id }
                ) {
                    TileUI(tile = it)
                }
            }

            LazyColumn {
                items(
                    columnD,
                    key = { it.id }
                ) {
                    TileUI(tile = it)
                }
            }

            LazyColumn {
                items(
                    columnE,
                    key = { it.id }
                ) {
                    TileUI(tile = it)
                }
            }

            LazyColumn {
                items(
                    columnF,
                    key = { it.id }
                ) {
                    TileUI(tile = it)
                }
            }

            LazyColumn {
                items(
                    columnG,
                    key = { it.id }
                ) {
                    TileUI(tile = it)
                }
            }

            LazyColumn {
                items(
                    columnH,
                    key = { it.id }
                ) {
                    TileUI(tile = it)
                }
            }

            LazyColumn {
                items(
                    columnI,
                    key = { it.id }
                ) {
                    TileUI(tile = it)
                }
            }
        }
    }
}

@Composable
fun TileUI(
    tile: Tile
) {
    Box(modifier = Modifier.wrapContentSize())
    Image(
        painter = painterResource(id = getTileImage(tile.color)),
        contentDescription = null,
        modifier = Modifier
            .size(width = 58.5.dp, height = 50.6.dp)
            .clickable { }
    )
}

@Preview
@Composable
fun GameScreenPreview() {
    GameScreen()
}