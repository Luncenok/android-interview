package com.goodylabs.android.interview.ui.characterlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.goodylabs.android.interview.R
import com.goodylabs.android.interview.data.models.Character
import com.goodylabs.android.interview.databinding.ListitemCharacterBinding

class CharacterListAdapter(val listener: CharacterListener) :
    ListAdapter<Character, CharacterListAdapter.ViewHolder>(CharacterDiffCallback()) {
    class ViewHolder(val binding: ListitemCharacterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.listitem_character
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val withDataBinding = DataBindingUtil.inflate<ListitemCharacterBinding>(
            LayoutInflater.from(parent.context),
            ViewHolder.LAYOUT,
            parent,
            false
        )
        return ViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.also {
            val character = getItem(position)
            it.character = character
            it.clickListener = listener
        }
    }

    class CharacterDiffCallback : DiffUtil.ItemCallback<Character>() {
        override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem == newItem
        }

    }
}

class CharacterListener(val clickListener: (observationId: Int) -> Unit) {
    fun onClick(character: Character) = clickListener(character.id)
}
