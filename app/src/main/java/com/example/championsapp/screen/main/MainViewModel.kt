package com.example.championsapp.screen.main


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.championsapp.model.ChampionTeam
import com.example.championsapp.usecase.GetChampionTeamFromDBUseCase
import com.example.championsapp.utils.NetworkConnection
import com.example.championsapp.utils.ScreenState
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel
@Inject constructor(private val getChampionTeamFromDBUseCase: GetChampionTeamFromDBUseCase) : ViewModel() {

    lateinit var uiModel: LiveData<List<ChampionTeam>>

    private val _uiState = MutableLiveData<ScreenState>()
    val uiState: LiveData<ScreenState> = _uiState

    fun initialize() {
        loadData()
    }

    private fun loadData() {
        _uiState.value = ScreenState.Loading
        setupObservers()
    }


    private fun setupObservers() {
        observeResponse()
    }

    private fun observeResponse() {
        viewModelScope.launch {
            uiModel = getChampionTeamFromDBUseCase.request()
        }
    }

    private fun displayError() {
        viewModelScope.launch {
            _uiState.setValue(ScreenState.Error)
        }
    }
}