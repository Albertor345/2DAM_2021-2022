package com.example.seriespelisretrofit.ui.seriesActivity.detalles

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.seriespelisretrofit.databinding.DetallesSerieFragmentBinding
import com.example.seriespelisretrofit.ui.model.SerieUI
import com.example.seriespelisretrofit.ui.peliculas.detalles.DetallesPeliculaFragmentArgs
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetallesSeriesFragment : Fragment() {
    private val viewModel: DetallesSeriesViewModel by viewModels()
    private var _binding: DetallesSerieFragmentBinding? = null


    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DetallesSerieFragmentBinding.inflate(inflater, container, false)
        observers()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args: DetallesPeliculaFragmentArgs by navArgs()
        getPelicula(args.id)
    }

    private fun observers() {
        viewModel.error.observe(this, {
            Toast.makeText(this.requireContext(), it, Toast.LENGTH_LONG).show()
        })
        viewModel.currentSerie.observe(this, {
            loadSerie(it)
        })
    }

    private fun loadSerie(it: SerieUI) {
        with(binding) {
            title.text = it.name
            overview.text = it.overview
            imagen.load(it.posterPath)
        }
    }


    private fun getPelicula(id: Int) {
        viewModel.getSerie(id)
    }

}