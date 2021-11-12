package com.example.crudpersonasv3.usecases.characters

import com.example.crudpersonasv3.data.domain.datamappers.toCharacterUI
import com.example.crudpersonasv3.data.repositories.characters.RepositoryCharacters

class GetCharacters(val repositoryCharacters: RepositoryCharacters) {

    suspend fun getCharacters() = repositoryCharacters.getCharacters().map { it.toCharacterUI() }

    suspend fun getCharacterFull(id: Int) =
        repositoryCharacters.getCharacterFull(id).toCharacterUI()
}
