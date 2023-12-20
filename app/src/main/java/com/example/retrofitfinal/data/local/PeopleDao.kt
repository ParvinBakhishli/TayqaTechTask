package com.example.retrofitfinal.data.local

import androidx.room.Dao
import androidx.room.Query
import com.example.retrofitfinal.presentation.adapters.FilterAdapter
import retrofit2.http.GET

@Dao
interface PeopleDao {

    @Query("SELECT fullName from PeopleEntity")
    fun getPeaopleNames(): List<FilterAdapter.FilterItem>
}