package com.example.crudpersonasv3.data.repositories.comics

import com.example.crudpersonasv3.data.domain.CharacterEntity
import com.example.crudpersonasv3.data.domain.ComicEntity
import javax.inject.Inject

class RepositoryComics @Inject constructor(private val daoComics: DaoComics) {

    suspend fun detele(comicEntity: ComicEntity): Int = daoComics.delete(comicEntity)

    suspend fun insert(comicEntity: ComicEntity, characterEntity: CharacterEntity): Long = daoComics.insertComicFull(comicEntity, characterEntity)


}

