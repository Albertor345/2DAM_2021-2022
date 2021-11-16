package com.example.crudpersonasv3.usecases.comics

import com.example.crudpersonasv3.data.repositories.comics.RepositoryComics
import com.example.crudpersonasv3.ui.domain.CharacterUI
import com.example.crudpersonasv3.ui.domain.ComicUI
import com.example.crudpersonasv3.ui.domain.datamappers.toCharacterEntity
import com.example.crudpersonasv3.ui.domain.datamappers.toComicEntity
import javax.inject.Inject

class InsertComic @Inject constructor(private val repositoryComics: RepositoryComics) {
    suspend fun invoke(comicUI: ComicUI, characterUI: CharacterUI): Long =
        repositoryComics.insert(comicUI.toComicEntity(), characterUI.toCharacterEntity())
}