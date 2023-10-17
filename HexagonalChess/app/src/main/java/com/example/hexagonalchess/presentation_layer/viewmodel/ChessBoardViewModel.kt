package com.example.hexagonalchess.presentation_layer.viewmodel

import androidx.lifecycle.ViewModel
import com.example.hexagonalchess.domain_layer.PieceColor
import com.example.hexagonalchess.domain_layer.PieceType
import com.example.hexagonalchess.domain_layer.TileDirections
import com.example.hexagonalchess.domain_layer.TileId
import com.example.hexagonalchess.data_layer.model.pieces.ChessPiece
import com.example.hexagonalchess.data_layer.model.tile.Tile
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ChessBoardViewModel(
  val allTiles:List<Tile>
) : ViewModel() {
    private val _chessBoard = MutableStateFlow(allTiles)

    val chessBoard:StateFlow<List<Tile>> = _chessBoard

    private val _whiteCaptured = MutableStateFlow(mutableListOf<ChessPiece>())
    val whiteCaptured:StateFlow<List<ChessPiece>> = _whiteCaptured

    private val _blackCaptured = MutableStateFlow(mutableListOf<ChessPiece>())
    val blackCaptured:StateFlow<List<ChessPiece>> = _blackCaptured

    fun findTile(id: TileId, direction: TileDirections): TileId? {
        var result: TileId? = null

        for (tile in _chessBoard.value) {
            if (tile.id == id) {
                result = when(direction) {
                    TileDirections.TOP -> tile.topTile
                    TileDirections.UPPER_RIGHT -> tile.upperRightTile
                    TileDirections.UNDER_RIGHT -> tile.underRightTile
                    TileDirections.BOTTOM -> tile.bottomTile
                    TileDirections.UNDER_LEFT -> tile.underLeftTile
                    TileDirections.UPPER_LEFT -> tile.upperLeftTile
                }
                break
            }
        }
        return result
    }

    fun onClickPieces(tile:Tile) {
        if (tile.chessPiece != null) {
            for (tiles in _chessBoard.value) {
                tiles.isSelected = false
                tiles.isAPossibleMove = false
            }
            tile.isSelected = true

            when (tile.chessPiece!!.type) {
                PieceType.PAWN -> pawnMove(tile)
                else -> {}
            }
        }
    }

    fun onClickTargeted(pieceMove:Tile,targetedTile: Tile) {
        if (targetedTile.chessPiece != null) {
            if (targetedTile.chessPiece!!.color == PieceColor.BLACK) {
                _blackCaptured.value.add(targetedTile.chessPiece!!)
            } else {
                _whiteCaptured.value.add(targetedTile.chessPiece!!)
            }
        }
        targetedTile.chessPiece = pieceMove.chessPiece
        pieceMove.chessPiece = null
    }

    private fun containPiece(tileId: TileId?): Boolean {
        for (tile in _chessBoard.value) {
            if (tileId == tile.id && tile.chessPiece != null) {
                return true
            }
        }
        return false
    }

    private fun pawnMove(selectedTile: Tile) {
        if (selectedTile.chessPiece!!.color == PieceColor.WHITE) {
            val result = mutableListOf<TileId?>()
            val forward1 = findTile(selectedTile.id, TileDirections.TOP)
            val forward2 = findTile(forward1!!, TileDirections.TOP)

            if (!containPiece(forward1)) {
                result.add(forward1)
                when(selectedTile.id) {
                    TileId.A1 -> result.add(forward2)
                    TileId.B2 -> result.add(forward2)
                    TileId.C3 -> result.add(forward2)
                    TileId.D4 -> result.add(forward2)
                    TileId.E5 -> result.add(forward2)
                    TileId.F4 -> result.add(forward2)
                    TileId.G3 -> result.add(forward2)
                    TileId.H2 -> result.add(forward2)
                    TileId.I1 -> result.add(forward2)
                    else -> { }
                }
            }

            val attack1 = findTile(selectedTile.id, TileDirections.UPPER_LEFT)
            val attack2 = findTile(selectedTile.id, TileDirections.UPPER_RIGHT)

            if (containPiece(attack1)) {
                result.add(attack1)
            }

            if (containPiece(attack2)) {
                result.add(attack2)
            }
            for (tiles in _chessBoard.value) {
                if (result.contains(tiles.id)) {
                    tiles.isAPossibleMove = true
                }
            }
        } else {
            val result = mutableListOf<TileId?>()
            val forward1 = findTile(selectedTile.id, TileDirections.BOTTOM)
            val forward2 = findTile(forward1!!, TileDirections.BOTTOM)

            if (!containPiece(forward1)) {
                result.add(forward1)
                when(selectedTile.id) {
                    TileId.A8 -> result.add(forward2)
                    TileId.B8 -> result.add(forward2)
                    TileId.C8 -> result.add(forward2)
                    TileId.D8 -> result.add(forward2)
                    TileId.E8 -> result.add(forward2)
                    TileId.F8 -> result.add(forward2)
                    TileId.G8 -> result.add(forward2)
                    TileId.H8 -> result.add(forward2)
                    TileId.I8 -> result.add(forward2)
                    else -> { }
                }
            }

            val attack1 = findTile(selectedTile.id, TileDirections.UNDER_LEFT)
            val attack2 = findTile(selectedTile.id, TileDirections.UNDER_RIGHT)

            if (containPiece(attack1)) {
                result.add(attack1)
            }

            if (containPiece(attack2)) {
                result.add(attack2)
            }
            for (tiles in _chessBoard.value) {
                if (result.contains(tiles.id)) {
                    tiles.isAPossibleMove = true
                }
            }
        }
    }
}


