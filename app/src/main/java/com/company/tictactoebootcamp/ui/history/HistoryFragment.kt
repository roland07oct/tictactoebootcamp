package com.company.tictactoebootcamp.ui.history

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.company.tictactoebootcamp.ScoreItems
import com.company.tictactoebootcamp.data.ScoreSerializer
import com.company.tictactoebootcamp.databinding.FragmentHistoryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HistoryFragment: Fragment() {
    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!

    val historyViewModel: HistoryViewModel by viewModels()

    private val DATA_STORE_FILE_NAME = "scoreItemsList.pb"

    private val Context.scoreItemsStore: DataStore<ScoreItems> by dataStore(
        fileName = DATA_STORE_FILE_NAME,
        serializer = ScoreSerializer,
    )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val scoresAdapter = HistoryAdapter{}
        binding.playedGamesList.apply {
            layoutManager = LinearLayoutManager(requireActivity())
            addItemDecoration(DividerItemDecoration(requireActivity(), DividerItemDecoration.VERTICAL))
            adapter = scoresAdapter
        }
        historyViewModel.scoreItems.observe(viewLifecycleOwner) {
            scoresAdapter.submitList(it.toMutableList())
        }
    }
}