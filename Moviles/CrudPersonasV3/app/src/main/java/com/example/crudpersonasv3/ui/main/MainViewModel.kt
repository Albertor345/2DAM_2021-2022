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


    private val _characters = MutableLiveData<List<CharacterUI>>()
    val characters: LiveData<List<CharacterUI>> get() = _characters


    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun deleteCharacter(character: CharacterUI) {
        try {
            viewModelScope.launch {
                deleteCharacter.invoke(character)
                getCharacters()
            }
        } catch (ex: Exception) {
            _error.value = ex.toString()
        }
    }

    fun addCharacter(character: CharacterUI) {
        try {
            viewModelScope.launch {
                character.id = insertCharacters.invoke(character)
                getCharacters()
            }
        } catch (ex: Exception) {
            _error.value = ex.toString()
        }
    }

    fun getCharacters() {
        try {
            viewModelScope.launch {
                _characters.value = getCharacters.getCharacters()
            }
        } catch (ex: Exception) {
            _error.value = ex.toString()
        }
    }
}