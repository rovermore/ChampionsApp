package com.example.championsapp.injection

import android.content.Context
import com.example.championsapp.ChampionsApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val app: ChampionsApp) {

    @Provides
    @Singleton
    fun context(): Context = app.applicationContext

}