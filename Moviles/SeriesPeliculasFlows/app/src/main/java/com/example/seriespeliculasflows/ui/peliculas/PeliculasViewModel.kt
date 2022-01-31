package com.example.seriespeliculasflows.ui.peliculas

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.seriespeliculasflows.data.remote.DataAccessResult
import com.example.seriespeliculasflows.usecases.peliculas.GetPeliculasTrendingUseCase
import com.example.seriespeliculasflows.usecases.peliculas.GetPeliculasUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PeliculasViewModel @Inject constructor(
    private val getPeliculasUseCase: GetPeliculasUseCase,
    private val getPeliculasTrendingUseCase: GetPeliculasTrendingUseCase
) :
    ViewModel() {

    private val _uiState: MutableStateFlow<PeliculasContract.PeliculasScreenStatus> by lazy {
        MutableStateFlow(PeliculasContract.PeliculasScreenStatus())
    }
    val uiState: StateFlow<PeliculasContract.PeliculasScreenStatus> = _uiState

    private val _uiError = Channel<String>()
    val uiError = _uiError.receiveAsFlow()

    fun handleEvent(event: PeliculasContract.Event, query: String?) {
        when (event) {
            PeliculasContract.Event.GetPeliculasQuery -> getPeliculas(query!!)
            PeliculasContract.Event.GetPeliculasUpcoming -> getPeliculasUpcoming()
        }
    }

    fun getPeliculas(query: String) {
        viewModelScope.launch {
            getPeliculasUseCase.getPeliculas(query)
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

    private fun getPeliculasUpcoming() {
        viewModelScope.launch {
            getPeliculasTrendingUseCase.getPeliculasUpcoming()
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