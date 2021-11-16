package com.example.crudpersonasv3.ui.detailsActivity

import android.os.Bundle
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.example.crudpersonasv3.R
import com.example.crudpersonasv3.databinding.DetailsActivityBinding
import com.example.crudpersonasv3.ui.domain.CharacterUI
import com.example.crudpersonasv3.ui.domain.ComicUI
import com.example.crudpersonasv3.ui.domain.SerieUI
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: DetailsActivityBinding
    private lateinit var adapterComics: ComicsAdapter
    private lateinit var adapterSeries: SeriesAdapter
    private val viewModel: DetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DetailsActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getCharacter(
            intent.getLongExtra(
                resources.getString(R.string.details_activity_characterID),
                -1
            )
        )
        observers()
        setListners()

        binding.comics.adapter = ComicsAdapter(::deleteComic)
        adapterComics = binding.comics.adapter as ComicsAdapter
        binding.comics.layoutManager = LinearLayoutManager(this)

        binding.series.adapter = SeriesAdapter(::deleteSerie)
        adapterSeries = binding.series.adapter as SeriesAdapter
        binding.series.layoutManager = LinearLayoutManager(this)
    }

    private fun observers() {
        viewModel.character.observe(this) {
            loadCharacter(it)
            adapterComics.submitList(it.comics)
            adapterSeries.submitList(it.series)
        }
        viewModel.comicsCharacter.observe(this) {
            adapterComics.submitList(it)
        }
        viewModel.seriesCharacter.observe(this) {
            adapterSeries.submitList(it)
        }
    }

    private fun setListners() {
        with(binding) {
            buttonAddComic.setOnClickListener {
                editTextDialog(
                    resources.getString(R.string.add_comic_title),
                    resources.getString(R.string.add_comic_message),
                    true
                )
            }
            buttonAddSerie.setOnClickListener {
                editTextDialog(
                    resources.getString(R.string.add_serie_title),
                    resources.getString(R.string.add_serie_message),
                    false
                )

            }
        }
    }

    private fun editTextDialog(title: String, message: String, boolean: Boolean) {
        val editText = EditText(this)
        MaterialAlertDialogBuilder(this)
            .setTitle(title)
            .setView(editText)
            .setMessage(message)
            .setNegativeButton(resources.getString(R.string.cancel)) { dialog, which ->
                dialog.dismiss()
            }
            .setPositiveButton(resources.getString(R.string.accept)) { dialog, which ->
                if (boolean)
                    addComic(ComicUI(null, editText.text.toString()))
                else
                    addSerie(SerieUI(null, editText.text.toString()))
            }
            .show()
    }

    private fun addSerie(serieUI: SerieUI) {
        viewModel.insertSerie(serieUI)
    }

    private fun addComic(comicUI: ComicUI) {
        viewModel.insertComic(comicUI)
    }

    private fun deleteComic(comicUI: ComicUI) {
        viewModel.deleteComic(comicUI)
    }

    private fun deleteSerie(serieUI: SerieUI) {
        viewModel.deleteSerie(serieUI)
    }

    private fun getCharacter(id: Long) {
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