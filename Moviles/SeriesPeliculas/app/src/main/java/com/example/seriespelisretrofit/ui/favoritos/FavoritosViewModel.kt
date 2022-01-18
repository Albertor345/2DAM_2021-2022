package com.example.seriespelisretrofit.ui.favoritos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.seriespelisretrofit.ui.model.FavoritoUI
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoritosViewModel @Inject constructor() : ViewModel() {
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

    fun addItemSelected(favorito: FavoritoUI) {
        selectedItem.contains(favorito) ?: selectedItem.add(favorito)
    }

    fun getSelectedItemSize() : Int{
        return selectedItem.size
    }
}