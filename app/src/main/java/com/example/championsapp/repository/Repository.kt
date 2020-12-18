package com.example.championsapp.repository

import androidx.lifecycle.LiveData
import com.example.championsapp.model.Champion
import com.example.championsapp.model.ChampionTeam

interface Repository {

    suspend fun getChampionResponse(): List<Champion>

    suspend fun getAllChampionTeam(): LiveData<List<ChampionTeam>>

    suspend fun saveChampionTeam(championTeam: ChampionTeam)
}