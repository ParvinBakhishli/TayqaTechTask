package com.example.retrofitfinal.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitfinal.databinding.ItemFilterBinding
import com.example.retrofitfinal.model.presentation.Country


class FilterAdapter : ListAdapter<FilterAdapter.FilterItem, FilterAdapter.FilterViewHolder>(callback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemFilterBinding.inflate(layoutInflater)

        return FilterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FilterViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class FilterViewHolder(private val binding: ItemFilterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: FilterItem) {
            binding.checkbox.text = item.title
            binding.checkbox.isChecked = item.isSelected

            binding.checkbox.setOnClickListener {
                binding.checkbox.isChecked = !binding.checkbox.isChecked
            }
        }

    }


    data class FilterItem(
        val id: Int = 0,
        val title: String,
        val isSelected: Boolean = false
    )

}


private val callback = object : DiffUtil.ItemCallback<FilterAdapter.FilterItem>() {
    override fun areItemsTheSame(oldItem: FilterAdapter.FilterItem, newItem: FilterAdapter.FilterItem): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: FilterAdapter.FilterItem, newItem: FilterAdapter.FilterItem): Boolean {
        return oldItem.id == newItem.id
    }
}
