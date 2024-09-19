package com.company.tictactoebootcamp.ui.history

import androidx.recyclerview.widget.DiffUtil
import com.company.tictactoebootcamp.ScoreItem

class ScoreDiffCallback : DiffUtil.ItemCallback<ScoreItem>() {
    override fun areItemsTheSame(oldItem: ScoreItem, newItem: ScoreItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ScoreItem, newItem: ScoreItem): Boolean {
        if (oldItem is ScoreItem && newItem is ScoreItem) {
            return oldItem.id == newItem.id && oldItem.name == newItem.name
        }
        return false
    }
}