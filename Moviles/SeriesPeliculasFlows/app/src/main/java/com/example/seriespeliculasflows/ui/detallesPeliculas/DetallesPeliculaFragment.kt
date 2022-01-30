package com.example.seriespeliculasflows.ui.detallesPeliculas

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.seriespeliculasflows.R
import com.example.seriespeliculasflows.databinding.DetallesPeliculaFragmentBinding
import com.example.seriespeliculasflows.ui.model.PeliculaUI
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetallesPeliculaFragment : Fragment() {
    private val viewModel: DetallesPeliculasViewModel by viewModels()
    private var _binding: DetallesPeliculaFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var menuItem: MenuItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        observers()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DetallesPeliculaFragmentBinding.inflate(inflater, container, false)
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
        lifecycleScope.launch{
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.uiError.collect {
                    Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                }
            }
        }

        lifecycleScope.launch{
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.uiState.collect {
                   // binding.loading.visibility = if (it.isLoading) View.VISIBLE else View.GONE
                    it.pelicula?.let { pelicula -> loadPelicula(pelicula) }

                }
            }
        }
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