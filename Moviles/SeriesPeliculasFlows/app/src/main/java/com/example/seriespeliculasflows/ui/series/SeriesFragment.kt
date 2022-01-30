package com.example.seriespeliculasflows.ui.series

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.seriespeliculasflows.R
import com.example.seriespeliculasflows.databinding.SeriesFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SeriesFragment : Fragment() {
    private val viewModel: SeriesViewModel by viewModels()
    private var _binding: SeriesFragmentBinding? = null
    private lateinit var adapter: SerieAdapter
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = SeriesFragmentBinding.inflate(inflater, container, false)
        observers()
        setListeners()
        if (viewModel.currentSeries.value != null) {
            if (viewModel.currentSeries.value?.isEmpty() == true) {
                getSeries(getString(R.string.firstPeliculasCallQuery))
            } else {
                adapter.submitList(viewModel.currentSeries.value)
            }
        } else {
            getSeries(getString(R.string.firstPeliculasCallQuery))
        }
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.series_fragment_menu, menu)
        val queryTextListener: SearchView.OnQueryTextListener =
            object : SearchView.OnQueryTextListener {
                override fun onQueryTextChange(newText: String): Boolean {
                    getSeries(newText)
                    return false
                }

                override fun onQueryTextSubmit(query: String): Boolean {
                    return false
                }
            }
        val actionSearch = menu.findItem(R.id.searchSeries).actionView as SearchView
        actionSearch.setOnQueryTextListener(queryTextListener)
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

    private fun getSeries(query: String) {
        viewModel.getSeries(query)
    }

}