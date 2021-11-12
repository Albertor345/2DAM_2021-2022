package com.example.crudpersonasv3.data.repositories.characters

import com.example.crudpersonasv3.data.domain.CharacterEntity

class RepositoryCharacters(private val daoCharacters: DaoCharacters)  {

    suspend fun insertCharacter(character: CharacterEntity) = daoCharacters.insertCharacter(character)

    suspend fun getCharacters() = daoCharacters.getCharacters()

    suspend fun getCharacterFull(id: Int) = daoCharacters.getCharacterFull(id)

    suspend fun deleteCharacter(id: Int) = daoCharacters.deleteCharacter(id)

}

