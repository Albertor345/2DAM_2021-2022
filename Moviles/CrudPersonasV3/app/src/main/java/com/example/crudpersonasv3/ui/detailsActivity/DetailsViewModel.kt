package com.example.crudpersonasv3.ui.detailsActivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crudpersonasv3.ui.domain.CharacterUI
import com.example.crudpersonasv3.usecases.characters.GetCharacters
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getCharacters: GetCharacters
) : ViewModel() {

    private val _character = MutableLiveData<CharacterUI>()
    val character: LiveData<CharacterUI> get() = _character

    fun getCharacter(id: Long) {
        viewModelScope.launch {
            _character.value = getCharacters.getCharacterFull(id)
        }
    }

}