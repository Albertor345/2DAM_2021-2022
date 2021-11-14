package com.example.crudpersonasv3.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.crudpersonasv3.R
import com.example.crudpersonasv3.data.domain.*
import com.example.crudpersonasv3.data.repositories.characters.DaoCharacters
import com.example.crudpersonasv3.data.repositories.comics.daoComics
import com.example.crudpersonasv3.data.repositories.series.daoSeries
import com.example.crudpersonasv3.data.utils.Converters

@Database(
    entities = [CharacterEntity::class, ComicEntity::class, SerieEntity::class, CharacterSeriesCrossReference::class, CharacterComicsCrossReference::class],
    version = 13,
    exportSchema = true
)
@TypeConverters(Converters::class)
abstract class RoomDatabaseConfig : RoomDatabase() {

    abstract fun daoCharacters(): DaoCharacters
    abstract fun daoComics(): daoComics
    abstract fun daoEntities(): daoSeries

    companion object {
        @Volatile
        private var INSTANCE: RoomDatabaseConfig? = null

        fun getDatabase(context: Context): RoomDatabaseConfig {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RoomDatabaseConfig::class.java,
                    "item_database"
                )
                    /*.createFromAsset("database/db.db")*/
                    .fallbackToDestructiveMigrationFrom(12)
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}