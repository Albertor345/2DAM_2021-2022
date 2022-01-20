package com.example.seriespelisretrofit.ui.favoritos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.seriespelisretrofit.data.remote.NetworkResult
import com.example.seriespelisretrofit.data.remote.repositories.peliculas.PelisRepository
import com.example.seriespelisretrofit.data.remote.repositories.series.SeriesRepository
import com.example.seriespelisretrofit.ui.model.FavoritoUI
import com.example.seriespelisretrofit.ui.model.PeliculaUI
import com.example.seriespelisretrofit.ui.model.SerieUI
import com.example.seriespelisretrofit.ui.model.datamappers.toFavorito
import com.example.seriespelisretrofit.usecases.favoritos.DeleteFromFavoritosUseCase
import com.example.seriespelisretrofit.usecases.favoritos.GetFavoritosLocalUseCase
import com.example.seriespelisretrofit.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritosViewModel @Inject constructor(
    private val getFavoritosLocalUseCase: GetFavoritosLocalUseCase,
    private val deleteFromFavoritosUseCase: DeleteFromFavoritosUseCase,
    private val repository: PelisRepository,
    private val repositorySeries: SeriesRepository
) :
    ViewModel() {
    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    private val _favoriteList = MutableLiveData<List<FavoritoUI>>()
    val favoriteList: LiveData<List<FavoritoUI>> get() = _favoriteList

    private var selectedItem = mutableListOf<FavoritoUI>()

    fun getFavoritos() {
        viewModelScope.launch {
            _favoriteList.value = getFavoritosLocalUseCase.getSeriesLocal()
                .map { it.toFavorito() } + getFavoritosLocalUseCase.getPeliculasLocal()
                .map { it.toFavorito() }
        }
    }

    fun delFavorito(favorito: FavoritoUI) {
        viewModelScope.launch {
            if (favorito.tipo == Constants.PELICULA_TYPE) {
                lateinit var pelicula: PeliculaUI
                when (val result = repository.getPelicula(favorito.id)) {
                    is NetworkResult.Success -> {
                        pelicula = result.data!!
                        deleteFromFavoritosUseCase.deletePeliculaFavoritos(pelicula)
                    }
                    is NetworkResult.Error -> _error.value = result.message!!
                }
            } else {
                lateinit var serie: SerieUI
                when (val result = repositorySeries.getSerie(favorito.id)) {
                    is NetworkResult.Success -> {
                        serie = result.data!!
                        deleteFromFavoritosUseCase.deleteSerieFavoritos(serie)

                    }
                    is NetworkResult.Error -> _error.value = result.message!!
                }
            }
            getFavoritos()
        }
    }

    fun seleccionaFavorito(favorito: FavoritoUI) {
        if (isSelected(favorito)) {
            selectedItem.remove(favorito)
        } else {
            selectedItem.add(favorito)
        }
    }

    fun isSelected(favorito: FavoritoUI): Boolean {
        return selectedItem.contains(favorito)
    }

    fun getSelectedItemSize(): Int {
        return selectedItem.size
    }
}