package com.example.crudpersonasv3.usecases.series

import com.example.crudpersonasv3.data.domain.CharacterEntity
import com.example.crudpersonasv3.data.repositories.series.RepositorySeries
import com.example.crudpersonasv3.ui.domain.CharacterUI
import com.example.crudpersonasv3.ui.domain.SerieUI
import com.example.crudpersonasv3.ui.domain.datamappers.toCharacterEntity
import com.example.crudpersonasv3.ui.domain.datamappers.toSerieEntity
import javax.inject.Inject

class InsertSerie @Inject constructor(private val repositorySeries: RepositorySeries) {
    suspend fun invoke(serieUI: SerieUI, characterUI: CharacterUI): Long = repositorySeries.insert(serieUI.toSerieEntity(), characterUI.toCharacterEntity())
}