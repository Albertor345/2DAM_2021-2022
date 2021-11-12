package com.example.crudpersonasv3.usecases.characters

import com.example.crudpersonasv3.data.repositories.characters.RepositoryCharacters

class DeleteCharacter(val repositoryCharacters: RepositoryCharacters) {
    suspend fun invoke(id: Int) =
        repositoryCharacters.deleteCharacter(id)
}