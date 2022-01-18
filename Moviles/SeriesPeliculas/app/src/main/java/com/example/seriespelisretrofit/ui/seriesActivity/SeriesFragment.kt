package com.example.seriespelisretrofit.ui.seriesActivity

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.seriespelisretrofit.R
import com.example.seriespelisretrofit.databinding.SeriesFragmentBinding
import com.example.seriespelisretrofit.ui.model.PeliculaUI
import com.example.seriespelisretrofit.ui.model.SerieUI
import com.example.seriespelisretrofit.ui.peliculas.PeliculaAdapter
import com.example.seriespelisretrofit.ui.peliculas.PeliculasFragmentDirections
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SeriesFragment : Fragment() {
    private val viewModel: SeriesViewModel by viewModels()
    private var _binding: SeriesFragmentBinding? = null
    private lateinit var adapter: SerieAdapter
    private val binding get() = _binding!!

    private val currentPage = 1


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = SeriesFragmentBinding.inflate(inflater, container, false)
        observers()
        setListeners()
        getSeries()
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.series_fragment_menu, menu)
    }

    private fun observers() {
        viewModel.error.observe(this, {
            Toast.makeText(this.requireContext(), it, Toast.LENGTH_LONG).show()
        })
        viewModel.currentSeries.observe(this, {
            adapter.submitList(it)
        })
    }

    private fun setListeners() {
        adapter = SerieAdapter(object : SerieAdapter.SerieAdapterActions {
            override fun detalles(item: SerieUI) {
                this@SeriesFragment.detalles(item)
            }
        })
        binding.recyclerViewSeries.adapter = adapter
    }

    private fun detalles(item: SerieUI) {
        findNavController().navigate(
            SeriesFragmentDirections.actionSeriesFragmentToDetallesSeriesFragment(
                item.id,
                null
            )
        )
    }

    private fun getSeries() {
        viewModel.getSeries(getString(R.string.firstPeliculasCallQuery), currentPage)
    }

}