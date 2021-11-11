package com.example.crudpersonasv3.data.domain.datamappers

import com.example.crudpersonasv3.data.domain.CharacterEntity
import com.example.crudpersonasv3.data.domain.CharacterFull
import com.example.crudpersonasv3.data.domain.ComicEntity
import com.example.crudpersonasv3.data.domain.SerieEntity
import com.example.crudpersonasv3.ui.domain.CharacterUI
import com.example.crudpersonasv3.ui.domain.ComicUI
import com.example.crudpersonasv3.ui.domain.SerieUI

fun CharacterEntity.toCharacterUI(): CharacterUI =
    CharacterUI(id, name, description, modified, image, comics = emptyList(), series = emptyList())

fun ComicEntity.toComicUI(): ComicUI = ComicUI(name, resourceURI)

fun SerieEntity.toSerieUI(): SerieUI = SerieUI(name, resourceURI)

fun CharacterFull.toCharacterUI(): CharacterUI {
    val char = this.characterWithComics.character.toCharacterUI()
    char.series = series.map { it.toSerieUI() }
    char.comics = characterWithComics.comics.map { it.toComicUI() }
    return char
}