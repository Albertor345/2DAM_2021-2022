package com.example.seriespeliculasflows.ui.favoritos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.seriespeliculasflows.data.remote.DataAccessResult
import com.example.seriespeliculasflows.data.remote.repositories.peliculas.PelisRepository
import com.example.seriespeliculasflows.data.remote.repositories.series.SeriesRepository
import com.example.seriespeliculasflows.ui.favoritos.FavoritosContract.FavoritosScreenStatus
import com.example.seriespeliculasflows.ui.model.FavoritoUI
import com.example.seriespeliculasflows.ui.model.PeliculaUI
import com.example.seriespeliculasflows.ui.model.SerieUI
import com.example.seriespeliculasflows.usecases.favoritos.DeleteFromFavoritosUseCase
import com.example.seriespeliculasflows.usecases.favoritos.GetFavoritosLocalUseCase
import com.example.seriespeliculasflows.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
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

    private val _uiState: MutableStateFlow<FavoritosScreenStatus> by lazy {
        MutableStateFlow(FavoritosScreenStatus())
    }
    val uiState: StateFlow<FavoritosScreenStatus> = _uiState

    private val _uiError = Channel<String>()
    val uiError = _uiError.receiveAsFlow()

    private var selectedItem = mutableListOf<FavoritoUI>()


    fun handleEvent(event: FavoritosContract.Event, favorito: FavoritoUI?) {
        when (event) {
            FavoritosContract.Event.FetchFavoritos -> getFavoritos()
            FavoritosContract.Event.DeleteFavorito -> favorito?.let { delFavorito(it) }
            FavoritosContract.Event.SeleccionarFavorito -> favorito?.let { seleccionaFavorito(it) }
            FavoritosContract.Event.ItemIsSelected -> favorito?.let { isSelected(it) }
        }
    }

    fun getFavoritos() {
        viewModelScope.launch {
            getFavoritosLocalUseCase.getFavoritos()
                .catch(action = { _uiError.send(it.message ?: "") })
                .collect { result ->
                    when (result) {
                        is DataAccessResult.Error -> _uiState.update { it.copy(error = result.message) }
                        is DataAccessResult.Success -> _uiState.update {
                            it.copy(
                                favoritos = result.data ?: emptyList(), isLoading = false
                            )
                        }
                        is DataAccessResult.Loading -> _uiState.update { it.copy(isLoading = true) }
                    }
                }
        }
    }

    fun delFavorito(favorito: FavoritoUI) {
        viewModelScope.launch {
            if (favorito.tipo == Constants.PELICULA_TYPE) {
                lateinit var pelicula: PeliculaUI
                when (val result = repository.getPelicula(favorito.id)) {
                    is DataAccessResult.Success -> {
                        pelicula = result.data!!
                        deleteFromFavoritosUseCase.deletePeliculaFavoritos(pelicula)
                    }
                    is DataAccessResult.Error -> _error.value = result.message!!
                }
            } else {
                lateinit var serie: SerieUI
                when (val result = repositorySeries.getSerie(favorito.id)) {
                    is DataAccessResult.Success -> {
                        serie = result.data!!
                        deleteFromFavoritosUseCase.deleteSerieFavoritos(serie)

                    }
                    is DataAccessResult.Error -> _error.value = result.message!!
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