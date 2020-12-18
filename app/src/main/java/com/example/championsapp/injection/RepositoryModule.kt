package com.example.championsapp.injection

import com.example.championsapp.client.RetrofitApiClientImplementation
import com.example.championsapp.db.RoomDataSource
import com.example.championsapp.repository.RepositoryImpl
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun repository(retrofitApiClientImplementation: RetrofitApiClientImplementation,
                   roomDataSource: RoomDataSource): RepositoryImpl =
        RepositoryImpl(retrofitApiClientImplementation, roomDataSource)
}