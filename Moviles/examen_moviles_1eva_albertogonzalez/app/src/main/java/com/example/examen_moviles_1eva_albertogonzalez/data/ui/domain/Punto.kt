package com.example.examen_moviles_1eva_albertogonzalez.data.ui.domain

data class Punto(
    val id: Long?,
    val longitud: Double,
    val latitud: Double,
    var mensajes: List<Mensaje>
)