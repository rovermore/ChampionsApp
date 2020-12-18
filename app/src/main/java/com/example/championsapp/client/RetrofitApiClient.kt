package com.example.championsapp.client

import com.example.championsapp.model.Champion
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitApiClient {

    @GET("ef0ec22f-c5a4-4316-a479-eea14078deae")
    suspend fun getChampionResponse()
            : List<Champion>

}