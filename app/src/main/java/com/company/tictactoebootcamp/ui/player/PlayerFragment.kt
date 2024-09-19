package com.company.tictactoebootcamp.ui.player

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.company.tictactoebootcamp.R
import com.company.tictactoebootcamp.databinding.FragmentPlayersBinding

class PlayerFragment : Fragment() {
    private var _binding: FragmentPlayersBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _binding = FragmentPlayersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle = Bundle()

        binding.saveButton.setOnClickListener {
            bundle.putString("playerName", binding.playerName.text.toString())
            findNavController().navigate(R.id.action_playerFragment_to_gameFragment, bundle)
        }
    }
}