package com.example.seriespeliculasflows.ui.detallesSeries

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.seriespeliculasflows.R
import com.example.seriespeliculasflows.databinding.DetallesSerieFragmentBinding
import com.example.seriespeliculasflows.ui.detallesPeliculas.DetallesPeliculaFragmentArgs
import com.example.seriespeliculasflows.ui.detallesPeliculas.DetallesPeliculasContract
import com.example.seriespeliculasflows.ui.model.ItemUI
import com.example.seriespeliculasflows.ui.model.TemporadaUI
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetallesSeriesFragment : Fragment() {
    private val viewModel: DetallesSeriesViewModel by viewModels()
    private var _binding: DetallesSerieFragmentBinding? = null
    private lateinit var adapter: SeasonAdapter
    private val binding get() = _binding!!
    private lateinit var menuItem: MenuItem

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args: DetallesPeliculaFragmentArgs by navArgs()
        getSerie(args.id)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.detalles_serie_menu, menu)
        menuItem = menu.findItem(R.id.serieFavorita)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.serieFavorita -> {
                changeFavStatus(item)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun changeFavStatus(menuItem: MenuItem) {
        viewModel.uiState.value.serie?.let {
            if (!it.favorito) {
                viewModel.handleEvent(DetallesSeriesContract.Event.AddFavorito, null, it)
            } else {
                viewModel.handleEvent(DetallesSeriesContract.Event.DeleteFavorito, null, it)
            }
        }
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
        findNavController().navigate(
            DetallesSeriesFragmentDirections.actionDetallesSeriesFragmentToDetallesSeasonFragment(
                item.idSerie, item.seasonNumber!!
            )
        )
    }

    private fun observers() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiError.collect {
                    Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {
                    // binding.loading.visibility = if (it.isLoading) View.VISIBLE else View.GONE
                    it.serie?.let { serie ->
                        loadSerie(serie)
                        adapter.submitList(serie.seasons)
                    }
                }
            }
        }
    }

    private fun loadSerie(serie: ItemUI.SerieUI) {
        with(binding) {
            title.text = serie.name
            overview.text = serie.overview
            imagen.load(serie.imagePath)
            if (serie.favorito) {
                menuItem.setIcon(R.drawable.ic_baseline_favorite_24)
            } else {
                menuItem.setIcon(R.drawable.ic_baseline_favorite_border_24)
            }

        }
    }


    private fun getSerie(id: Int) {
        viewModel.handleEvent(DetallesSeriesContract.Event.GetSerie, id, null)
    }

}