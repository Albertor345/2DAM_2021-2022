package com.example.seriespelisretrofit.ui.peliculas.detalles

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.seriespelisretrofit.R
import com.example.seriespelisretrofit.databinding.DetallesPeliculaFragmentBinding
import com.example.seriespelisretrofit.ui.model.PeliculaUI
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetallesPeliculaFragment : Fragment() {
    private val viewModel: DetallesPeliculasViewModel by viewModels()
    private var _binding: DetallesPeliculaFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var menuItem: MenuItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.detalles_pelicula_menu, menu)
        menuItem = menu.findItem(R.id.peliculaFavorita)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.peliculaFavorita -> {
                changeFavStatus(item)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun changeFavStatus(menuItem: MenuItem) {
        if (!viewModel.currentFilm.value!!.favorito) {
            menuItem.setIcon(R.drawable.ic_baseline_favorite_24)
            viewModel.addToFavorito(viewModel.currentFilm.value!!)
        } else {
            menuItem.setIcon(R.drawable.ic_baseline_favorite_border_24)
            viewModel.removeFavorito(viewModel.currentFilm.value!!)
        }
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
            if (it.favorito) {
                menuItem.setIcon(R.drawable.ic_baseline_favorite_24)
            } else {
                menuItem.setIcon(R.drawable.ic_baseline_favorite_border_24)
            }
        }
    }


    private fun getPelicula(id: Int) {
        viewModel.getPelicula(id)
    }
}