package com.example.championsapp.usecase

import androidx.lifecycle.LiveData
import com.example.championsapp.model.ChampionTeam
import com.example.championsapp.repository.RepositoryImpl
import javax.inject.Inject

class GetChampionTeamFromDBUseCase
@Inject constructor(private val repositoryImpl: RepositoryImpl): UseCase {

    override suspend fun request(): LiveData<List<ChampionTeam>> {
        return repositoryImpl.getAllChampionTeam()
    }
}