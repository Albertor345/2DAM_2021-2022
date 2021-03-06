package com.example.crudpersonasv3.ui.domain.datamappers

import com.example.crudpersonasv3.data.domain.CharacterEntity
import com.example.crudpersonasv3.data.domain.CharacterFull
import com.example.crudpersonasv3.data.domain.ComicEntity
import com.example.crudpersonasv3.data.domain.SerieEntity
import com.example.crudpersonasv3.ui.domain.CharacterUI
import com.example.crudpersonasv3.ui.domain.ComicUI
import com.example.crudpersonasv3.ui.domain.SerieUI

fun CharacterUI.toCharacterEntity(): CharacterEntity =
    CharacterEntity(id, name, description, modified, image)

fun ComicUI.toComicEntity(): ComicEntity =
    ComicEntity(id, name)

fun SerieUI.toSerieEntity(): SerieEntity =
    SerieEntity(id, name)

fun CharacterUI.toCharacterFull(): CharacterFull {
    return CharacterFull(
        this.toCharacterEntity(),
        series.map { it.toSerieEntity() },
        comics.map { it.toComicEntity() })
}