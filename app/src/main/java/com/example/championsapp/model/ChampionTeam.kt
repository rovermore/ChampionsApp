package com.example.championsapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.championsapp.db.ChampionConverter

@Entity
data class ChampionTeam (
    @PrimaryKey(autoGenerate = true) val id: Int?,
    @TypeConverters(ChampionConverter::class)
    val championList: List<Champion>,
    val teamName: String
) {
}