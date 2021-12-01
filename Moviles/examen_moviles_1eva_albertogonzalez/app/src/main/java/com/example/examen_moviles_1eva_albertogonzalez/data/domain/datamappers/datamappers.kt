package com.example.examen_moviles_1eva_albertogonzalez.data.domain.datamappers

import com.example.examen_moviles_1eva_albertogonzalez.data.domain.MensajeEntity
import com.example.examen_moviles_1eva_albertogonzalez.data.domain.PuntoEntity
import com.example.examen_moviles_1eva_albertogonzalez.data.domain.PuntoWithMensajes
import com.example.examen_moviles_1eva_albertogonzalez.data.ui.domain.Mensaje
import com.example.examen_moviles_1eva_albertogonzalez.data.ui.domain.Punto

fun MensajeEntity.toMensaje(): Mensaje =
    Mensaje(id, autor, mensaje, punto_id)

fun PuntoEntity.toPunto(): Punto =
    Punto(id, longitud, latitud, emptyList())

fun PuntoWithMensajes.toPunto(): Punto {
    val punto = punto.toPunto()
    punto.mensajes = mensajes.map { it.toMensaje() }
    return punto
}

