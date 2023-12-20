package com.example.retrofitfinal.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.retrofitfinal.databinding.ActivityMainBinding
import com.example.retrofitfinal.model.presentation.People
import com.example.retrofitfinal.presentation.adapters.MainAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel>()

    private lateinit var binding: ActivityMainBinding

    private val adapter by lazy {
        MainAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.adapter = adapter

        setupListeners()
        setupSwipeToRefresh()
        setupObservers()
    }

    private fun setupListeners() {
        binding.imageViewFilterCountry.setOnClickListener {
            showFilterBottomSheet(FilterType.Country)
        }

        binding.imageViewFilterCity.setOnClickListener {
            showFilterBottomSheet(FilterType.City)
        }
    }

    private fun setupSwipeToRefresh() {
        binding.root.setOnRefreshListener {
            viewModel.refresh()
            binding.root.isRefreshing = false
        }
    }

    private fun setupObservers() {
        lifecycleScope.launch {
            viewModel.peoples.collectLatest {people->
                if(people?.isEmpty()== true) {
                    Toast.makeText(this@MainActivity, "Data tapılmadı.", Toast.LENGTH_SHORT).show()
                }
                adapter.submitList(people)
            }
        }
    }

    private fun showFilterBottomSheet(type: FilterType) {
        FilterBottomSheetFragment.newInstance(type).show(supportFragmentManager, FilterBottomSheetFragment.TAG)
    }

}