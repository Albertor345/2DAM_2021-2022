package com.example.seriespelisretrofit.ui.seriesActivity.detalles

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.seriespelisretrofit.databinding.DetallesSerieFragmentBinding
import com.example.seriespelisretrofit.ui.model.PeliculaUI
import com.example.seriespelisretrofit.ui.model.SerieUI
import com.example.seriespelisretrofit.ui.model.TemporadaUI
import com.example.seriespelisretrofit.ui.peliculas.PeliculaAdapter
import com.example.seriespelisretrofit.ui.peliculas.PeliculasFragmentDirections
import com.example.seriespelisretrofit.ui.peliculas.detalles.DetallesPeliculaFragmentArgs
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetallesSeriesFragment : Fragment() {
    private val viewModel: DetallesSeriesViewModel by viewModels()
    private var _binding: DetallesSerieFragmentBinding? = null
    private lateinit var adapter: SeasonAdapter
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DetallesSerieFragmentBinding.inflate(inflater, container, false)
        observers()
        configAdapter()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args: DetallesPeliculaFragmentArgs by navArgs()
        getSerie(args.id)
    }

    private fun configAdapter() {
        adapter = SeasonAdapter(object : SeasonAdapter.SeasonAdapterActions {
            override fun detalles(item: TemporadaUI) {
                this@DetallesSeriesFragment.detalles(item)
            }
        })
        binding.seasons.adapter = adapter
    }

    private fun detalles(item: TemporadaUI) {

    }

    private fun observers() {
        viewModel.error.observe(this, {
            Toast.makeText(this.requireContext(), it, Toast.LENGTH_LONG).show()
        })
        viewModel.currentSerie.observe(this, {
            loadSerie(it)
            adapter.submitList(it.seasons)
        })
    }

    private fun loadSerie(it: SerieUI) {
        with(binding) {
            title.text = it.name
            overview.text = it.overview
            imagen.load(it.posterPath)
        }
    }


    private fun getSerie(id: Int) {
        viewModel.getSerie(id)
    }

}