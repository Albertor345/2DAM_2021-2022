package alberto.gonzalez.crudpersonasv2.data

import alberto.gonzalez.crudpersonasv2.config.ConfigMoshi
import alberto.gonzalez.crudpersonasv2.domain.Character
import alberto.gonzalez.crudpersonasv2.domain.CharacterDataWrapper
import java.io.InputStream

class Dao {
    private val moshi = ConfigMoshi.getInstance()

    companion object {
        private lateinit var list: MutableList<Character>
    }

    constructor(file: InputStream) {
        list = mutableListOf()
        if (list.isEmpty()) {
            list = loadData(file)
        }
    }

    constructor()

    private fun loadData(file: InputStream): MutableList<Character> {
        val data = moshi?.adapter(CharacterDataWrapper::class.java)
            ?.fromJson(file.bufferedReader().readText())!!

        return data.data.characters

    }

    fun getLista(): MutableList<Character> {
        return list
    }

    fun getCharacter(index: Int): Character {
        return list[index]
    }

    fun addCharacter(character: Character) {
        if (!list.contains(character)) {
            list.add(character)
        }
    }

    fun removeCharacter(character: Character) {
        if (list.contains(character)) list.remove(character)

    }


}

