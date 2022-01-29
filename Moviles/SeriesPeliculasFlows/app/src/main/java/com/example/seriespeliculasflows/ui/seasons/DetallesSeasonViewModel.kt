package com.example.seriespeliculasflows.ui.seasons

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.seriespeliculasflows.data.remote.DataAccessResult
import com.example.seriespeliculasflows.ui.model.CapituloUI
import com.example.seriespeliculasflows.usecases.temporadas.GetTemporadaUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetallesSeasonViewModel @Inject constructor(private val getSeasonUseCase: GetTemporadaUseCase) :
    ViewModel() {

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    private val _capitulos = MutableLiveData<List<CapituloUI>>()
    val capitulos: LiveData<List<CapituloUI>> get() = _capitulos

    fun getSeason(id: Int, seasonNumber: Int) {
        try {
            viewModelScope.launch {
                when (val result = getSeasonUseCase.getSeason(id, seasonNumber)) {
                    is DataAccessResult.Error -> _error.value = result.message!!
                    is DataAccessResult.Success -> _capitulos.value = result.data?.capitulos!!
                }
            }
        } catch (ex: Exception) {
            _error.value = ex.toString()
        }
    }

}