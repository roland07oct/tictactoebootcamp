package com.company.tictactoebootcamp.data.model

data class Board(private val board: MutableMap<Cell, CellState> = mutableMapOf()) {

    val topLeft: CellState
        get() = board[Cell.TopLeft] ?: CellState.Blank
    val topCenter: CellState
        get() = board[Cell.TopCenter] ?: CellState.Blank
    val topRight: CellState
        get() = board[Cell.TopRight] ?: CellState.Blank
    val centerLeft: CellState
        get() = board[Cell.CenterLeft] ?: CellState.Blank
    val centerCenter: CellState
        get() = board[Cell.CenterCenter] ?: CellState.Blank
    val centerRight: CellState
        get() = board[Cell.CenterRight] ?: CellState.Blank
    val bottomLeft: CellState
        get() = board[Cell.BottomLeft] ?: CellState.Blank
    val bottomCenter: CellState
        get() = board[Cell.BottomCenter] ?: CellState.Blank
    val bottomRight: CellState
        get() = board[Cell.BottomRight] ?: CellState.Blank

    fun setCell(cell: Cell, state: CellState): Boolean {
        if (board.containsKey(cell)) {
            return false
        }
        board[cell] = state
        return true
    }

    fun clearBoard() {
        board.clear()
    }

    fun findNextWinningMove(state: CellState): Cell? = when {
        Cell.TopLeft wins state -> Cell.TopLeft
        Cell.TopCenter wins state -> Cell.TopCenter
        Cell.TopRight wins state -> Cell.TopRight
        Cell.CenterLeft wins state -> Cell.CenterLeft
        Cell.CenterCenter wins state -> Cell.CenterCenter
        Cell.CenterRight wins state -> Cell.CenterRight
        Cell.BottomLeft wins state -> Cell.BottomLeft
        Cell.BottomCenter wins state -> Cell.BottomCenter
        Cell.BottomRight wins state -> Cell.BottomRight
        else -> null
    }

    private infix fun Cell.wins(state: CellState): Boolean {
        if (board.containsKey(this)) {
            return false
        }
        board[this] = state
        val hasWon = stateWon(state)
        board.remove(this)
        return hasWon
    }

    val boardState: BoardState
        get() {
            return when {
                stateWon(CellState.X) -> BoardState.PlayerWon
                stateWon(CellState.Circle) -> BoardState.AIWon
                board.size < 9 -> BoardState.Incomplete
                else -> BoardState.Draw
            }
        }

    private fun stateWon(state: CellState): Boolean {
        fun testState(vararg cells: Cell) = cells.all { cell ->
            board[cell] == state
        }

        return testState(Cell.TopLeft, Cell.CenterLeft, Cell.BottomLeft) ||
                testState(Cell.TopCenter, Cell.CenterCenter, Cell.BottomCenter) ||
                testState(Cell.TopRight, Cell.CenterRight, Cell.BottomRight) ||
                testState(Cell.TopLeft, Cell.TopCenter, Cell.TopRight) ||
                testState(Cell.CenterLeft, Cell.CenterCenter, Cell.CenterRight) ||
                testState(Cell.BottomLeft, Cell.BottomCenter, Cell.BottomRight) ||
                testState(Cell.TopLeft, Cell.CenterCenter, Cell.BottomRight) ||
                testState(Cell.BottomLeft, Cell.CenterCenter, Cell.TopRight)
    }
}