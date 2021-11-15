package com.example.crudpersonasv3.data.repositories.characters

import androidx.room.*
import com.example.crudpersonasv3.R
import com.example.crudpersonasv3.data.domain.CharacterEntity
import com.example.crudpersonasv3.data.domain.CharacterFull
import com.example.crudpersonasv3.data.domain.CharacterWithComics

@Dao
interface DaoCharacters {

    @Query("SELECT * from characters")
    suspend fun getCharacters(): MutableList<CharacterEntity>

    @Transaction
    @Query("Select * from characters where id_character = :id")
    suspend fun getCharacterFull(id: Long): CharacterFull

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCharacter(character: CharacterEntity): Long

    @Delete
    suspend fun deleteCharacter(character: CharacterEntity): Int



}