package com.example.retrofitfinal.model.network

import com.google.gson.annotations.SerializedName


data class DataResponse(
    @SerializedName("countryList")
    val countryList: List<CountryDto>?
)
