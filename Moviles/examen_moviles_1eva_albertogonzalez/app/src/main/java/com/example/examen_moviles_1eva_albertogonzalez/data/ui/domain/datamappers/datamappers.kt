package com.example.examen_moviles_1eva_albertogonzalez.data.ui.domain.datamappers

import com.example.examen_moviles_1eva_albertogonzalez.data.domain.MensajeEntity
import com.example.examen_moviles_1eva_albertogonzalez.data.domain.PuntoEntity
import com.example.examen_moviles_1eva_albertogonzalez.data.ui.domain.Mensaje
import com.example.examen_moviles_1eva_albertogonzalez.data.ui.domain.Punto

fun Mensaje.toMensajeEntity(): MensajeEntity = MensajeEntity(id, autor, mensaje, punto_id)

fun Punto.toPuntoEntity(): PuntoEntity = PuntoEntity(id, longitud, latitud)
