package alberto.gonzalez.crudpersonasv2.data

import alberto.gonzalez.crudpersonasv2.config.ConfigMoshi
import alberto.gonzalez.crudpersonasv2.domain.Character
import alberto.gonzalez.crudpersonasv2.domain.CharacterDataWrapper
import java.io.InputStream
import java.util.stream.Collectors

class Repository {
    private val moshi = ConfigMoshi.getInstance()

    companion object {
        private lateinit var list: MutableList<Character>
        private lateinit var fullList: List<Character>
    }

    constructor(file: InputStream) {
        list = mutableListOf()
        if (list.isEmpty()) {
            fullList = loadData(file)
            list.addAll(fullList)
        }
    }

    constructor()

    private fun loadData(file: InputStream): MutableList<Character> {
        val data = moshi?.adapter(CharacterDataWrapper::class.java)
            ?.fromJson(file.bufferedReader().readText())!!

        return data.data.characters

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

    fun filterNameStartsWith(s: String): MutableList<Character> {
        return if (s.isNotEmpty() && s.isNotBlank()) {
            list.stream().filter {
                it.name.startsWith(s)
            }.collect(Collectors.toList())
        } else {
            fullList as MutableList<Character>
        }
    }


}

