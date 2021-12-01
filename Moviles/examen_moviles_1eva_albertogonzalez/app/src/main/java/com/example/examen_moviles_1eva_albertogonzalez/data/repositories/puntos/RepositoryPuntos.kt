package com.example.examen_moviles_1eva_albertogonzalez.data.repositories.puntos

import com.example.examen_moviles_1eva_albertogonzalez.data.domain.PuntoEntity
import com.example.examen_moviles_1eva_albertogonzalez.data.ui.domain.Punto
import javax.inject.Inject

class RepositoryPuntos @Inject constructor(private val daoPuntos: DaoPuntos) {

    suspend fun getPuntos() = daoPuntos.getPuntos()

    suspend fun addPunto(punto: PuntoEntity) =
        daoPuntos.addPunto(punto)

    suspend fun getPunto(id: Long): PuntoEntity = daoPuntos.getPunto(id)

    suspend fun addPuntos(list: List<PuntoEntity>) = daoPuntos.addPuntos(list)

}

