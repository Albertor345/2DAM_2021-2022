package com.example.seriespeliculasflows.ui.series

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.seriespeliculasflows.data.remote.DataAccessResult
import com.example.seriespeliculasflows.usecases.series.GetSeriesUseCase
import com.example.seriespeliculasflows.usecases.series.GetTopRatedSeriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SeriesViewModel @Inject constructor(
    private val getSeriesUseCase: GetSeriesUseCase,
    private val getTopRatedSeriesUseCase: GetTopRatedSeriesUseCase
) :
    ViewModel() {

    private val _uiState: MutableStateFlow<SeriesContract.SeriesScreenStatus> by lazy {
        MutableStateFlow(SeriesContract.SeriesScreenStatus())
    }
    val uiState: StateFlow<SeriesContract.SeriesScreenStatus> = _uiState

    private val _uiError = Channel<String>()
    val uiError = _uiError.receiveAsFlow()


    fun handleEvent(event: SeriesContract.Event, query: String?) {
        when (event) {
            SeriesContract.Event.GetSeriesQuery -> getSeries(query!!)
            SeriesContract.Event.GetTopRatedSeries -> getTopRatedSeries()
        }
    }

    fun getSeries(query: String) {
        viewModelScope.launch {
            getSeriesUseCase.getSeries(query)
                .catch(action = { _uiError.send(it.message ?: "") })
                .collect { result ->
                    when (result) {
                        is DataAccessResult.Error -> _uiState.update {
                            it.copy(error = result.message ?: "")
                        }
                        is DataAccessResult.Loading -> _uiState.update { it.copy(isLoading = true) }
                        is DataAccessResult.Success -> _uiState.update {
                            it.copy(items = result.data.orEmpty(), isLoading = false)
                        }
                    }
                }
        }
    }

    fun getTopRatedSeries() {
        viewModelScope.launch {
            getTopRatedSeriesUseCase.getTopRatedSeries()
                .catch(action = { _uiError.send(it.message ?: "") })
                .collect { result ->
                    when (result) {
                        is DataAccessResult.Error -> _uiState.update {
                            it.copy(error = result.message ?: "")
                        }
                        is DataAccessResult.Loading -> _uiState.update { it.copy(isLoading = true) }
                        is DataAccessResult.Success -> _uiState.update {
                            it.copy(items = result.data.orEmpty(), isLoading = false)
                        }
                    }
                }
        }
    }
}