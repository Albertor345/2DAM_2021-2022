package com.example.examen_moviles_1eva_albertogonzalez.data.usecases.mensajes

import com.example.examen_moviles_1eva_albertogonzalez.data.repositories.mensajes.RepositoryMensajes
import com.example.examen_moviles_1eva_albertogonzalez.data.ui.domain.Mensaje
import com.example.examen_moviles_1eva_albertogonzalez.data.ui.domain.datamappers.toMensajeEntity
import javax.inject.Inject

class DeleteMensaje @Inject constructor(private val repositoryMensajes: RepositoryMensajes){
    suspend fun deleteMensaje(mensaje: Mensaje) = repositoryMensajes.deleteMensaje(mensaje.toMensajeEntity())
}