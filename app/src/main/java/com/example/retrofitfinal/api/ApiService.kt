package com.example.retrofitfinal.api

import com.example.retrofitfinal.model.network.DataResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("tayqa/tiger/api/development/test/TayqaTech/getdata")
    suspend fun getAllData(): Response<DataResponse>

}
