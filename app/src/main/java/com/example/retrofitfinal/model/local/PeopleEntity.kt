package com.example.retrofitfinal.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PeopleEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val fullName: String
)