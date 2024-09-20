package com.company.tictactoebootcamp.ui.game

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.company.tictactoebootcamp.ScoreItem
import com.company.tictactoebootcamp.data.model.Board
import com.company.tictactoebootcamp.data.model.BoardState
import com.company.tictactoebootcamp.data.model.Cell
import com.company.tictactoebootcamp.databinding.FragmentGameBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class GameFragment : Fragment() {

    private var _binding: FragmentGameBinding? = null
    private val binding get() = _binding!!
    private val gameViewModel: GameViewModel by viewModels()
    private var playerName = ""
    private var gameFinished : Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        playerName = arguments?.getString("playerName").toString()
        bindClickEvents()
        binding.textPlayer.text = "$playerName:"

        // Observe the boardLiveData from ViewModel
        gameViewModel.boardLiveData.observe(viewLifecycleOwner) { board ->
            if (!gameFinished) {
                updateBoard(board)
            } else {
                gameFinished = false
            }
        }

        gameViewModel.scorePlayerLiveData.observe(viewLifecycleOwner) { scorePlayer ->
            // Update UI with the new score
            binding.textPlayer.text = "$playerName:$scorePlayer"
        }

        gameViewModel.scoreAILiveData.observe(viewLifecycleOwner) { scoreAI ->
            // Update UI with the new score
            binding.textAI.text = "AI: $scoreAI"
        }
    }

    private fun updateBoard(board: Board) {
        // Update UI based on the board state
        binding.square0.setImageResource(board.topLeft.res)
        binding.square1.setImageResource(board.topCenter.res)
        binding.square2.setImageResource(board.topRight.res)
        binding.square3.setImageResource(board.centerLeft.res)
        binding.square4.setImageResource(board.centerCenter.res)
        binding.square5.setImageResource(board.centerRight.res)
        binding.square6.setImageResource(board.bottomLeft.res)
        binding.square7.setImageResource(board.bottomCenter.res)
        binding.square8.setImageResource(board.bottomRight.res)

        when (board.boardState) {
            BoardState.AIWon -> {
                setupBoard(true)
                showWinningMessage("AI Won!")
                saveScore(false)
                gameViewModel.increaseAIScore()
                gameFinished = true
            }
            BoardState.PlayerWon -> {
                setupBoard(true)
                showWinningMessage("$playerName Won!")
                saveScore(true)
                gameViewModel.increasePlayerScore()
                gameFinished = true
            }
            BoardState.Draw -> {
                setupBoard(true)
                showWinningMessage("It's a Draw!")
                gameFinished = true
            }
            BoardState.Incomplete -> {
                setupBoard()
                hideWinningMessage()
            }
        }
    }

    private fun setupBoard(disable: Boolean = false) {
        val squares = listOf(
            binding.square0, binding.square1, binding.square2,
            binding.square3, binding.square4, binding.square5,
            binding.square6, binding.square7, binding.square8
        )
        squares.forEach { square ->
            square.isEnabled = !disable
            square.alpha = if (disable) 0.5f else 1f
        }
    }

    private fun bindClickEvents() {
        binding.square0.setOnClickListener { gameViewModel.boardClicked(Cell.TopLeft) }
        binding.square1.setOnClickListener { gameViewModel.boardClicked(Cell.TopCenter) }
        binding.square2.setOnClickListener { gameViewModel.boardClicked(Cell.TopRight) }
        binding.square3.setOnClickListener { gameViewModel.boardClicked(Cell.CenterLeft) }
        binding.square4.setOnClickListener { gameViewModel.boardClicked(Cell.CenterCenter) }
        binding.square5.setOnClickListener { gameViewModel.boardClicked(Cell.CenterRight) }
        binding.square6.setOnClickListener { gameViewModel.boardClicked(Cell.BottomLeft) }
        binding.square7.setOnClickListener { gameViewModel.boardClicked(Cell.BottomCenter) }
        binding.square8.setOnClickListener { gameViewModel.boardClicked(Cell.BottomRight) }
        binding.buttonReset.setOnClickListener {
            gameFinished = false
            gameViewModel.resetBoard()
        }
    }

    private fun showWinningMessage(message: String) {
        binding.textWinningMessage.visibility = View.VISIBLE
        binding.textWinningMessage.text = message
    }

    private fun hideWinningMessage() {
        binding.textWinningMessage.visibility = View.GONE
    }

    private fun saveScore(isSuccess: Boolean) {
        gameViewModel.addScores(
            listOf(
                ScoreItem.newBuilder()
                    .setName(playerName)
                    .setIsSuccessful(isSuccess)
                    .build()
            )
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}