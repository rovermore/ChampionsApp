package com.example.championsapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.championsapp.model.ChampionTeam


@Database(entities = [ChampionTeam::class], version = 1, exportSchema = false)
@TypeConverters(ChampionConverter::class)
abstract class ChampionDatabase: RoomDatabase() {

    abstract val championDao: ChampionDao

    companion object {
        @Volatile
        private var INSTANCE: ChampionDatabase? = null
        fun getInstance(context: Context): ChampionDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ChampionDatabase::class.java,
                        "Champions"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }

}