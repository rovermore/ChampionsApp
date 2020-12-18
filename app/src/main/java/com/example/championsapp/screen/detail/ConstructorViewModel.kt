package com.example.championsapp.screen.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.championsapp.model.Champion
import com.example.championsapp.model.ChampionTeam
import com.example.championsapp.usecase.GetChampionCatalogUseCase
import com.example.championsapp.usecase.SaveChampionTeamUseCase
import com.example.championsapp.utils.NetworkConnection
import com.example.championsapp.utils.ScreenState
import kotlinx.coroutines.launch
import javax.inject.Inject

class ConstructorViewModel
    @Inject constructor(private val networkConnection: NetworkConnection,
                        private val getChampionCatalogUseCase: GetChampionCatalogUseCase,
                        private val saveChampionTeamUseCase: SaveChampionTeamUseCase
    ) : ViewModel() {

    private val innerBoardList = mutableListOf<Champion>(
        Champion("a"),
        Champion("a"),
        Champion("a"),
        Champion("a"),
        Champion("a"),
        Champion("a"),
        Champion("a"),
        Champion("a"),
        Champion("a"),
        Champion("a")
    )

    private var _boardList =
        MutableLiveData<MutableList<Champion>>()
    val boardList: LiveData<MutableList<Champion>> = _boardList

    private val _uiModel =
        MutableLiveData<List<Champion>>()
    val uiModel: LiveData<List<Champion>> = _uiModel

    private val _uiState = MutableLiveData<ScreenState>()
    val uiState: LiveData<ScreenState> = _uiState

    fun initialize() {
        loadData()
    }

    fun loadData() {
        _uiState.value = ScreenState.Loading
        checkInternetConnection()
    }

    private fun checkInternetConnection() {
        if (networkConnection.isNetworkConnected())
            setupObservers()
        else
            _uiState.setValue(ScreenState.Error)
    }

    private fun setupObservers() {
        observeResponse()
    }

    private fun observeResponse() {
        viewModelScope.launch {
            val response = getChampionCatalogUseCase.request()
            if (response.isNullOrEmpty()) {
                displayError()
            } else
                createAndPostUiModel(response)
        }
    }

    private fun displayError() {
        _uiState.value = ScreenState.Error
    }

    private fun createAndPostUiModel(response: List<Champion>) {
        viewModelScope.launch {
            _uiModel.value = response
            _uiState.setValue(ScreenState.Success)
        }
    }

    fun addChampion(champion: Champion) {
        if (innerBoardList.size == 10) {
            val replaceChampion = innerBoardList.firstOrNull { it.image == "a" }
            innerBoardList.remove(replaceChampion)
            innerBoardList.add(champion)
            _boardList.value = innerBoardList
        }

    }

    fun deleteChampion(position: Int) {
        innerBoardList.removeAt(position)
        innerBoardList.add(Champion("a"))
        _boardList.value = innerBoardList

    }

    fun saveChampionTeam(name: String) {
        viewModelScope.launch {
            saveChampionTeamUseCase.requestWithParameter(ChampionTeam(null, innerBoardList, name))
        }
    }
}