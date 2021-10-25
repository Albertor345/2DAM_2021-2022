package alberto.gonzalez.crudpersonasv2.services

import alberto.gonzalez.crudpersonasv2.data.Dao
import alberto.gonzalez.crudpersonasv2.domain.Character
import java.io.InputStream

class Services {

    fun getListaCharacters(file: InputStream? = null): List<Character>? {
        var dao = file?.let { Dao(it) }
        return dao?.getLista()
    }
}