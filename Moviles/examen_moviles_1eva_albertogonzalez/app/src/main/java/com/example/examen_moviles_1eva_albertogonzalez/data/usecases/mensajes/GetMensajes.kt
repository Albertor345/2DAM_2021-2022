package com.example.examen_moviles_1eva_albertogonzalez.data.usecases.mensajes

import com.example.examen_moviles_1eva_albertogonzalez.data.domain.datamappers.toMensaje
import com.example.examen_moviles_1eva_albertogonzalez.data.domain.datamappers.toPunto
import com.example.examen_moviles_1eva_albertogonzalez.data.repositories.mensajes.RepositoryMensajes
import com.example.examen_moviles_1eva_albertogonzalez.data.ui.domain.Mensaje
import com.example.examen_moviles_1eva_albertogonzalez.data.ui.domain.Punto
import javax.inject.Inject

class GetMensajes @Inject constructor(private val repositoryMensajes: RepositoryMensajes) {
    suspend fun getMensajes(): MutableList<Mensaje> =
        repositoryMensajes.getMensajes().map { it.toMensaje() }.toMutableList()

    suspend fun getMensajesPunto(id: Long) = repositoryMensajes.getMensajesPunto(id).toPunto()
}