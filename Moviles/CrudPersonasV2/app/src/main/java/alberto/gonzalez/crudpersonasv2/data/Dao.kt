package alberto.gonzalez.crudpersonasv2.data

import alberto.gonzalez.crudpersonasv2.config.ConfigMoshi
import alberto.gonzalez.crudpersonasv2.domain.Character
import alberto.gonzalez.crudpersonasv2.domain.CharacterDataWrapper
import java.io.InputStream

class Dao {
    private val moshi = ConfigMoshi.getInstance()

    companion object {
        private lateinit var list: List<Character>
    }

    constructor(file: InputStream) {
        if (list.isEmpty()) {
            list = loadData(file)
        }
    }

    private fun loadData(file: InputStream): List<Character> {
        val data = moshi.adapter(CharacterDataWrapper::class.java)
            .fromJson(file.bufferedReader().readText())!!

        return data.data.characters

    }

    fun getLista(): List<Character> {
        return list
    }


}

