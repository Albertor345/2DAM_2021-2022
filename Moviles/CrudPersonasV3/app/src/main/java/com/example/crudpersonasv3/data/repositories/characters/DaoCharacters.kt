package com.example.crudpersonasv3.data.repositories.characters

import androidx.room.*
import com.example.crudpersonasv3.R
import com.example.crudpersonasv3.data.domain.CharacterEntity
import com.example.crudpersonasv3.data.domain.CharacterFull
import com.example.crudpersonasv3.data.domain.CharacterWithComics

@Dao
interface DaoCharacters {

    @Query(R.string.SELECT_ALL_CHARACTERS.toString())
    suspend fun getCharacters(): List<CharacterEntity>

    @Transaction
    @Query(R.string.SELECT_CHARACTER.toString())
    suspend fun getCharacterFull(id: Int): CharacterFull

    @Insert
    suspend fun insertCharacter(character: CharacterEntity): Int

    @Delete
    suspend fun deleteCharacter(id: Int): Int



}