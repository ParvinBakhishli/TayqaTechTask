package com.example.retrofitfinal.model.presentation


data class City(
    val id: Int,
    val name: String,
    val people: List<People>
)