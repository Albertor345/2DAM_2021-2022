package com.example.seriespelisretrofit.ui.series.detalles

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.seriespelisretrofit.ui.model.SerieUI
import com.example.seriespelisretrofit.usecases.series.GetSeriesUseCase
import com.example.seriespelisretrofit.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetallesSeriesViewModel @Inject constructor(private val getSeriesUseCase: GetSeriesUseCase) :
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
                    is NetworkResult.Success -> _currentSerie.value = result.data!!
                }
            }
        } catch (ex: Exception) {
            _error.value = ex.toString()
        }
    }
}