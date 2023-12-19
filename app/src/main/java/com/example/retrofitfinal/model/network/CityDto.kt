package com.example.retrofitfinal.model.network


import com.google.gson.annotations.SerializedName

data class CityDto(
    @SerializedName("cityId")
    val cityId: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("peopleList")
    val peopleList: List<PeopleDto?>?
)