package com.example.hexagonalchess.domain_layer

import com.example.hexagonalchess.data_layer.model.pieces.ChessPiece
import com.example.hexagonalchess.data_layer.model.tile.Tile
import com.example.hexagonalchess.domain_layer.piecemove.bishopMove
import com.example.hexagonalchess.domain_layer.piecemove.kingMove
import com.example.hexagonalchess.domain_layer.piecemove.knightMove
import com.example.hexagonalchess.domain_layer.piecemove.queenMove
import com.example.hexagonalchess.domain_layer.piecemove.rookMove

fun findTile(id: TileId, direction: TileDirections, board: List<Tile>): TileId? {
    val targetIndex = getTileIndexPovWhite(id)
    val targetedTile = board[targetIndex]
    return when(direction) {
        TileDirections.TOP -> targetedTile.topTile
        TileDirections.UPPER_RIGHT -> targetedTile.upperRightTile
        TileDirections.UNDER_RIGHT -> targetedTile.underRightTile
        TileDirections.BOTTOM -> targetedTile.bottomTile
        TileDirections.UNDER_LEFT -> targetedTile.underLeftTile
        TileDirections.UPPER_LEFT -> targetedTile.upperLeftTile
    }
}

fun containPiece(tileId: TileId?, board: List<Tile>): Boolean {
    val targetIndex = tileId?.let { getTileIndexPovWhite(it) }
    targetIndex?.let {
        if (board[targetIndex].chessPiece != null ) {
            return true
        }
    }
    return false
}

fun getAllTileInMultiDirection(selectedTile: Tile, directions: List<TileDirections>, board: List<Tile>):List<TileId?> {
    val result = mutableListOf<TileId?>()
    var firstTileId:TileId? = selectedTile.id
    for (direction in directions) {
        firstTileId = firstTileId?.let { findTile(it, direction, board) }
    }
    firstTileId?.let {
        var currentTile = board[getTileIndexPovWhite(it)]
        currentTile.chessPiece?.let { adjacentTileWithPiece ->
            if (adjacentTileWithPiece.color != selectedTile.chessPiece!!.color) {
                result.add(currentTile.id)
            }
            return result
        }
        result.add(currentTile.id)
        for (i in 1 until 12) {
            var nextTile:TileId? = currentTile.id
            for (direction in directions) {
                nextTile = nextTile?.let { nextTileId-> findTile(nextTileId, direction, board) }
            }
            nextTile?.let { nextTileId ->
                currentTile = board[getTileIndexPovWhite(nextTileId)]
                result.add(nextTile)
            }
            if (currentTile.chessPiece != null) {
                break
            }
        }
    }

    return result
}

fun checkKingUnavailableMove(pieceColor: PieceColor, board: List<Tile>):List<TileId?> {
    val result = mutableListOf<TileId?>()
    for (tile in board) {
        tile.chessPiece?.let {
            if (it.color == pieceColor) {
                result += when(it.type) {
                    PieceType.KNIGHT -> knightMove(tile, board)
                    PieceType.PAWN -> checkPawnAttack(tile, board)
                    PieceType.BISHOP -> bishopMove(tile, board)
                    PieceType.ROOK -> rookMove(tile, board)
                    PieceType.QUEEN -> queenMove(tile, board)
                    PieceType.KING ->
                        listOf(
                            findTile(tile.id,TileDirections.TOP, board),
                            findTile(tile.id,TileDirections.UPPER_RIGHT, board),
                            findTile(tile.id,TileDirections.UNDER_RIGHT, board),
                            findTile(tile.id,TileDirections.BOTTOM, board),
                            findTile(tile.id,TileDirections.UNDER_LEFT, board),
                            findTile(tile.id,TileDirections.UPPER_LEFT, board)
                        )
                }
            }
        }
    }
    return result
}

private fun checkPawnAttack(tile: Tile, board:List<Tile>):List<TileId?> {
    return if(tile.chessPiece!!.color == PieceColor.BLACK) {
        listOf(findTile(tile.id,TileDirections.UNDER_LEFT, board),findTile(tile.id,TileDirections.UNDER_RIGHT, board))
    } else {
        listOf(findTile(tile.id,TileDirections.UPPER_LEFT, board),findTile(tile.id,TileDirections.UPPER_RIGHT, board))
    }
}

fun getAllTileInDirection(selectedTile: Tile, direction: TileDirections, board: List<Tile>):List<TileId?> {
    val result = mutableListOf<TileId?>()
    val firstTileId = findTile(selectedTile.id,direction, board)
    firstTileId?.let {
        var currentTile = board[getTileIndexPovWhite(it)]
        currentTile.chessPiece?.let { adjacentTileWithPiece ->
            if (adjacentTileWithPiece.color != selectedTile.chessPiece!!.color) {
                result.add(currentTile.id)
            }
            return result
        }
        result.add(currentTile.id)
        for (i in 1 until 12) {
            val nextTile = findTile(currentTile.id,direction, board)
            nextTile?.let { nextTileId ->
                currentTile = board[getTileIndexPovWhite(nextTileId)]
                result.add(nextTile)
            }
            if (currentTile.chessPiece != null) {
                break
            }
        }
    }
    return result
}

fun wasKingAttacked(board: List<Tile>,kingColor: PieceColor): Boolean {
    for (tile in board) {
        tile.chessPiece?.let { currentPiece ->
            if (currentPiece.color != kingColor) {
                val possibleMove = when(currentPiece.type) {
                    PieceType.KNIGHT -> knightMove(tile, board)
                    PieceType.PAWN -> checkPawnAttack(tile, board)
                    PieceType.BISHOP -> bishopMove(tile, board)
                    PieceType.ROOK -> rookMove(tile, board)
                    PieceType.QUEEN -> queenMove(tile, board)
                    PieceType.KING -> kingMove(tile, board)
                }

                for (move in possibleMove) {
                    move?.let { moveId ->
                        board[getTileIndexPovWhite(moveId)].chessPiece?.let { targetablePiece ->
                            if (targetablePiece.type == PieceType.KING && targetablePiece.color == kingColor) {
                                return true
                            }
                        }
                    }
                }
            }
        }
    }
    return false
}

fun filterIllegalMove(
    startingTile: TileId,
    moves: List<TileId?>,
    kingColor: PieceColor,
    board: List<Tile>,
    movingPiece: ChessPiece
): List<TileId?> {
    val result = mutableListOf<TileId?>()
    val initMockBoard = mutableListOf<Tile>()
    for (tile in board) {
        initMockBoard.add(tile.copy())
    }
    var mockBoard = initMockBoard
    for (move in moves) {
        move?.let { moveId ->
            mockBoard[getTileIndexPovWhite(startingTile)].chessPiece = null
            mockBoard[getTileIndexPovWhite(moveId)].chessPiece = movingPiece
            if (wasKingAttacked(mockBoard, kingColor)) {
                result.add(move)
            }
        }
        mockBoard = initMockBoard
    }

    return result
}

fun filterSameColor(selectedTile: Tile,result: MutableList<TileId?>,board: List<Tile>) {
    val sameColorTiles = mutableListOf<TileId>()
    for (move in result) {
        move?.let {
            val moveToTile = board[getTileIndexPovWhite(move)]
            moveToTile.chessPiece?.let {
                if (it.color == selectedTile.chessPiece!!.color) {
                    sameColorTiles.add(move)
                }
            }
        }
    }
    result.removeAll(sameColorTiles)
}