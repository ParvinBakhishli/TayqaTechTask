package com.example.retrofitfinal.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

private const val BASE_URL = "http://89.147.202.166:1153/"


object ApiUtils {

    val apiService by lazy {
        getRetrofit().create<ApiService>()
    }


    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}
