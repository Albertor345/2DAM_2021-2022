package com.example.examen_moviles_1eva_albertogonzalez.data.ui.delete

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.examen_moviles_1eva_albertogonzalez.data.ui.domain.Mensaje
import com.example.examen_moviles_1eva_albertogonzalez.data.ui.domain.Punto
import com.example.examen_moviles_1eva_albertogonzalez.data.usecases.mensajes.DeleteMensaje
import com.example.examen_moviles_1eva_albertogonzalez.data.usecases.mensajes.GetMensajes
import com.example.examen_moviles_1eva_albertogonzalez.data.usecases.puntos.GetPuntos
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DeleteViewModel @Inject constructor(
    private val deleteMensaje: DeleteMensaje,
    private val getPuntos: GetPuntos,
    private val getMensajes: GetMensajes
) : ViewModel() {

    private val _puntos = MutableLiveData<List<Punto>>()
    val puntos: LiveData<List<Punto>> get() = _puntos

    private val _mensajes = MutableLiveData<List<Mensaje>>()
    val mensajes: LiveData<List<Mensaje>> get() = _mensajes

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

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

    fun delMensaje(mensaje: Mensaje) {
        try {
            viewModelScope.launch {
                deleteMensaje.deleteMensaje(mensaje)
                getMensajes()
            }
        } catch (ex: Exception) {
            _error.value = ex.toString()
        }
    }

    fun getMensajes() {
        try {
            viewModelScope.launch {
                val list = getMensajes.getMensajes()
                _mensajes.value = list
            }
        } catch (ex: Exception) {
            _error.value = ex.toString()
        }
    }

    fun getMensajesPunto(punto: Punto) {
        try {
            viewModelScope.launch {
                val punto = getMensajes.getMensajesPunto(punto.id!!)
                _mensajes.value = punto.mensajes
            }
        } catch (ex: Exception) {
            _error.value = ex.toString()
        }
    }




}