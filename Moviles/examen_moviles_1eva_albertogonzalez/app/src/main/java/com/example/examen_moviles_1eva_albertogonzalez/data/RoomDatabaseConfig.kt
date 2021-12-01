package com.example.examen_moviles_1eva_albertogonzalez.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.crudpersonasv3.data.utils.Converters
import com.example.examen_moviles_1eva_albertogonzalez.data.domain.MensajeEntity
import com.example.examen_moviles_1eva_albertogonzalez.data.domain.PuntoEntity
import com.example.examen_moviles_1eva_albertogonzalez.data.repositories.mensajes.DaoMensajes
import com.example.examen_moviles_1eva_albertogonzalez.data.repositories.puntos.DaoPuntos

@Database(
    entities = [MensajeEntity::class, PuntoEntity::class],
    version = 4,
    exportSchema = true
)
@TypeConverters(Converters::class)
abstract class RoomDatabaseConfig : RoomDatabase() {

    abstract fun daoPuntos(): DaoPuntos
    abstract fun daoMensajes(): DaoMensajes
}