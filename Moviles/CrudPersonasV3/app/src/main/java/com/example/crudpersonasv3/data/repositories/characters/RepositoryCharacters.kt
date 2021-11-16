package com.example.crudpersonasv3.data.repositories.characters

import com.example.crudpersonasv3.data.domain.CharacterEntity
import com.example.crudpersonasv3.data.domain.CharacterFull
import javax.inject.Inject

class RepositoryCharacters @Inject constructor(private val daoCharacters: DaoCharacters) {

    suspend fun insertCharacter(character: CharacterEntity) =
        daoCharacters.insertCharacter(character)

    suspend fun getCharacters() = daoCharacters.getCharacters()

    suspend fun getCharacterFull(id: Long) = daoCharacters.getCharacterFull(id)

    suspend fun deleteCharacter(character: CharacterEntity) =
        daoCharacters.deleteCharacterFull(character)

}

