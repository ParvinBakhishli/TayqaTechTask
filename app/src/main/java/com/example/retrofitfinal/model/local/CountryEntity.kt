package com.example.retrofitfinal.model.local

import androidx.room.Entity

@Entity(tableName = "CountryEntity")
data class CountryEntity(
    val id: Int?,
    val name: String?
)