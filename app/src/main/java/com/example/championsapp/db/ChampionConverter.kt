package com.example.championsapp.db

import androidx.room.TypeConverter
import com.example.championsapp.model.Champion
import java.util.*
import kotlin.collections.ArrayList

class ChampionConverter {

    @TypeConverter
    fun storedStringToChampionList(value: String): List<Champion> {
        val stringList = (value.split(",").toTypedArray())
        val championList = mutableListOf<Champion>()
        for (string in stringList) {
            championList.add(Champion(string))
        }
        return championList
    }

    @TypeConverter
    fun championListToString(championList: List<Champion>): String {
        var string = ""

        for (champion in championList) {
            string += champion.image + ","
        }
        return string
    }
}