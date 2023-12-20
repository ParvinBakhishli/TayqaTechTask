package com.example.retrofitfinal.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cities")
data class CityEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val people: List<PeopleEntity>
)