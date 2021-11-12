package com.example.crudpersonasv3.ui.detailsActivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.example.crudpersonasv3.R
import com.example.crudpersonasv3.data.repositories.characters.RepositoryCharacters
import com.example.crudpersonasv3.databinding.DetailsActivityBinding
import com.example.crudpersonasv3.ui.domain.CharacterUI

class DetailsActivity : AppCompatActivity() {


    private lateinit var binding: DetailsActivityBinding
    private lateinit var repository: RepositoryCharacters
    private lateinit var character: CharacterUI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DetailsActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //repository = RepositoryCharacters()

        /*character =
            repository.getCharacter(intent.getIntExtra(getString(R.string.characterIndex), 0))
        loadCharacter(character)*/
    }

    private fun loadCharacter(character: CharacterUI) {
        with(binding) {
            imageviewHeader.load(character.image)
            textviewTitle.text = character.name
            textviewDescription.text = character.description
        }
    }

}