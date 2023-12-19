package com.example.retrofitfinal

import com.example.retrofitfinal.api.ApiUtils
import com.example.retrofitfinal.model.network.CountryDto


/**
 * We do not want ViewModel to see the api directly.
 * why?
 *  - Because we might want to test it with fake data in future
 */
class MainRepository {

    private val apiService = ApiUtils.apiService


    /**
     * Gets all data available
     */
    private suspend fun getData(): List<CountryDto>? {
        val response = apiService.getAllData()

        return if (response.isSuccessful) {
            response.body()?.countryList
                ?.filterNotNull()
        } else {
            null
        }
    }

    /**
     * Gets countries with their id
     */
    suspend fun getCountries(): Map<Int, String> {
        val countries = getData() ?: return emptyMap()

        return countries.associateBy(
            keySelector = { it.countryId ?: -1 },
            valueTransform = { it.name ?: "-" }
        )
    }

}
