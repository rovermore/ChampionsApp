package com.example.championsapp.usecase

interface UseCaseWithParameter {
    suspend fun requestWithParameter(parameter: Any): Any
}