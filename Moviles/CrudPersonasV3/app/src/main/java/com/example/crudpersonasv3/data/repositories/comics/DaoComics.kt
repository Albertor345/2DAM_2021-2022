package com.example.crudpersonasv3.data.repositories.comics

import androidx.room.*
import com.example.crudpersonasv3.data.domain.CharacterComicsCrossReference
import com.example.crudpersonasv3.data.domain.CharacterEntity
import com.example.crudpersonasv3.data.domain.ComicEntity

@Dao
interface DaoComics {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertComic(comicEntity: ComicEntity): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCharacterComic(characterComicsCrossReference: CharacterComicsCrossReference)

    @Delete
    suspend fun delete(comicEntity: ComicEntity): Int

    @Transaction
    suspend fun insertComicFull(comicEntity: ComicEntity, characterEntity: CharacterEntity): Long {
        val comicID = insertComic(comicEntity)
        insertCharacterComic(
            CharacterComicsCrossReference(
                characterEntity.id_character!!,
                comicID
            )
        )
        return comicID
    }
}