package alberto.gonzalez.crudpersonasv2.ui

import alberto.gonzalez.crudpersonasv2.R
import alberto.gonzalez.crudpersonasv2.data.Repository
import alberto.gonzalez.crudpersonasv2.databinding.DetailsActivityBinding
import alberto.gonzalez.crudpersonasv2.domain.Character
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import coil.load

class DetailsActivity : AppCompatActivity() {


    private lateinit var binding: DetailsActivityBinding
    private lateinit var repository: Repository
    private lateinit var character: Character

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DetailsActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        repository = Repository()

        character = repository.getCharacter(intent.getIntExtra(getString(R.string.characterIndex), 0))
        loadCharacter(character)
    }

    private fun loadCharacter(character: Character) {
        with(binding) {
            imageviewHeader.load(character.image.path + "." + character.image.extension)
            textviewTitle.text = character.name
            textviewDescription.text = character.description
        }
    }

}