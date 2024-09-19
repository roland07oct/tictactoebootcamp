package com.company.tictactoebootcamp.data.model


sealed class BoardState {
    object Incomplete : BoardState()
    object PlayerWon : BoardState()
    object AIWon : BoardState()
    object Draw : BoardState()
}