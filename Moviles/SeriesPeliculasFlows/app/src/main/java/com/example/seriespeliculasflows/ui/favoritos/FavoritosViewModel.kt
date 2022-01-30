package com.example.seriespeliculasflows.ui.favoritos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.seriespeliculasflows.data.remote.DataAccessResult
import com.example.seriespeliculasflows.data.remote.repositories.peliculas.PelisRepository
import com.example.seriespeliculasflows.data.remote.repositories.series.SeriesRepository
import com.example.seriespeliculasflows.ui.favoritos.FavoritosContract.FavoritosScreenStatus
import com.example.seriespeliculasflows.ui.model.ItemUI
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


    fun handleEvent(event: FavoritosContract.Event, item: ItemUI?) {
        when (event) {
            FavoritosContract.Event.GetFavoritos -> getFavoritos()
            FavoritosContract.Event.DeleteFavorito -> item?.let { delFavorito(it) }
            FavoritosContract.Event.SeleccionarFavorito -> item?.let { seleccionaFavorito(it) }
            FavoritosContract.Event.ItemIsSelected -> item?.let { isSelected(it) }
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
                                items = result.data ?: emptyList(), isLoading = false
                            )
                        }
                        is DataAccessResult.Loading -> _uiState.update { it.copy(isLoading = true) }
                    }
                }
        }
    }

    fun delFavorito(item: ItemUI) {
        viewModelScope.launch {
            deleteFromFavoritosUseCase.deleteFromFavorito(item)
                .catch(action = { _uiError.send(it.message ?: "") })
                .collect { result ->
                    when (result) {
                        is DataAccessResult.Error -> _uiState.update { it.copy(error = result.message) }
                        is DataAccessResult.Success -> _uiState.update {
                            it.copy(
                                error = Constants.SUCCESS_REMOVING_FAVORITO, isLoading = false
                            )
                        }
                        is DataAccessResult.Loading -> _uiState.update { it.copy(isLoading = true) }
                    }
                }
        }
    }

    fun seleccionaFavorito(item: ItemUI) {
        if (isSelected(item)) {
            _uiState.update {
                it.copy(
                    selectedItems = it.selectedItems.minus(item).toMutableList()
                )
            }
        } else {
            _uiState.update {
                it.copy(
                    selectedItems = it.selectedItems.plus(item).toMutableList()
                )
            }
        }
    }

    fun isSelected(item: ItemUI): Boolean {
        return uiState.value.selectedItems.contains(item)
    }

    fun getSelectedItemSize(): Int {
        return uiState.value.selectedItems.size
    }
}