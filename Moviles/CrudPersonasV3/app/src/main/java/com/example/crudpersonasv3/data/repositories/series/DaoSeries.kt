package com.example.crudpersonasv3.data.repositories.series

import androidx.room.*
import com.example.crudpersonasv3.data.domain.CharacterEntity
import com.example.crudpersonasv3.data.domain.CharacterSeriesCrossReference
import com.example.crudpersonasv3.data.domain.SerieEntity

@Dao
interface DaoSeries {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSerie(serieEntity: SerieEntity): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCharacterSerie(characterSeriesCrossReference: CharacterSeriesCrossReference)

    @Delete
    suspend fun delete(serieEntity: SerieEntity): Int

    @Transaction
    suspend fun insertSerieFull(serieEntity: SerieEntity, characterEntity: CharacterEntity): Long {
        val serieID = insertSerie(serieEntity)
        insertCharacterSerie(
            CharacterSeriesCrossReference(
                characterEntity.id_character!!,
                serieID
            )
        )
        return serieID
    }
}