package com.example.examen_moviles_1eva_albertogonzalez.data.usecases.puntos

import com.example.examen_moviles_1eva_albertogonzalez.data.domain.datamappers.toPunto
import com.example.examen_moviles_1eva_albertogonzalez.data.repositories.puntos.RepositoryPuntos
import com.example.examen_moviles_1eva_albertogonzalez.data.ui.domain.Punto
import javax.inject.Inject

class GetPuntos @Inject constructor(private val repositoryPuntos: RepositoryPuntos) {
    suspend fun getPuntos(): MutableList<Punto> =
        repositoryPuntos.getPuntos().map { it.toPunto() }.toMutableList()
}