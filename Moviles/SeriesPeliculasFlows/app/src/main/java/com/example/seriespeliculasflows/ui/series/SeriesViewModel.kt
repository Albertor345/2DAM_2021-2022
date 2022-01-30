package com.example.seriespeliculasflows.ui.series

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.seriespeliculasflows.data.remote.DataAccessResult
import com.example.seriespeliculasflows.usecases.series.GetSeriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SeriesViewModel @Inject constructor(private val getSeriesUseCase: GetSeriesUseCase) :
    ViewModel() {

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    private val _currentSeries = MutableLiveData<List<SerieUI>>()
    val currentSeries: LiveData<List<SerieUI>> get() = _currentSeries

    fun getSeries(query: String) {
        try {
            viewModelScope.launch {
                when (val result = getSeriesUseCase.getSeries(query)) {
                    is DataAccessResult.Error -> _error.value = result.message!!
                    is DataAccessResult.Success -> _currentSeries.value = result.data!!
                }
            }
        } catch (ex: Exception) {
            _error.value = ex.toString()
        }
    }
}