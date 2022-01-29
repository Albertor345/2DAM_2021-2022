package com.example.seriespeliculasflows.ui.detallesPeliculas

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.seriespeliculasflows.data.remote.DataAccessResult
import com.example.seriespeliculasflows.ui.detallesPeliculas.DetallesPeliculasContract.DetallesPeliculasScreenStatus
import com.example.seriespeliculasflows.ui.model.PeliculaUI
import com.example.seriespeliculasflows.usecases.favoritos.AddToFavoritosUseCase
import com.example.seriespeliculasflows.usecases.favoritos.DeleteFromFavoritosUseCase
import com.example.seriespeliculasflows.usecases.peliculas.GetPeliculaUseCase
import com.example.seriespeliculasflows.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetallesPeliculasViewModel @Inject constructor(
    private val getPelicula: GetPeliculaUseCase,
    private val addToFavoritosUseCase: AddToFavoritosUseCase,
    private val deleteFromFavoritosUseCase: DeleteFromFavoritosUseCase
) : ViewModel() {

    private val _uiState: MutableStateFlow<DetallesPeliculasScreenStatus> by lazy {
        MutableStateFlow(DetallesPeliculasScreenStatus())
    }
    val uiState: StateFlow<DetallesPeliculasScreenStatus> = _uiState

    private val _uiError = Channel<String>()
    val uiError = _uiError.receiveAsFlow()

    fun getPelicula(id: Int) {
        viewModelScope.launch {
            getPelicula.getPelicula(id).catch(action = { _uiError.send(it.message ?: "") })
                .collect { result ->
                    when (result) {
                        is DataAccessResult.Error -> _uiState.update {
                            it.copy(error = result.message ?: "")
                        }
                        is DataAccessResult.Loading -> _uiState.update { it.copy(isLoading = true) }
                        is DataAccessResult.Success -> _uiState.update {
                            it.copy(pelicula = result.data, isLoading = false)
                        }
                    }
                }
        }
    }

    fun addToFavorito(pelicula: PeliculaUI) {
        viewModelScope.launch {
            addToFavoritosUseCase.addPeliculaToFavoritos(pelicula)
                .catch(action = { _uiError.send(it.message ?: "") })
                .collect { result ->
                    when (result) {
                        is DataAccessResult.Error -> _uiState.update {
                            it.copy(error = result.message ?: "")
                        }
                        is DataAccessResult.Loading -> _uiState.update { it.copy(isLoading = true) }
                        is DataAccessResult.Success -> _uiState.update {
                            it.copy(mensaje = result.data.let { affectedRows ->
                                if (affectedRows!!.toInt() == 1) Constants.SUCCESS_ADDING_PELICULA else {
                                    ""
                                }
                            }, isLoading = false)
                        }
                    }
                }
        }
    }

    fun removeFavorito(pelicula: PeliculaUI) {
        viewModelScope.launch {
            deleteFromFavoritosUseCase.deletePeliculaFavoritos(pelicula)
                .catch(action = { _uiError.send(it.message ?: "") })
                .collect { result ->
                    when (result) {
                        is DataAccessResult.Error -> _uiState.update {
                            it.copy(error = result.message ?: "")
                        }
                        is DataAccessResult.Loading -> _uiState.update { it.copy(isLoading = true) }
                        is DataAccessResult.Success -> _uiState.update {
                            it.copy(mensaje = result.data.let { affectedRows ->
                                if (affectedRows == 1) Constants.SUCCESS_REMOVING_PELICULA else {
                                    ""
                                }
                            }, isLoading = false)
                        }
                    }
                }
        }
    }
}