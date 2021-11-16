package com.example.crudpersonasv3.data.repositories.characters

import androidx.room.*
import com.example.crudpersonasv3.data.domain.CharacterEntity
import com.example.crudpersonasv3.data.domain.CharacterFull
import com.example.crudpersonasv3.data.domain.ComicEntity
import com.example.crudpersonasv3.data.domain.SerieEntity

@Dao
interface DaoCharacters {

    @Query("Select * from characters")
    suspend fun getCharacters(): MutableList<CharacterFull>

    @Transaction
    @Query("Select * from characters where id_character = :id")
    suspend fun getCharacterFull(id: Long): CharacterFull

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCharacter(character: CharacterEntity): Long

    @Delete
    suspend fun deleteCharacter(character: CharacterEntity): Int

    @Delete
    suspend fun deleteComicList(comicList: List<ComicEntity>): Int

    @Delete
    suspend fun deleteSerieList(comicList: List<SerieEntity>): Int

    @Transaction
    suspend fun deleteCharacterFull(character: CharacterEntity) {
        val characterFull: CharacterFull = getCharacterFull(character.id_character!!)
        deleteComicList(characterFull.comics)
        deleteSerieList(characterFull.series)
        deleteCharacter(characterFull.character)
    }


}