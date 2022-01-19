package com.example.seriespelisretrofit.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.seriespelisretrofit.data.local.model.CapituloEntity
import com.example.seriespelisretrofit.data.local.model.PeliculaEntity
import com.example.seriespelisretrofit.data.local.model.SerieEntity
import com.example.seriespelisretrofit.data.local.model.TemporadaEntity
import com.example.seriespelisretrofit.data.local.repositories.favoritos.DaoFavoritos
import com.example.seriespelisretrofit.utils.Converters

@Database(
    entities = [SerieEntity::class, CapituloEntity::class, PeliculaEntity::class, TemporadaEntity::class],
    version = 1,
    exportSchema = true
)
@TypeConverters(Converters::class)
abstract class RoomDatabaseConfig : RoomDatabase() {
    abstract fun daoFavoritos(): DaoFavoritos

}