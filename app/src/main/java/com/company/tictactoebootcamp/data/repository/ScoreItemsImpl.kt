package com.company.tictactoebootcamp.data.repository

import androidx.datastore.core.DataStore
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import com.company.tictactoebootcamp.ScoreItem
import com.company.tictactoebootcamp.ScoreItems
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import java.io.IOException
import javax.inject.Inject

class ScoreItemsImpl @Inject constructor(
    private val scoreItemsDataStore: DataStore<ScoreItems>
) : ScoreItemsRepository {

    override suspend fun getScoreItems(): LiveData<List<ScoreItem>> {
        return scoreItemsDataStore.data.asLiveData().switchMap {
            MutableLiveData(it.scoresList)
        }
    }

    override suspend fun addScoreItems(scoreList: List<ScoreItem>) {
        scoreItemsDataStore.updateData { scoreItems: ScoreItems ->
            scoreItems.toBuilder().addAllScores(scoreList).build()
        }
    }

}