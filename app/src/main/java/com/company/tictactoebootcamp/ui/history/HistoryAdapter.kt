package com.company.tictactoebootcamp.ui.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.company.tictactoebootcamp.R
import com.company.tictactoebootcamp.ScoreItem
import com.company.tictactoebootcamp.databinding.ItemHistoryBinding

class HistoryAdapter(private val onClick: (ScoreItem) -> Unit) :
    ListAdapter<ScoreItem, HistoryAdapter.HistoryHolder>(ScoreDiffCallback()) {
    class HistoryHolder(val binding: ItemHistoryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(scoreItem: ScoreItem) {
            if(scoreItem.isSuccessful) {
                binding.imageview.setImageResource(R.drawable.ic_x)
                binding.textView.text = scoreItem.name + " Won"
            } else {
                binding.imageview.setImageResource(R.drawable.ic_circle)
                binding.textView.text = "AI Won against " + scoreItem.name
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryHolder {
        val binding = ItemHistoryBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return HistoryHolder(binding)
    }

    override fun onBindViewHolder(holder: HistoryHolder, position: Int) {
        holder.bind(getItem(position))
    }
}