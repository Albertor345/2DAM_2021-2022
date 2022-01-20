package com.example.seriespelisretrofit.ui.model

data class FavoritoUI(
    val id: Int,
    val tipo: String,
    var selected: Boolean,
    val imagePath: String?,
    val name: String?
)
