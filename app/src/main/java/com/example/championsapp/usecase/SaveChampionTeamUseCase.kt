package com.example.championsapp.usecase

import com.example.championsapp.model.ChampionTeam
import com.example.championsapp.repository.RepositoryImpl
import javax.inject.Inject

class SaveChampionTeamUseCase
@Inject constructor(private val repositoryImpl: RepositoryImpl) : UseCaseWithParameter {
    override suspend fun requestWithParameter(parameter: Any): Any {
        return repositoryImpl.saveChampionTeam(parameter as ChampionTeam)
    }
}