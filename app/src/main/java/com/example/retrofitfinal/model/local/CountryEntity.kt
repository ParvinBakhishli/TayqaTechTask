package com.example.retrofitfinal.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "countries")
data class CountryEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val cities: List<CityEntity>
)
