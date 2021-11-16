package com.example.crudpersonasv3.usecases.comics

import com.example.crudpersonasv3.data.repositories.comics.RepositoryComics
import com.example.crudpersonasv3.ui.domain.ComicUI
import com.example.crudpersonasv3.ui.domain.datamappers.toComicEntity
import javax.inject.Inject

class DeleteComic @Inject constructor(private val repositoryComics: RepositoryComics) {
    suspend fun invoke(comicUI: ComicUI) = repositoryComics.detele(comicUI.toComicEntity())
}