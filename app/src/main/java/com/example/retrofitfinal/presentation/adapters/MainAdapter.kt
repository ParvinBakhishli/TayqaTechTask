package com.example.retrofitfinal.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitfinal.databinding.ItemMainBinding
import com.example.retrofitfinal.model.presentation.Country

class MainAdapter : ListAdapter<Country, MainAdapter.MainViewHolder>(callback) {

    class MainViewHolder(private val binding: ItemMainBinding) :
        RecyclerView.ViewHolder(binding.root){
            fun bind(country: Country){
                binding.textView.text = country.name
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemMainBinding.inflate(layoutInflater)

        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(getItem(position))
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