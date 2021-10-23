package alberto.gonzalez.crudpersonasv2.data

import alberto.gonzalez.crudpersonasv2.config.ConfigMoshi
import alberto.gonzalez.crudpersonasv2.domain.CharacterDataWrapper
import java.io.InputStream

class DaoPersonas {
    private val moshi = ConfigMoshi.getInstance()

    companion object {
        private val list = List<Character>()
    }

    constructor(file: InputStream) {
        if (list.isEmpty()) {
            loadData(file)
        }
    }

    private fun loadData(file: InputStream?) {
        val data = moshi.adapter(CharacterDataWrapper::class.java)
            .fromJson(file?.bufferedReader()?.readText())!!

        list.addAll(data.data.characters)

    }

    fun getLista(): List<Character> {
        return list
    }


}