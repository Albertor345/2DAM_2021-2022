package alberto.gonzalez.crudpersonasv2.data

import alberto.gonzalez.crudpersonasv2.config.ConfigMoshi
import alberto.gonzalez.crudpersonasv2.domain.CharacterDataWrapper
import java.io.InputStream
import java.time.LocalDate
import java.util.*
import java.util.stream.Collectors

class Repository {
    private val moshi = ConfigMoshi.getInstance()

    companion object {
        private lateinit var list: MutableList<Character>
        private lateinit var initList: List<Character>
    }

    constructor(file: InputStream) {
        list = mutableListOf()
        if (list.isEmpty()) {
            initList = loadData(file)
            list.addAll(initList)
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

    fun filterNameStartsWith(s: String): MutableList<Character> {
        return if (s.isNotEmpty() && s.isNotBlank()) {
            list.stream().filter {
                it.name.lowercase().startsWith(s.lowercase())
            }.collect(Collectors.toList())
        } else {
            initList as MutableList<Character>
        }
    }

    fun filterDateRange(date: Date): MutableList<Character> {
        return list.stream().filter {
            LocalDate.parse(it.modified).isBefore()
        }.collect(Collectors.toList())
            ?: initList as MutableList<Character>
    }

}

