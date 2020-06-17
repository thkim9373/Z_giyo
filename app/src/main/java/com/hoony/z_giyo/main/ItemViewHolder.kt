package com.hoony.z_giyo.main

import androidx.recyclerview.widget.RecyclerView
import com.hoony.z_giyo.databinding.ItemSingleTextBinding

class ItemViewHolder(private val binding: ItemSingleTextBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun setListener(listener: ListAdapter.OnItemClickListener) {
        binding.clContainer.setOnClickListener {
            if (adapterPosition != RecyclerView.NO_POSITION) {
                listener.onItemClick(adapterPosition)
            }
        }
        binding.clContainer.setOnLongClickListener {
            if (adapterPosition != RecyclerView.NO_POSITION) {
                listener.onItemLongClick(adapterPosition)
            }
            true
        }
    }

    fun setText(text: String) {
        binding.tvTitle.text = text
    }
}