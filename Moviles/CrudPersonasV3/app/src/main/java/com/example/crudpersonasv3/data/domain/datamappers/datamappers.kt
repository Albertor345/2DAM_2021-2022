package com.example.crudpersonasv3.data.domain.datamappers

import com.example.crudpersonasv3.data.domain.CharacterEntity
import com.example.crudpersonasv3.data.domain.CharacterFull
import com.example.crudpersonasv3.data.domain.ComicEntity
import com.example.crudpersonasv3.data.domain.SerieEntity
import com.example.crudpersonasv3.ui.domain.CharacterUI
import com.example.crudpersonasv3.ui.domain.ComicUI
import com.example.crudpersonasv3.ui.domain.SerieUI

fun CharacterEntity.toCharacterUI(): CharacterUI =
    CharacterUI(
        id_character,
        name,
        description,
        modified,
        image,
        comics = emptyList(),
        series = emptyList()
    )

fun ComicEntity.toComicUI(): ComicUI = ComicUI(id_comic, name)

fun SerieEntity.toSerieUI(): SerieUI = SerieUI(id_serie, name)

fun CharacterFull.toCharacterUI(): CharacterUI {
    var character = character.toCharacterUI()
    character.comics = comics.map { it.toComicUI() }
    character.series = series.map { it.toSerieUI() }
    return character
}