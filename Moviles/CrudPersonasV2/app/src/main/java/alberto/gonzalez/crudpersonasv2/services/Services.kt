package alberto.gonzalez.crudpersonasv2.services

import alberto.gonzalez.crudpersonasv2.data.Dao
import alberto.gonzalez.crudpersonasv2.domain.Character
import java.io.InputStream

class Services {

    fun getListaCharacters(file: InputStream? = null): MutableList<Character>? {
        var dao = file?.let { Dao(it) }
        return dao?.getLista()
    }

    fun getCharacter(index: Int): Character {
        var dao = Dao()
        return dao.getCharacter(index)
    }

    fun addCharacter(character: Character) {
        var dao = Dao()
        return dao.addCharacter(character)
    }

    fun removeCharacter(character: Character){
        var dao = Dao()
        return dao.removeCharacter(character)
    }
}