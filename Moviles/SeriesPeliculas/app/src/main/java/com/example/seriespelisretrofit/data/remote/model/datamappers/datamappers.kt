package com.example.seriespelisretrofit.data.remote.model.datamappers

import com.example.seriespelisretrofit.data.remote.model.SearchResult
import com.example.seriespelisretrofit.ui.model.PeliculaUI

fun SearchResult.toListPeliculasUI(): List<PeliculaUI> = results.map { it.toPeliculaUI() }