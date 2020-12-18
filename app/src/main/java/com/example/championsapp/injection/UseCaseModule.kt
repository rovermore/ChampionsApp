package com.example.championsapp.injection

import com.example.championsapp.repository.RepositoryImpl
import com.example.championsapp.usecase.GetChampionCatalogUseCase
import com.example.championsapp.usecase.GetChampionTeamFromDBUseCase
import com.example.championsapp.usecase.SaveChampionTeamUseCase

import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {

    @Provides
    fun getChampionCatalogUseCase(repositoryImpl: RepositoryImpl): GetChampionCatalogUseCase =
        GetChampionCatalogUseCase(repositoryImpl)

    @Provides
    fun saveChampionTeamUseCase(repositoryImpl: RepositoryImpl): SaveChampionTeamUseCase =
        SaveChampionTeamUseCase(repositoryImpl)

    @Provides
    fun getChampionTeamFromDBUseCase(repositoryImpl: RepositoryImpl): GetChampionTeamFromDBUseCase =
        GetChampionTeamFromDBUseCase(repositoryImpl)
}