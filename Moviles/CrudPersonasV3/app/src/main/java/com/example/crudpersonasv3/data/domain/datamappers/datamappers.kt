package com.example.crudpersonasv3.data.utils

import com.example.crudpersonasv3.data.domain.CharacterEntity
import com.example.crudpersonasv3.data.domain.CharacterFull
import com.example.crudpersonasv3.data.domain.ComicEntity
import com.example.crudpersonasv3.data.domain.SerieEntity
import com.example.crudpersonasv3.ui.domain.CharacterUI
import com.example.crudpersonasv3.ui.domain.ComicUI
import com.example.crudpersonasv3.ui.domain.SerieUI

fun CharacterEntity.toCharacterUI(): CharacterUI =
    CharacterUI(id_character, name, description, modified, image)

fun ComicEntity.toComicUI(): ComicUI = ComicUI(name, resourceURI)

fun SerieEntity.toSerieUI(): SerieUI = SerieUI(name, resourceURI)

fun CharacterFull.toCharacterUI(): CharacterUI {
    return CharacterUI(
        characterWithComics.character.id_character,
        characterWithComics.character.name,
        characterWithComics.character.description,
        characterWithComics.character.modified,
        characterWithComics.character.image,
        characterWithComics.comics.map {
            it.toComicUI()
        },
        series.map {
            it.toSerieUI()
        }
    )
}