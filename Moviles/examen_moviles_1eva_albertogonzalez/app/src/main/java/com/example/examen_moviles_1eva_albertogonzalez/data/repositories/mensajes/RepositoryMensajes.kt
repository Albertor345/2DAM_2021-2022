package com.example.examen_moviles_1eva_albertogonzalez.data.repositories.mensajes

import com.example.examen_moviles_1eva_albertogonzalez.data.domain.MensajeEntity
import javax.inject.Inject

class RepositoryMensajes @Inject constructor(private val daoMensajes: DaoMensajes) {

    suspend fun getMensajes() = daoMensajes.getMensajes()

    suspend fun addMensaje(mensaje: MensajeEntity) =
        daoMensajes.addMensaje(mensaje)

    suspend fun getMensaje(id: Long): MensajeEntity = daoMensajes.getMensaje(id)

    suspend fun deleteMensaje(mensaje: MensajeEntity) = daoMensajes.deleteMensaje(mensaje)

    suspend fun getMensajesPunto(id: Long) = daoMensajes.getPuntoWithMensajes(id)

}

