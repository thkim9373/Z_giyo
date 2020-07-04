package com.hoony.z_giyo.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.hoony.z_giyo.R
import com.hoony.z_giyo.db.entity.Item

class RecyclerViewAdapter(
    private val listener: OnItemClickListener
) :
    ListAdapter<Item, ItemViewHolder>(
        object : DiffUtil.ItemCallback<Item>() {
            override fun areItemsTheSame(oldItem: Item, newItem: Item):
                    Boolean = oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Item, newItem: Item):
                    Boolean = oldItem == newItem
        }
    ) {

    interface OnItemClickListener {
        fun onItemClick(position: Int)
        fun onItemLongClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_single_text,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return when (val count = super.getItemCount()) {
            0 -> 1
            else -> count
        }
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.setListener(listener)
        holder.setText(getItem(position).text)
    }
}