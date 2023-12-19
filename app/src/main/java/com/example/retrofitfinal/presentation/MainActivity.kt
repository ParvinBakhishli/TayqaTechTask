package com.example.retrofitfinal.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.retrofitfinal.databinding.ActivityMainBinding
import com.example.retrofitfinal.presentation.adapters.MainAdapter


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

        setupSwipeToRefresh()
        setupObservers()
    }

    private fun setupSwipeToRefresh() {
        binding.root.setOnRefreshListener {
            Toast.makeText(this, "Salam", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupObservers() {
    }

}