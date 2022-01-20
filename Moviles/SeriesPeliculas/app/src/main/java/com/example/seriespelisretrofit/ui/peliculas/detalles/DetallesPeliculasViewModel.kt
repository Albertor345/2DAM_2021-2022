package com.example.seriespelisretrofit.ui.peliculas.detalles

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.seriespelisretrofit.data.remote.NetworkResult
import com.example.seriespelisretrofit.ui.model.PeliculaUI
import com.example.seriespelisretrofit.usecases.favoritos.AddToFavoritosUseCase
import com.example.seriespelisretrofit.usecases.favoritos.DeleteFromFavoritosUseCase
import com.example.seriespelisretrofit.usecases.favoritos.GetFavoritosLocalUseCase
import com.example.seriespelisretrofit.usecases.peliculas.GetPeliculasUseCase
import com.example.seriespelisretrofit.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetallesPeliculasViewModel @Inject constructor(
    private val getPeliculas: GetPeliculasUseCase,
    private val addToFavoritosUseCase: AddToFavoritosUseCase,
    private val getFavoritosLocalUseCase: GetFavoritosLocalUseCase,
    private val deleteFromFavoritosUseCase: DeleteFromFavoritosUseCase
) : ViewModel() {
    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    private val _currentFilm = MutableLiveData<PeliculaUI>()
    val currentFilm: LiveData<PeliculaUI> get() = _currentFilm

    fun getPelicula(id: Int) {
        try {
            viewModelScope.launch {
                when (val result = getPeliculas.getPelicula(id)) {
                    is NetworkResult.Error -> _error.value = result.message!!
                    is NetworkResult.Success -> {
                        val pelicula: PeliculaUI = result.data!!
                        pelicula.favorito = getFavoritosLocalUseCase.getPeliculaLocal(id) != null
                        _currentFilm.value = result.data!!
                    }
                }
            }
        } catch (ex: Exception) {
            _error.value = ex.toString()
        }
    }

    fun addToFavorito(pelicula: PeliculaUI) {
        viewModelScope.launch {
            try {
                addToFavoritosUseCase.addPeliculaToFavoritos(pelicula)
                _error.value = Constants.SUCCESS_ADDING_PELICULA
            } catch (ex: java.lang.Exception) {
                _error.value = ex.toString()
            }

        }
    }

    fun removeFavorito(pelicula: PeliculaUI) {
        viewModelScope.launch {
            try {
                deleteFromFavoritosUseCase.deletePeliculaFavoritos(pelicula)
                _error.value = Constants.SUCCESS_REMOVING_PELICULA
            } catch (ex: java.lang.Exception) {
                _error.value = ex.toString()
            }
        }
    }
}