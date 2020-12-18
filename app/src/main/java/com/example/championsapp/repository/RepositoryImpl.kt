package com.example.championsapp.repository

import androidx.lifecycle.LiveData
import com.example.championsapp.client.RetrofitApiClientImplementation
import com.example.championsapp.db.RoomDataSource
import com.example.championsapp.model.Champion
import com.example.championsapp.model.ChampionTeam
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val api: RetrofitApiClientImplementation,
                                         private val roomDataSource: RoomDataSource): Repository {

    override suspend fun getChampionResponse(): List<Champion> {
        return api.getChampionResponse()
    }

    override suspend fun getAllChampionTeam(): LiveData<List<ChampionTeam>> {
        return roomDataSource.getAllChampionTeams()
    }

    override suspend fun saveChampionTeam(championTeam: ChampionTeam) {
        roomDataSource.saveChampionTeam(championTeam)
    }
}