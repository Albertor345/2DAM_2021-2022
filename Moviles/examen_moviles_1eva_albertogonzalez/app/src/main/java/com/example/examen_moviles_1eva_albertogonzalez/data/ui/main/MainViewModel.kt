package com.example.examen_moviles_1eva_albertogonzalez.data.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.examen_moviles_1eva_albertogonzalez.data.ui.domain.Mensaje
import com.example.examen_moviles_1eva_albertogonzalez.data.ui.domain.Punto
import com.example.examen_moviles_1eva_albertogonzalez.data.usecases.mensajes.AddMensaje
import com.example.examen_moviles_1eva_albertogonzalez.data.usecases.puntos.AddPuntos
import com.example.examen_moviles_1eva_albertogonzalez.data.usecases.puntos.GetPuntos
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val addMensaje: AddMensaje,
    private val getPuntos: GetPuntos,
    private val addPuntos: AddPuntos
) : ViewModel() {


    private val _puntos = MutableLiveData<List<Punto>>()
    val puntos: LiveData<List<Punto>> get() = _puntos


    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun addMensaje(mensaje: Mensaje) {
        try {
            viewModelScope.launch {
                addMensaje.addMensaje(mensaje)
                _error.value = "Mensaje añadido"
            }
        } catch (ex: Exception) {
            _error.value = ex.toString()
        }
    }

    fun getPuntos() {
        try {
            viewModelScope.launch {
                val list = getPuntos.getPuntos()
                _puntos.value = list
            }
        } catch (ex: Exception) {
            _error.value = ex.toString()
        }
    }

    fun addPuntos(list: MutableList<Punto>) {
        try {
            viewModelScope.launch {
                addPuntos.addPuntos(list)
                getPuntos()
            }
        } catch (ex: Exception) {
            _error.value = ex.toString()
        }
    }
}