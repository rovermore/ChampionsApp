package com.example.championsapp.client

import com.example.championsapp.model.Champion
import javax.inject.Inject

class RetrofitApiClientImplementation
    @Inject constructor(private val retrofitApiClient: RetrofitApiClient):
    RetrofitApiClient {

    override suspend fun getChampionResponse(): List<Champion> {
        return retrofitApiClient.getChampionResponse()
    }

}