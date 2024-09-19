package com.company.tictactoebootcamp.domain.logic

import com.company.tictactoebootcamp.data.model.Board
import com.company.tictactoebootcamp.data.model.Cell
import com.company.tictactoebootcamp.data.model.Cell.BottomCenter.getRandomCell
import com.company.tictactoebootcamp.data.model.CellState

class GameManager(private val board: Board) {

    // Update the board after a move
    fun playerMove(cell: Cell, cellState: CellState): Boolean {
        return if (board.setCell(cell, cellState)) {
            true
        } else {
            false
        }
    }

    // Handle the AI's turn
    fun aiTurn() {
        val circleWinningCell = board.findNextWinningMove(CellState.Circle)
        val xWinningCell = board.findNextWinningMove(CellState.X)
        when {
            circleWinningCell != null -> board.setCell(circleWinningCell, CellState.Circle)
            xWinningCell != null -> board.setCell(xWinningCell, CellState.Circle)
            board.setCell(Cell.CenterCenter, CellState.Circle) -> Unit
            else -> do {
                val nextCell = getRandomCell()
                val placeSuccess = board.setCell(nextCell, CellState.Circle)
            } while (!placeSuccess)
        }
    }

    // Reset the board to the initial state
    fun resetBoard() {
        board.clearBoard()
    }

    // Get the current state of the board
    fun getBoard(): Board {
        return board
    }
}