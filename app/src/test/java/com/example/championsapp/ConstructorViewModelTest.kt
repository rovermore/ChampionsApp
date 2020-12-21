package com.example.championsapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.championsapp.screen.detail.ConstructorViewModel
import com.example.championsapp.usecase.GetChampionCatalogUseCase
import com.example.championsapp.usecase.SaveChampionTeamUseCase
import com.example.championsapp.utils.NetworkConnection
import com.example.championsapp.utils.ScreenState
import com.google.common.truth.Truth
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class ConstructorViewModelTest {
    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    private val testDispatcher = TestCoroutineDispatcher()

    private lateinit var networkConnection: NetworkConnection
    private lateinit var getChampionCatalogUseCase: GetChampionCatalogUseCase
    private lateinit var saveChampionTeamUseCase: SaveChampionTeamUseCase
    private lateinit var constructorViewModel: ConstructorViewModel

    private val championResponse = ChampionMock.championListMock
    private val emptyChampionResponse = ChampionMock.emptyChampionListMock

    @Before
    fun setup() = runBlockingTest {
        MockitoAnnotations.initMocks(this)
        getChampionCatalogUseCase = Mockito.mock(GetChampionCatalogUseCase::class.java)
        saveChampionTeamUseCase = Mockito.mock(SaveChampionTeamUseCase::class.java)
        networkConnection = Mockito.mock(NetworkConnection::class.java)
        whenever(getChampionCatalogUseCase.request()).thenReturn(championResponse)
        whenever(networkConnection.isNetworkConnected()).thenReturn(true)
        constructorViewModel = ConstructorViewModel(networkConnection, getChampionCatalogUseCase, saveChampionTeamUseCase)
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `checks uiModel is null when championCatalog request is empty`() = runBlocking {
        whenever(getChampionCatalogUseCase.request()).thenReturn(emptyChampionResponse)
        constructorViewModel.initialize()
        Truth.assertThat(constructorViewModel.uiModel.value).isNull()
    }

    @Test
    fun `checks uiModel is not empty when championCatalog request is not empty`() = runBlocking {
        constructorViewModel.initialize()
        Truth.assertThat(constructorViewModel.uiModel.value).isNotNull()
    }

    @Test
    fun `checks uiState is Error when catalog request is empty`() = runBlocking {
        whenever(getChampionCatalogUseCase.request()).thenReturn(emptyChampionResponse)
        constructorViewModel.initialize()
        Truth.assertThat(constructorViewModel.uiState.value).isEqualTo(ScreenState.Error)
    }

    @Test
    fun `checks uiState is Success when catalog request is not empty`() = runBlocking {
        constructorViewModel.initialize()
        Truth.assertThat(constructorViewModel.uiState.value).isEqualTo(ScreenState.Success)
    }
}