package com.example.seriespelisretrofit.ui.series.detalles

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.seriespelisretrofit.data.local.LocalResult
import com.example.seriespelisretrofit.data.remote.NetworkResult
import com.example.seriespelisretrofit.ui.model.SerieUI
import com.example.seriespelisretrofit.usecases.favoritos.AddToFavoritosUseCase
import com.example.seriespelisretrofit.usecases.favoritos.DeleteFromFavoritosUseCase
import com.example.seriespelisretrofit.usecases.favoritos.GetFavoritosLocalUseCase
import com.example.seriespelisretrofit.usecases.series.GetSeriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetallesSeriesViewModel @Inject constructor(
    private val getSeriesUseCase: GetSeriesUseCase,
    private val addToFavoritosUseCase: AddToFavoritosUseCase,
    private val getFavoritosLocalUseCase: GetFavoritosLocalUseCase,
    private val deleteFromFavoritosUseCase: DeleteFromFavoritosUseCase
) :
    ViewModel() {

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    private val _currentSerie = MutableLiveData<SerieUI>()
    val currentSerie: LiveData<SerieUI> get() = _currentSerie


    fun getSerie(id: Int) {
        try {
            viewModelScope.launch {
                when (val result = getSeriesUseCase.getSerie(id)) {
                    is NetworkResult.Error -> _error.value = result.message!!
                    is NetworkResult.Success -> {
                        val serie: SerieUI = result.data!!
                        serie.favorito = getFavoritosLocalUseCase.getSerieLocal(id) != null
                        _currentSerie.value = result.data!!
                    }
                }
            }
        } catch (ex: Exception) {
            _error.value = ex.toString()
        }
    }

    fun addToFavorito(serie: SerieUI) {
        viewModelScope.launch {
            try {
                when (val result = addToFavoritosUseCase.addSerieToFavoritos(serie)) {
                    is LocalResult.Success -> _error.value = result.data!!
                    is LocalResult.Error -> _error.value = result.error!!
                }
            } catch (ex: java.lang.Exception) {
                _error.value = ex.toString()
            }

        }
    }

    fun removeFavorito(serie: SerieUI) {
        viewModelScope.launch {
            try {
                when (val result =
                    deleteFromFavoritosUseCase.deleteSerieFavoritos(serie)) {
                    is LocalResult.Success -> _error.value = result.data!!
                    is LocalResult.Error -> _error.value = result.error!!
                }
            } catch (ex: java.lang.Exception) {
                _error.value = ex.toString()
            }
        }
    }
}