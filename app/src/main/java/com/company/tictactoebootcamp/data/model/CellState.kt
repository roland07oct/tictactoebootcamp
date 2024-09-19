package com.company.tictactoebootcamp.data.model

import androidx.annotation.DrawableRes
import com.company.tictactoebootcamp.R

sealed class CellState(@DrawableRes val res: Int) {
    object Blank : CellState(R.drawable.ic_blank)
    object X : CellState(R.drawable.ic_x)
    object Circle : CellState(R.drawable.ic_circle)
}
