package com.example.seriespelisretrofit.ui.seasons

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.seriespelisretrofit.ui.model.CapituloUI
import com.example.seriespelisretrofit.usecases.temporadas.GetTemporadaUseCase
import com.example.seriespelisretrofit.utils.NetworkResult
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
                    is NetworkResult.Error -> _error.value = result.message!!
                    is NetworkResult.Success -> _capitulos.value = result.data?.capitulos!!
                }
            }
        } catch (ex: Exception) {
            _error.value = ex.toString()
        }
    }

}