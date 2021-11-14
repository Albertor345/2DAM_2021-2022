package com.example.crudpersonasv3.usecases.characters

import com.example.crudpersonasv3.data.domain.CharacterEntity
import com.example.crudpersonasv3.data.repositories.characters.RepositoryCharacters
import com.example.crudpersonasv3.ui.domain.CharacterUI
import com.example.crudpersonasv3.ui.domain.datamappers.toCharacterEntity
import javax.inject.Inject

class DeleteCharacter @Inject constructor(private val repositoryCharacters: RepositoryCharacters) {
    suspend fun invoke(character: CharacterUI) =
        repositoryCharacters.deleteCharacter(character.toCharacterEntity())
}