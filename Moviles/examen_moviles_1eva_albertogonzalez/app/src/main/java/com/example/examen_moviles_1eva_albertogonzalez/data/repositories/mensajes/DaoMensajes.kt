package com.example.examen_moviles_1eva_albertogonzalez.data.repositories.mensajes

import androidx.room.*
import com.example.examen_moviles_1eva_albertogonzalez.data.domain.MensajeEntity
import com.example.examen_moviles_1eva_albertogonzalez.data.domain.PuntoEntity
import com.example.examen_moviles_1eva_albertogonzalez.data.domain.PuntoWithMensajes

@Dao
interface DaoMensajes {

    @Query("Select * from mensajes")
    suspend fun getMensajes(): MutableList<MensajeEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addMensaje(mensaje: MensajeEntity): Long

    @Query("select * from mensajes where id = :id")
    suspend fun getMensaje(id: Long): MensajeEntity

    @Delete
    suspend fun deleteMensaje(mensaje: MensajeEntity): Int

    @Query("select * from puntos where id = :id")
    suspend fun getPuntoWithMensajes(id: Long): PuntoWithMensajes


}