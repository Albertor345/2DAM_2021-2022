package com.example.seriespeliculasflows.ui.detallesSeries

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.seriespeliculasflows.data.remote.DataAccessResult
import com.example.seriespeliculasflows.ui.model.ItemUI
import com.example.seriespeliculasflows.usecases.favoritos.AddToFavoritosUseCase
import com.example.seriespeliculasflows.usecases.favoritos.DeleteFromFavoritosUseCase
import com.example.seriespeliculasflows.usecases.favoritos.GetFavoritosLocalUseCase
import com.example.seriespeliculasflows.usecases.series.GetSerieUseCase
import com.example.seriespeliculasflows.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetallesSeriesViewModel @Inject constructor(
    private val getSerieUseCase: GetSerieUseCase,
    private val addToFavoritosUseCase: AddToFavoritosUseCase,
    private val deleteFromFavoritosUseCase: DeleteFromFavoritosUseCase
) :
    ViewModel() {

    private val _uiState: MutableStateFlow<DetallesSeriesContract.DetallesSeriesScreenStatus> by lazy {
        MutableStateFlow(DetallesSeriesContract.DetallesSeriesScreenStatus())
    }
    val uiState: StateFlow<DetallesSeriesContract.DetallesSeriesScreenStatus> = _uiState

    private val _uiError = Channel<String>()
    val uiError = _uiError.receiveAsFlow()


    fun handleEvent(
        event: DetallesSeriesContract.Event,
        id: Int?,
        serie: ItemUI.SerieUI?
    ) {
        when (event) {
            DetallesSeriesContract.Event.AddFavorito -> serie?.let { addToFavorito(serie) }
            DetallesSeriesContract.Event.DeleteFavorito -> serie?.let {
                removeFavorito(
                    serie
                )
            }
            DetallesSeriesContract.Event.GetSerie -> id?.let { getSerie(id) }
        }
    }

    fun getSerie(id: Int) {
        viewModelScope.launch {
            getSerieUseCase.getSerie(id).catch(action = { _uiError.send(it.message ?: "") })
                .collect { result ->
                    when (result) {
                        is DataAccessResult.Error -> _uiState.update {
                            it.copy(error = result.message ?: "")
                        }
                        is DataAccessResult.Loading -> _uiState.update { it.copy(isLoading = true) }
                        is DataAccessResult.Success -> _uiState.update {
                            it.copy(serie = result.data, isLoading = false)
                        }
                    }
                }
        }
    }

    fun addToFavorito(serie: ItemUI.SerieUI) {


        viewModelScope.launch {
            addToFavoritosUseCase.addSerieToFavoritos(serie)
                .catch(action = { _uiError.send(it.message ?: "") })
                .collect { result ->
                    when (result) {
                        is DataAccessResult.Error -> _uiState.update {
                            it.copy(error = result.message ?: "")
                        }
                        is DataAccessResult.Loading -> _uiState.update { it.copy(isLoading = true) }
                        is DataAccessResult.Success -> _uiState.update {
                            it.copy(mensaje = result.data.let { affectedRows ->
                                if (affectedRows!!.toInt() == 1) Constants.SUCCESS_ADDING_FAVORITO else {
                                    ""
                                }
                            }, serie = serie.apply { favorito = true }, isLoading = false)
                        }
                    }
                }
        }
    }

    fun removeFavorito(serie: ItemUI.SerieUI) {
        viewModelScope.launch {
            deleteFromFavoritosUseCase.deleteFromFavorito(serie)
                .catch(action = { _uiError.send(it.message ?: "") })
                .collect { result ->
                    when (result) {
                        is DataAccessResult.Error -> _uiState.update {
                            it.copy(error = result.message ?: "")
                        }
                        is DataAccessResult.Loading -> _uiState.update { it.copy(isLoading = true) }
                        is DataAccessResult.Success -> {
                            _uiState.update {
                                it.copy(
                                    mensaje = result.data.let { affectedRows ->
                                        if (affectedRows == 1) Constants.SUCCESS_REMOVING_FAVORITO else {
                                            ""
                                        }
                                    },
                                    serie = serie.apply { favorito = false },
                                    isLoading = false
                                )
                            }

                        }
                    }
                }
        }
    }
}