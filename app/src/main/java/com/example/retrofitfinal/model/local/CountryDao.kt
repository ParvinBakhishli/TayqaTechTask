package com.example.retrofitfinal.model.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CountryDao {

    @Insert
    suspend fun insert(countries: List<CountryEntity>)

    @Query("SELECT * from CountryEntity")
    suspend fun getCountries(): List<CountryEntity>?

}
