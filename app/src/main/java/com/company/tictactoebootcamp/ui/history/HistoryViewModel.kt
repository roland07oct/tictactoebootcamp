package com.company.tictactoebootcamp.ui.history


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.company.tictactoebootcamp.ScoreItem
import com.company.tictactoebootcamp.data.repository.ScoreItemsImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor (
    private val scoreItemsImpl: ScoreItemsImpl
) : ViewModel() {

    init {
        //Getting all recent locations
        getAllScores()
    }

    //Private variables
    private lateinit var _scoreItems: LiveData<List<ScoreItem>>

    //Live data variables
    val scoreItems: LiveData<List<ScoreItem>> = _scoreItems

    private fun getAllScores() = viewModelScope.launch {
        _scoreItems = scoreItemsImpl.getScoreItems()
    }
}
