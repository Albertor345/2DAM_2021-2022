package com.example.logincompose.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.logincompose.data.local.model.CapituloEntity
import com.example.logincompose.data.local.model.PeliculaEntity
import com.example.logincompose.data.local.model.SerieEntity
import com.example.logincompose.data.local.model.TemporadaEntity
import com.example.logincompose.data.local.repositories.favoritos.DaoLocal
import com.example.logincompose.utils.Converters

@Database(
    entities = [SerieEntity::class, CapituloEntity::class, PeliculaEntity::class, TemporadaEntity::class],
    version = 2,
    exportSchema = true
)
@TypeConverters(Converters::class)
abstract class RoomDatabaseConfig : RoomDatabase() {
    abstract fun daoFavoritos(): DaoLocal

}