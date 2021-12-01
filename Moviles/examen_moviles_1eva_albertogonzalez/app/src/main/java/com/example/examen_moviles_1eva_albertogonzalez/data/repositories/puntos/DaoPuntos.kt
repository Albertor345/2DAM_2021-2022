package com.example.examen_moviles_1eva_albertogonzalez.data.repositories.puntos

import androidx.room.*
import com.example.examen_moviles_1eva_albertogonzalez.data.domain.PuntoEntity

@Dao
interface DaoPuntos {

    @Query("Select * from puntos")
    suspend fun getPuntos(): MutableList<PuntoEntity>

    @Query("Select * from puntos where id = :id")
    suspend fun getPunto(id: Long): PuntoEntity

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addPunto(punto: PuntoEntity): Long

    @Transaction
    suspend fun addPuntos(list: List<PuntoEntity>) {
        list.forEach { addPunto(it) }
    }


}