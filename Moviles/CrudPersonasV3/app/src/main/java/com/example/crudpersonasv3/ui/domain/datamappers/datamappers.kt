package com.example.crudpersonasv3.ui.domain.datamappers

import com.example.crudpersonasv3.data.domain.*
import com.example.crudpersonasv3.ui.domain.CharacterUI
import com.example.crudpersonasv3.ui.domain.ComicUI
import com.example.crudpersonasv3.ui.domain.SerieUI

fun CharacterUI.toCharacterEntity(): CharacterEntity =
    CharacterEntity(id, name, description, modified, image)

fun ComicUI.toComicEntity(): ComicEntity =
    ComicEntity(idComic = -1, name, resourceURI, idCharacter = -1)

fun SerieUI.toSerieEntity(): SerieEntity =
    SerieEntity(idSerie = -1, name, resourceURI, id_character = -1)

fun CharacterUI.toCharacterFull(): CharacterFull {
    val charWithComics = CharacterWithComics(this.toCharacterEntity(), comics.map { it.toComicEntity() })
    return CharacterFull(charWithComics, series.map { it.toSerieEntity() })
}