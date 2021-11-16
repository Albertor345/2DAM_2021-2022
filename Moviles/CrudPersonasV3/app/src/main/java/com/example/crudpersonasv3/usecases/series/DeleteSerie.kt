package com.example.crudpersonasv3.usecases.series

import com.example.crudpersonasv3.data.repositories.series.RepositorySeries
import com.example.crudpersonasv3.ui.domain.SerieUI
import com.example.crudpersonasv3.ui.domain.datamappers.toSerieEntity
import javax.inject.Inject

class DeleteSerie @Inject constructor(private val repositorySeries: RepositorySeries) {
    suspend fun invoke(serieUI: SerieUI) = repositorySeries.detele(serieUI.toSerieEntity())
}