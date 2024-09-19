package com.company.tictactoebootcamp.data.repository

import androidx.lifecycle.LiveData
import com.company.tictactoebootcamp.ScoreItem


interface ScoreItemsRepository {

    suspend fun getScoreItems(): LiveData<List<ScoreItem>>

    suspend fun addScoreItems(locations: List<ScoreItem>)
}