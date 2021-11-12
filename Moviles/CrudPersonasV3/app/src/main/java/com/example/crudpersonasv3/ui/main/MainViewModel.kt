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

    private val _characters = MutableLiveData<List<CharacterUI>>()
    val characters: LiveData<List<CharacterUI>> get() = _characters

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun removeCharacter(index: Int): Int{
        viewModelScope.launch {
            deleteCharacter.repositoryCharacters.deleteCharacter(index)
        }
    }

    private fun addItem(character: CharacterUI?, index: Int) {
        viewModelScope.launch {

        }

        character?.let {
            repository.addCharacter(character, index)
        } ?: showDialog()

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
