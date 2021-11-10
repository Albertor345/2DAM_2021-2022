package alberto.gonzalez.crudpersonasv2.data

import alberto.gonzalez.crudpersonasv2.config.ConfigMoshi
import alberto.gonzalez.crudpersonasv2.domain.CharacterDataWrapper
import alberto.gonzalez.crudpersonasv2.domain.Character
import java.io.InputStream
import java.time.LocalDate
import java.util.*
import java.util.stream.Collectors

class Repository {
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

        return data.characterDataContainer.characters

    }

    fun getList(): MutableList<Character> {
        return list
    }

    fun getCharacter(index: Int): Character {
        return list[index]
    }

    fun addCharacter(character: Character, index: Int) {
        list.add(index, character)
    }

    fun removeCharacter(index: Int) {
        list.removeAt(index)

    }

}

