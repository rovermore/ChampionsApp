package com.example.championsapp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.championsapp.model.ChampionTeam

@Dao
interface ChampionDao {

    @Query("SELECT * FROM ChampionTeam")
    fun getAll(): LiveData<List<ChampionTeam>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertChampionTeam(championTeam: ChampionTeam)
}