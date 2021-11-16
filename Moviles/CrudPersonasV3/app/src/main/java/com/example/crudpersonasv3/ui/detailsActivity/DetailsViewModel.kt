package com.example.crudpersonasv3.ui.detailsActivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crudpersonasv3.ui.domain.CharacterUI
import com.example.crudpersonasv3.ui.domain.ComicUI
import com.example.crudpersonasv3.ui.domain.SerieUI
import com.example.crudpersonasv3.usecases.characters.GetCharacters
import com.example.crudpersonasv3.usecases.comics.DeleteComic
import com.example.crudpersonasv3.usecases.comics.InsertComic
import com.example.crudpersonasv3.usecases.series.DeleteSerie
import com.example.crudpersonasv3.usecases.series.InsertSerie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val deleteComic: DeleteComic,
    private val insertComic: InsertComic,
    private val deleteSerie: DeleteSerie,
    private val insertSerie: InsertSerie,
    private val getCharacters: GetCharacters
) : ViewModel() {

    private val _character = MutableLiveData<CharacterUI>()
    val character: LiveData<CharacterUI> get() = _character

    private val _comicsCharacter = MutableLiveData<List<ComicUI>>()
    val comicsCharacter: LiveData<List<ComicUI>> get() = _comicsCharacter

    private val _seriesCharacter = MutableLiveData<List<SerieUI>>()
    val seriesCharacter: LiveData<List<SerieUI>> get() = _seriesCharacter

    fun getCharacter(id: Long) {
        viewModelScope.launch {
            val characterFull = getCharacters.getCharacterFull(id)
            _character.value = characterFull
            _comicsCharacter.value = characterFull.comics
            _seriesCharacter.value = characterFull.series
        }
    }

    fun insertComic(comicUI: ComicUI) {
        viewModelScope.launch {
            comicUI.id = insertComic.invoke(comicUI, character.value!!)
            _comicsCharacter.value = _comicsCharacter.value?.plus(comicUI)
        }
    }

    fun deleteComic(comicUI: ComicUI) {
        viewModelScope.launch {
            deleteComic.invoke(comicUI)
            _comicsCharacter.value = _comicsCharacter.value?.minus(comicUI)
        }
    }

    fun insertSerie(serieUI: SerieUI) {
        viewModelScope.launch {
            serieUI.id = insertSerie.invoke(serieUI, character.value!!)
            _seriesCharacter.value = _seriesCharacter.value?.plus(serieUI)
        }
    }

    fun deleteSerie(serieUI: SerieUI) {
        viewModelScope.launch {
            deleteSerie.invoke(serieUI)
            _seriesCharacter.value = _seriesCharacter.value?.minus(serieUI)
        }
    }

}