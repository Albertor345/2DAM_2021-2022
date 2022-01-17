package com.example.seriespelisretrofit.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.seriespelisretrofit.ui.model.FavoritoUI
import com.example.seriespelisretrofit.usecases.favoritos.GetFavoritosLocalUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val getFavoritosLocalLocal: GetFavoritosLocalUseCase) : ViewModel() {

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    private val _favoriteList = MutableLiveData<List<FavoritoUI>>()
    val favoriteList: LiveData<List<FavoritoUI>> get() = _favoriteList

    private var selectedItem = mutableListOf<FavoritoUI>()

    fun getFavoritos() {
        TODO()
    }

    fun delFavorito(favorito: FavoritoUI) {
        TODO()
    }

    fun addFavorito(favorito: FavoritoUI) {
        TODO()
    }

    fun seleccionaFavorito(favorito: FavoritoUI) {
        if (isSelected(favorito)) {
            selectedItem.remove(favorito)
        }
        else {
            selectedItem.add(favorito)
        }
    }

    fun isSelected(favorito: FavoritoUI): Boolean {
        return selectedItem.contains(favorito)
    }

}