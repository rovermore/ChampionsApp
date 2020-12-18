package com.example.championsapp.db

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.championsapp.model.ChampionTeam
import javax.inject.Inject

class RoomDataSource @Inject constructor(context: Context): LocalDataSource {
    private val db = ChampionDatabase.getInstance(context)
    private val championDao = db.championDao

    override suspend fun saveChampionTeam(championTeam: ChampionTeam) {
        championDao.insertChampionTeam(championTeam)
    }

    override suspend fun getAllChampionTeams(): LiveData<List<ChampionTeam>> =
        championDao.getAll()


}