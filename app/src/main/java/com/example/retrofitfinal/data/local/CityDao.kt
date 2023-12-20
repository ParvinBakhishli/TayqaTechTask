package com.example.retrofitfinal.data.local

import androidx.room.Dao
import androidx.room.Query
import com.example.retrofitfinal.model.local.CityEntity
import com.example.retrofitfinal.presentation.adapters.FilterAdapter

@Dao
interface CityDao {

    @Query("SELECT name from cities")
    fun getCities(): List<FilterAdapter.FilterItem>
}