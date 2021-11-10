package com.example.crudpersonasv3.usecases.characters

import com.example.crudpersonasv3.ui.domain.CharacterUI

class InsertCharacter {
    suspend fun invoke(character: CharacterUI) = personasRepository.insertPersona(persona.toPersonaEntity())
}