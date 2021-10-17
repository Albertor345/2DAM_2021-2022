package alberto.gonzalez.pantallacrudpersonas.data

import alberto.gonzalez.pantallacrudpersonas.domain.Persona

class DaoPersonas {
    companion object {
        private val lista = mutableListOf<Persona>()
    }

    constructor() {
        if (lista.size == 0) {
            lista.add(Persona(0, "Alberto", 23, "Hombre"))
            lista.add(Persona(1, "Esmeralda", 24, "Mujer"))
            lista.add(Persona(2, "Oscar", 18, "Hombre"))
            lista.add(Persona(3, "Estela", 21, "Mujer"))
            lista.add(Persona(4, "Cristian", 24, "Hombre"))
            lista.add(Persona(5, "Taiseta", 12, "Mujer"))
            lista.add(Persona(0, "Pelusete", 15, "Hombre"))
            lista.add(Persona(1, "Pilar", 56, "Mujer"))
            lista.add(Persona(2, "Irene", 27, "Mujer"))
            lista.add(Persona(3, "Rosa", 21, "Mujer"))
            lista.add(Persona(4, "Carlos", 48, "Hombre"))
            lista.add(Persona(5, "Freya", 8, "Mujer"))
        }
    }

    fun getLista() : List<Persona>{
        return lista
    }


}