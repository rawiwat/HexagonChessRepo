package com.example.hexagonalchess.presentation_layer.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hexagonalchess.data_layer.model.database.Database
import com.example.hexagonalchess.domain_layer.PieceColor
import com.example.hexagonalchess.domain_layer.PieceType
import com.example.hexagonalchess.domain_layer.TileDirections
import com.example.hexagonalchess.domain_layer.TileId
import com.example.hexagonalchess.data_layer.model.pieces.ChessPiece
import com.example.hexagonalchess.data_layer.model.tile.Tile
import com.example.hexagonalchess.domain_layer.getTileIndex
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ChessBoardViewModel(
    allTiles:List<Tile>,
    //private val database: Database
) : ViewModel() {
    private val _chessBoard = MutableStateFlow(allTiles)
    val chessBoard: StateFlow<List<Tile>> = _chessBoard

    //private val _whiteCaptured = MutableStateFlow(mutableListOf<ChessPiece>())
    //val whiteCaptured:StateFlow<List<ChessPiece>> = _whiteCaptured

    //private val _blackCaptured = MutableStateFlow(mutableListOf<ChessPiece>())
    //val blackCaptured:StateFlow<List<ChessPiece>> = _blackCaptured

    private var selectedTile:Tile? = null

    private fun findTile(id: TileId, direction: TileDirections): TileId? {
        val targetIndex = getTileIndex(id)
        val targetedTile = _chessBoard.value[targetIndex]
        return when(direction) {
                    TileDirections.TOP -> targetedTile.topTile
                    TileDirections.UPPER_RIGHT -> targetedTile.upperRightTile
                    TileDirections.UNDER_RIGHT -> targetedTile.underRightTile
                    TileDirections.BOTTOM -> targetedTile.bottomTile
                    TileDirections.UNDER_LEFT -> targetedTile.underLeftTile
                    TileDirections.UPPER_LEFT -> targetedTile.upperLeftTile
        }
    }

    fun onClickPieces(tile: Tile) {
        if (tile.chessPiece != null) {
            for (tiles in _chessBoard.value) {
                tiles.isAPossibleMove = false
            }
            selectedTile = tile
            when (tile.chessPiece!!.type) {
                PieceType.PAWN -> pawnMove(tile)
                else -> {  }
            }
        }
        updateBoard()
    }

    fun onClickTargeted(targetedTile: Tile) {
        for (tile in _chessBoard.value) {
            tile.isAPossibleMove = false
        }
        val targetedIndex = getTileIndex(targetedTile.id)
        _chessBoard.value[targetedIndex].chessPiece = selectedTile!!.chessPiece
        val selectedTileIndex = getTileIndex(selectedTile!!.id)
        _chessBoard.value[selectedTileIndex].chessPiece = null
        /*for (tile in _chessBoard.value) {
            if (tile.id == targetedTile.id) {
                tile.chessPiece = selectedTile!!.chessPiece
                break
            }
        }
        for (tiles in _chessBoard.value) {
            if(tiles.id == selectedTile!!.id) {
                tiles.chessPiece = null
                break
            }
        }*/
        updateBoard()
        //selectedTile?.let { database.movePieces(it,targetedTile) }
    }

    private fun containPiece(tileId: TileId?): Boolean {
        val targetIndex = tileId?.let { getTileIndex(it) }
        targetIndex?.let {
            if (_chessBoard.value[targetIndex].chessPiece != null ) {
                return true
            }
        }
        /*for (tile in _chessBoard.value) {
            if (tileId == tile.id && tile.chessPiece != null) {
                return true
            }
        }*/
        return false
    }

    private fun pawnMove(selectedTile: Tile) {
        if (selectedTile.chessPiece!!.color == PieceColor.WHITE) {
            val result = mutableListOf<TileId?>()
            val forward1 = findTile(selectedTile.id, TileDirections.TOP)
            val forward2 = findTile(forward1!!, TileDirections.TOP)

            if (!containPiece(forward1)) {
                result.add(forward1)
            }

            if(!containPiece(forward1) && !containPiece(forward2)) {
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
            val attack1Index = attack1?.let { getTileIndex(it) }
            attack1Index?.let {
                if (_chessBoard.value[it].chessPiece != null && _chessBoard.value[it].chessPiece!!.color == PieceColor.BLACK) {
                    result.add(attack1)
                }
            }

            val attack2Index = attack2?.let { getTileIndex(it) }
            attack2Index?.let {
                if (_chessBoard.value[it].chessPiece != null && _chessBoard.value[it].chessPiece!!.color == PieceColor.BLACK) {
                    result.add(attack2)
                }
            }

            /*for (tiles in _chessBoard.value) {
                if (tiles.id == attack2 && tiles.chessPiece != null && tiles.chessPiece!!.color == PieceColor.BLACK) {
                    result.add(attack2)
                }
            }*/
            for (tileId in result) {
                val index = tileId?.let { getTileIndex(it) }
                index?.let {
                    _chessBoard.value[it].isAPossibleMove = true
                }
            }
            /*for (tiles in _chessBoard.value) {
                if (result.contains(tiles.id)) {
                    tiles.isAPossibleMove = true
                }
            }*/
        } else {
            val result = mutableListOf<TileId?>()
            val forward1 = findTile(selectedTile.id, TileDirections.BOTTOM)
            val forward2 = findTile(forward1!!, TileDirections.BOTTOM)

            if (!containPiece(forward1)) {
                result.add(forward1)
            }

            if(!containPiece(forward1) && !containPiece(forward2)) {
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
            val attack1Index = attack1?.let { getTileIndex(it) }
            attack1Index?.let {
                if (_chessBoard.value[it].chessPiece != null && _chessBoard.value[it].chessPiece!!.color == PieceColor.WHITE) {
                    result.add(attack1)
                }
            }

            val attack2Index = attack2?.let { getTileIndex(it) }
            attack2Index?.let {
                if (_chessBoard.value[it].chessPiece != null && _chessBoard.value[it].chessPiece!!.color == PieceColor.WHITE) {
                    result.add(attack2)
                }
            }

            /*for (tiles in _chessBoard.value) {
                if (tiles.id == attack2 && tiles.chessPiece != null && tiles.chessPiece!!.color == PieceColor.BLACK) {
                    result.add(attack2)
                }
            }*/
            for (tileId in result) {
                val index = tileId?.let { getTileIndex(it) }
                index?.let {
                    _chessBoard.value[it].isAPossibleMove = true
                }
            }
        }
    }

    private fun updateBoard() {
        val updatedChessBoard = _chessBoard.value.map { tile ->
            tile.copy()
        }
        _chessBoard.value = updatedChessBoard
    }
}


