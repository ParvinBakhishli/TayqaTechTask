package com.example.retrofitfinal

import android.util.Log
import com.example.retrofitfinal.data.network.ApiUtils
import com.example.retrofitfinal.model.network.CountryDto


class MainRepository {

    private val apiService = ApiUtils.apiService


    suspend fun getNetworkData(): List<CountryDto>? {
        val response = apiService.getAllData()

        return if (response.isSuccessful) {
            response.body()?.countryList
        } else {
            null
        }
    }

    /**
     * Gets countries with their id
     */
    suspend fun getCountries(): Map<Int, String> {
        val countries = getNetworkData() ?: return emptyMap()

        return countries.associateBy(
            keySelector = { it.countryId ?: -1 },
            valueTransform = { it.name ?: "-" }
        )
    }

}
