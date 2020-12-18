package com.example.championsapp.db

import androidx.lifecycle.LiveData
import com.example.championsapp.model.ChampionTeam

interface LocalDataSource {

    suspend fun saveChampionTeam(championTeam: ChampionTeam)
    suspend fun getAllChampionTeams(): LiveData<List<ChampionTeam>>
}