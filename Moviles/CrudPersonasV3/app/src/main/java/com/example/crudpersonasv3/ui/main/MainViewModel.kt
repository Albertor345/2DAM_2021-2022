package com.example.crudpersonasv3.ui.main

import androidx.lifecycle.*
import com.example.crudpersonasv3.ui.domain.CharacterUI
import com.example.crudpersonasv3.usecases.characters.DeleteCharacter
import com.example.crudpersonasv3.usecases.characters.GetCharacters
import com.example.crudpersonasv3.usecases.characters.InsertCharacter
import kotlinx.coroutines.launch

class MainViewModel(
    private val getCharacters: GetCharacters,
    private val insertCharacters: InsertCharacter,
    private val deleteCharacter: DeleteCharacter
) : ViewModel() {

    private val _characters = MutableLiveData<MutableList<CharacterUI>>()
    val characters: LiveData<List<CharacterUI>> get() = _characters as LiveData<List<CharacterUI>>

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun deleteCharacter(id: Int) {
        viewModelScope.launch {
            deleteCharacter.invoke(id)
            _characters.value?.remove(
                CharacterUI(
                    id,
                    name = "",
                    description = "",
                    modified = "",
                    image = "",
                    comics = emptyList(),
                    series = emptyList()
                )
            )
        }
    }

    fun addCharacter(character: CharacterUI) {
        viewModelScope.launch {
            insertCharacters.invoke(character)
            _characters.value?.add(character)
        }
    }

    fun getCharacters(){
        viewModelScope.launch {
            _characters.value = getCharacters.getCharacters()
        }
    }

}


class MainViewModelFactory(
    private val getCharacters: GetCharacters,
    private val insertCharacters: InsertCharacter,
    private val deleteCharacter: DeleteCharacter
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(
                getCharacters,
                insertCharacters,
                deleteCharacter
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
