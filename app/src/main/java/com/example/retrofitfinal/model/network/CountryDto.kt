package com.example.retrofitfinal.model.network


import com.google.gson.annotations.SerializedName

data class CountryDto(
    @SerializedName("cityList")
    val cityList: List<CityDto?>?,
    @SerializedName("countryId")
    val countryId: Int?,
    @SerializedName("name")
    val name: String?
)