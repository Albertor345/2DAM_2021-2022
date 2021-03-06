package com.example.seriespelisretrofit.ui.peliculas

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.seriespelisretrofit.ui.model.PeliculaUI
import com.example.seriespelisretrofit.usecases.peliculas.GetPeliculasUseCase
import com.example.seriespelisretrofit.data.remote.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PeliculasViewModel @Inject constructor(private val getPeliculas: GetPeliculasUseCase) : ViewModel() {

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    private val _currentFilms = MutableLiveData<List<PeliculaUI>>()
    val currentFilms: LiveData<List<PeliculaUI>> get() = _currentFilms

    fun getPeliculas(query: String) {
        try {
            viewModelScope.launch {
                when (val result = getPeliculas.getPeliculas(query)){
                    is NetworkResult.Error -> _error.value = result.message!!
                    is NetworkResult.Success -> _currentFilms.value = result.data!!
                }
            }
        } catch (ex: Exception) {
            _error.value = ex.toString()
        }
    }
}