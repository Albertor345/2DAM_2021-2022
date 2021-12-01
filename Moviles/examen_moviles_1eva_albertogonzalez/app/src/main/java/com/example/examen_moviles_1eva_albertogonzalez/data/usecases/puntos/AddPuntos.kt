package com.example.examen_moviles_1eva_albertogonzalez.data.usecases.puntos

import com.example.examen_moviles_1eva_albertogonzalez.data.repositories.puntos.RepositoryPuntos
import com.example.examen_moviles_1eva_albertogonzalez.data.ui.domain.Punto
import com.example.examen_moviles_1eva_albertogonzalez.data.ui.domain.datamappers.toPuntoEntity
import javax.inject.Inject

class AddPuntos @Inject constructor(private val puntosRepository: RepositoryPuntos){
    suspend fun addPuntos(list: List<Punto>) = puntosRepository.addPuntos(list.map { it.toPuntoEntity() })
}