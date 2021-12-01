package com.example.examen_moviles_1eva_albertogonzalez.data.usecases.mensajes

import com.example.examen_moviles_1eva_albertogonzalez.data.repositories.mensajes.RepositoryMensajes
import com.example.examen_moviles_1eva_albertogonzalez.data.ui.domain.Mensaje
import com.example.examen_moviles_1eva_albertogonzalez.data.ui.domain.datamappers.toMensajeEntity
import javax.inject.Inject

class AddMensaje @Inject constructor(private val repositoryMensajes: RepositoryMensajes) {

    suspend fun addMensaje(mensaje: Mensaje): Long =
        repositoryMensajes.addMensaje(mensaje.toMensajeEntity())
}