package com.example.crudpersonasv3.ui.detailsActivity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.example.crudpersonasv3.R
import com.example.crudpersonasv3.databinding.DetailsActivityBinding
import com.example.crudpersonasv3.ui.domain.CharacterUI
import com.example.crudpersonasv3.usecases.characters.GetCharacters
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailsActivity @Inject constructor(getCharacters: GetCharacters) : AppCompatActivity() {


    private lateinit var binding: DetailsActivityBinding
    private lateinit var character: CharacterUI

    private val viewModel: DetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DetailsActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getCharacter(intent.getIntExtra(getString(R.string.characterId), 0))
        observers()
    }

    private fun observers() {
        viewModel.character.observe(this) {
            loadCharacter(it)
        }
    }

    private fun getCharacter(id: Int) {
        viewModel.getCharacter(id)
    }

    private fun loadCharacter(character: CharacterUI) {
        with(binding) {
            imageviewHeader.load(character.image)
            textviewTitle.text = character.name
            textviewDescription.text = character.description
        }
    }

}