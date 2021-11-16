package com.example.crudpersonasv3.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.crudpersonasv3.data.domain.*
import com.example.crudpersonasv3.data.repositories.characters.DaoCharacters
import com.example.crudpersonasv3.data.repositories.comics.DaoComics
import com.example.crudpersonasv3.data.repositories.series.DaoSeries
import com.example.crudpersonasv3.data.utils.Converters

@Database(
    entities = [CharacterEntity::class, ComicEntity::class, SerieEntity::class, CharacterSeriesCrossReference::class, CharacterComicsCrossReference::class],
    version = 17,
    exportSchema = true
)
@TypeConverters(Converters::class)
abstract class RoomDatabaseConfig : RoomDatabase() {

    abstract fun daoCharacters(): DaoCharacters
    abstract fun daoComics(): DaoComics
    abstract fun daoSeries(): DaoSeries
}