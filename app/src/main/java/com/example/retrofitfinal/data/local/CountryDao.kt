package com.example.retrofitfinal.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.retrofitfinal.model.local.CountryEntity
import com.example.retrofitfinal.presentation.adapters.FilterAdapter
import kotlinx.coroutines.flow.Flow

@Dao
interface CountryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(countries: List<CountryEntity>)

    @Query("SELECT * from countries")
    suspend fun getCountries(): List<CountryEntity>?

    @Query("SELECT name from countries")
    fun getCountryNames(): List<FilterAdapter.FilterItem>



}
