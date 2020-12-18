package com.example.championsapp

import android.app.Application
import com.example.championsapp.injection.*

class ChampionsApp: Application() {

    companion object {
        lateinit var mDaggerAppComponent: AppComponent
        fun daggerAppComponent():AppComponent = mDaggerAppComponent
    }


    override fun onCreate() {
        super.onCreate()
        mDaggerAppComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .apiModule(ApiModule())
            .repositoryModule(RepositoryModule())
            .useCaseModule(UseCaseModule())
            .networkConnectionModule(NetworkConnectionModule())
            .build()

    }
}