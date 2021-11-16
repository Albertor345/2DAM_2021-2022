package com.example.crudpersonasv3.usecases.characters

import com.example.crudpersonasv3.data.domain.datamappers.toCharacterUI
import com.example.crudpersonasv3.data.repositories.characters.RepositoryCharacters
import javax.inject.Inject

class GetCharacters @Inject constructor(private val repositoryCharacters: RepositoryCharacters) {

    suspend fun getCharacters() =
        repositoryCharacters.getCharacters().map { it.toCharacterUI() }.toMutableList()

    suspend fun getCharacterFull(id: Long) =
        repositoryCharacters.getCharacterFull(id).toCharacterUI()
}
