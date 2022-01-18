package com.example.seriespelisretrofit.ui.peliculas.detalles

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.seriespelisretrofit.databinding.DetallesPeliculaFragmentBinding
import com.example.seriespelisretrofit.ui.model.PeliculaUI
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetallesPeliculaFragment : Fragment() {
    private val viewModel: DetallesPeliculasViewModel by viewModels()
    private var _binding: DetallesPeliculaFragmentBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DetallesPeliculaFragmentBinding.inflate(inflater, container, false)
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
        viewModel.currentFilm.observe(this, {
            loadPelicula(it)
        })
    }

    private fun loadPelicula(it: PeliculaUI) {
        with(binding) {
            title.text = it.title
            overview.text = it.overview
            imagen.load(it.posterPath)
        }
    }


    private fun getPelicula(id: Int) {
        viewModel.getPelicula(id)
    }
}