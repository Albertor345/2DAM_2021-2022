package com.example.seriespeliculasflows.ui.detallesSeries

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.seriespeliculasflows.R
import com.example.seriespeliculasflows.databinding.DetallesSerieFragmentBinding
import com.example.seriespeliculasflows.ui.model.SerieUI
import com.example.seriespeliculasflows.ui.model.TemporadaUI
import com.example.seriespeliculasflows.ui.detallesPeliculas.DetallesPeliculaFragmentArgs
import dagger.hilt.android.AndroidEntryPoint

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
        if (!viewModel.currentSerie.value!!.favorito) {
            menuItem.setIcon(R.drawable.ic_baseline_favorite_24)
            viewModel.addToFavorito(viewModel.currentSerie.value!!)
        } else {
            menuItem.setIcon(R.drawable.ic_baseline_favorite_border_24)
            viewModel.removeFavorito(viewModel.currentSerie.value!!)
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
            if (it.favorito) {
                menuItem.setIcon(R.drawable.ic_baseline_favorite_24)
            } else {
                menuItem.setIcon(R.drawable.ic_baseline_favorite_border_24)
            }

        }
    }


    private fun getSerie(id: Int) {
        viewModel.getSerie(id)
    }

}