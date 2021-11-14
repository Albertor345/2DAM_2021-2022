package com.example.crudpersonasv3.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crudpersonasv3.ui.domain.CharacterUI
import com.example.crudpersonasv3.usecases.characters.DeleteCharacter
import com.example.crudpersonasv3.usecases.characters.GetCharacters
import com.example.crudpersonasv3.usecases.characters.InsertCharacter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getCharacters: GetCharacters,
    private val insertCharacters: InsertCharacter,
    private val deleteCharacter: DeleteCharacter
) : ViewModel() {

    private val _characters = MutableLiveData<MutableList<CharacterUI>>()
    val characters: LiveData<List<CharacterUI>> get() = _characters as LiveData<List<CharacterUI>>

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun deleteCharacter(character: CharacterUI) {
        viewModelScope.launch {
            deleteCharacter.invoke(character)
            _characters.value?.remove(character)
        }
    }

    fun addCharacter(character: CharacterUI) {
        viewModelScope.launch {
            character.id = insertCharacters.invoke(character)
            _characters.value?.add(character)
        }
    }

    fun getCharacters() {
        viewModelScope.launch {
            _characters.value = getCharacters.getCharacters()
        }
    }

}