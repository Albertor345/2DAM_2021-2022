package com.example.crudpersonasv3.data.repositories.series

import com.example.crudpersonasv3.data.domain.CharacterEntity
import com.example.crudpersonasv3.data.domain.SerieEntity
import javax.inject.Inject

class RepositorySeries @Inject constructor(private val daoSeries: DaoSeries) {

    suspend fun detele(serieEntity: SerieEntity): Int = daoSeries.delete(serieEntity)

    suspend fun insert(serieEntity: SerieEntity, characterEntity: CharacterEntity): Long = daoSeries.insertSerieFull(serieEntity, characterEntity)
}

