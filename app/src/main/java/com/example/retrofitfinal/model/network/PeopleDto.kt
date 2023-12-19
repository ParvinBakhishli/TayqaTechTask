package com.example.retrofitfinal.model.network


import com.google.gson.annotations.SerializedName

data class PeopleDto(
    @SerializedName("humanId")
    val humanId: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("surname")
    val surname: String?
)