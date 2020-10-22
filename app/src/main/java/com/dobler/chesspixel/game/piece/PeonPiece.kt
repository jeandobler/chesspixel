package com.dobler.chesspixel.game.piece

import androidx.compose.foundation.*
import androidx.compose.runtime.Composable
import com.dobler.chesspixel.game.PieceColor

class PeonPiece(
    pieceColor: PieceColor,
    positionCol: Int,
    positionRow: Int
) : AbstractPieces(
    pieceColor, positionCol,
    positionRow
) {

    override val name = "P"

    var startCol: Int = 0

    init {
        startCol = positionCol
    }

    override fun verifyMovements(board: Array<Array<Pieces?>>) {
        movements = ArrayList()
        this.board = board
        addMovement(positionCol + pieceColor.direction, positionRow, false)
        if (startCol == positionCol) {
            addMovement(
                positionCol + pieceColor.direction + pieceColor.direction,
                positionRow,
                false
            )
        }
        verifyCapture()
    }

    fun verifyCapture() {
        captures = ArrayList()
        val positionColAux = positionCol + pieceColor.direction

        if (!inBoardLimit(positionColAux)) {
            return
        }

        if (inBoardLimit(positionRow - 1)
            && board[positionColAux][positionRow - 1] == pieceColor.oppositeColor()
        ) {
            captures[movements.size] = Pair(positionColAux, positionRow - 1)
        }

        if (inBoardLimit(positionRow + 1)
            && board[positionColAux][positionRow + 1] == pieceColor.oppositeColor()
        ) {
            captures[movements.size] = Pair(positionColAux, positionRow + 1)
        }

    }

    @Composable
    override fun image() {
        Text(text = "P", color = pieceColor.color)
    }


}
