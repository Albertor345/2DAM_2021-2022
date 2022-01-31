package com.example.seriespeliculasflows.ui.seasons

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.seriespeliculasflows.data.remote.DataAccessResult
import com.example.seriespeliculasflows.usecases.temporadas.GetTemporadaUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetallesSeasonViewModel @Inject constructor(private val getSeasonUseCase: GetTemporadaUseCase) :
    ViewModel() {

    private val _uiState: MutableStateFlow<SeasonsContract.SeasonsScreenStatus> by lazy {
        MutableStateFlow(SeasonsContract.SeasonsScreenStatus())
    }
    val uiState: StateFlow<SeasonsContract.SeasonsScreenStatus> = _uiState

    private val _uiError = Channel<String>()
    val uiError = _uiError.receiveAsFlow()

    fun getSeason(id: Int, seasonNumber: Int) {
        viewModelScope.launch {
            getSeasonUseCase.getSeason(id, seasonNumber)
                .catch(action = { _uiError.send(it.message ?: "") })
                .collect { result ->
                    when (result) {
                        is DataAccessResult.Error -> _uiState.update {
                            it.copy(error = result.message ?: "")
                        }
                        is DataAccessResult.Loading -> _uiState.update { it.copy(isLoading = true) }
                        is DataAccessResult.Success -> _uiState.update {
                            it.copy(season = result.data, isLoading = false)
                        }
                    }
                }
        }
    }

}