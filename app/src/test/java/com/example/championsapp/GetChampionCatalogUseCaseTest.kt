package com.example.championsapp

import com.example.championsapp.repository.RepositoryImpl
import com.example.championsapp.usecase.GetChampionCatalogUseCase
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class GetChampionCatalogUseCaseTest {

    private lateinit var repository: RepositoryImpl
    private lateinit var getChampionCatalogUseCase: GetChampionCatalogUseCase

    private val championResponse = ChampionMock.championListMock

    @Before
    fun setup() = runBlockingTest {
        MockitoAnnotations.initMocks(this)
        repository = Mockito.mock(RepositoryImpl::class.java)
        whenever(repository.getChampionResponse()).thenReturn(this@GetChampionCatalogUseCaseTest.championResponse)
        getChampionCatalogUseCase = GetChampionCatalogUseCase(repository)
    }


    @Test
    fun `if Repository returns transactions then GetChampionCatalogUseCaseTest returns same transactions`() = runBlockingTest {
        val championsResponseFromUseCase = getChampionCatalogUseCase.request()
        Assert.assertEquals(championsResponseFromUseCase, this@GetChampionCatalogUseCaseTest.championResponse)
        Assert.assertEquals(championsResponseFromUseCase[0].image, this@GetChampionCatalogUseCaseTest.championResponse[0].image)
    }
}