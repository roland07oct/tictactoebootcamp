package com.company.tictactoebootcamp.ui.game

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.company.tictactoebootcamp.ScoreItem
import com.company.tictactoebootcamp.data.model.Board
import com.company.tictactoebootcamp.data.model.BoardState
import com.company.tictactoebootcamp.data.model.Cell
import com.company.tictactoebootcamp.data.model.CellState
import com.company.tictactoebootcamp.data.repository.ScoreItemsImpl
import com.company.tictactoebootcamp.domain.logic.GameManager
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.PublishSubject
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class GameViewModel @Inject constructor(
    private val scoreItemsImpl: ScoreItemsImpl
) : ViewModel() {

    private val mainBoard = Board()
    private val gameManager = GameManager(mainBoard)
    val board: BehaviorSubject<Board> = BehaviorSubject.createDefault(mainBoard)
    private val _scorePlayerLiveData = MutableLiveData(0)
    val scorePlayerLiveData: LiveData<Int> get() = _scorePlayerLiveData

    private val _scoreAILiveData = MutableLiveData(0)
    val scoreAILiveData: LiveData<Int> get() = _scoreAILiveData

    fun addScores(scoreItems: List<ScoreItem>) = viewModelScope.launch {
        scoreItemsImpl.addScoreItems(scoreItems)
    }

    private fun updateBoard() {
        board.onNext(gameManager.getBoard())
    }

    fun increasePlayerScore() {
        val currentScore = _scorePlayerLiveData.value ?: 0
        _scorePlayerLiveData.value = currentScore + 1
    }

    fun increaseAIScore() {
        val currentScore = _scoreAILiveData.value ?: 0
        _scoreAILiveData.value = currentScore + 1
    }

    fun boardClicked(cell: Cell) {
        if (gameManager.playerMove(cell, CellState.X)) {
            updateBoard()
            if (mainBoard.boardState == BoardState.Incomplete) {
                gameManager.aiTurn()
                updateBoard()
            }
        }
    }

    fun resetBoard() {
        gameManager.resetBoard()
        updateBoard()
    }
}