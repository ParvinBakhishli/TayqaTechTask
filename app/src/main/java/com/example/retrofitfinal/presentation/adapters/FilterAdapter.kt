package com.example.retrofitfinal.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitfinal.databinding.ItemFilterBinding
import com.example.retrofitfinal.model.presentation.Country


class FilterAdapter : ListAdapter<Country, FilterAdapter.FilterViewHolder>(callback) {

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

        fun bind(country: Country) {
            binding.textView.text = country.name
        }

    }

}


private val callback = object : DiffUtil.ItemCallback<Country>() {
    override fun areItemsTheSame(oldItem: Country, newItem: Country): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Country, newItem: Country): Boolean {
        return oldItem.id == newItem.id
    }
}
