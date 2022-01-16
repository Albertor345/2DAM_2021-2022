package com.example.SeriesPelisRetrofit.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.seriespelisretrofit.usecases.GetPeliculasUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val getPeliculas: GetPeliculasUseCase ) : ViewModel() {

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    private val _currentFilms = MutableLiveData<String>()
    val currentFilms: LiveData<String> get() = _error

    fun getPeliculas(query: String, page: Int) {
        try {
            viewModelScope.launch {
                val list = getPeliculas.getPeliculas(query, page)
                when (list){

                }
                _currentFilms.value = list
            }
        } catch (ex: Exception) {
            _error.value = ex.toString()
        }
    }

}