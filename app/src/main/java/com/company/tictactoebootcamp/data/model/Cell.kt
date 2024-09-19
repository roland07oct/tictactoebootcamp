package com.company.tictactoebootcamp.data.model

import kotlin.random.Random

sealed class Cell {
    object TopLeft : Cell()
    object TopCenter : Cell()
    object TopRight : Cell()
    object CenterLeft : Cell()
    object CenterCenter : Cell()
    object CenterRight : Cell()
    object BottomLeft : Cell()
    object BottomCenter : Cell()
    object BottomRight : Cell()

    fun getRandomCell(): Cell {
        return when (Random.nextInt(9)) {
            0 -> TopLeft
            1 -> TopCenter
            2 -> TopRight
            3 -> CenterLeft
            4 -> CenterCenter
            5 -> CenterRight
            6 -> BottomLeft
            7 -> BottomCenter
            8 -> BottomRight
            else -> throw IllegalStateException("Unexpected value")
        }
    }
}