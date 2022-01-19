package com.example.seriespelisretrofit.ui.series

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
class SeriesViewModel @Inject constructor(private val getSeriesUseCase: GetSeriesUseCase) :
    ViewModel() {

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    private val _currentSeries = MutableLiveData<List<SerieUI>>()
    val currentSeries: LiveData<List<SerieUI>> get() = _currentSeries

    fun getSeries(query: String, page: Int) {
        try {
            viewModelScope.launch {
                when (val result = getSeriesUseCase.getSeries(query, page)) {
                    is NetworkResult.Error -> _error.value = result.message!!
                    is NetworkResult.Success -> _currentSeries.value = result.data!!
                }
            }
        } catch (ex: Exception) {
            _error.value = ex.toString()
        }
    }
}