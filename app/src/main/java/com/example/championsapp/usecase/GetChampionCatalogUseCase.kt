package com.example.championsapp.usecase

import com.example.championsapp.model.Champion
import com.example.championsapp.repository.RepositoryImpl
import javax.inject.Inject

class GetChampionCatalogUseCase
    @Inject constructor(private val repositoryImpl: RepositoryImpl): UseCase{

    override suspend fun request(): List<Champion> {
        return repositoryImpl.getChampionResponse()
    }
}