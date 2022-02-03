package com.example.seriespeliculasflows.ui.series

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.seriespeliculasflows.R
import com.example.seriespeliculasflows.databinding.SeriesFragmentBinding
import com.example.seriespeliculasflows.ui.model.ItemUI
import com.example.seriespeliculasflows.utils.Constants
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

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
    ): View {
        _binding = SeriesFragmentBinding.inflate(inflater, container, false)
        observers()
        setListeners()
        viewModel.handleEvent(SeriesContract.Event.GetTopRatedSeries, null, false)
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
                    return false
                }

                override fun onQueryTextSubmit(query: String): Boolean {
                    getSeries(query)
                    return false
                }
            }
        val actionSearch = menu.findItem(R.id.searchSeries).actionView as SearchView
        actionSearch.setOnQueryTextListener(queryTextListener)
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
                    it.items.let { adapter.submitList(it) }
                }
            }
        }
    }

    private fun setListeners() {
        adapter = SerieAdapter(object : SerieAdapter.SerieAdapterActions {
            override fun detalles(item: ItemUI.SerieUI) {
                this@SeriesFragment.detalles(item)
            }
        })
        binding.updateButton.setOnClickListener{
            updateTopRatedSeries()
        }
        binding.recyclerViewSeries.adapter = adapter
    }

    private fun detalles(item: ItemUI.SerieUI) {
        findNavController().navigate(
            SeriesFragmentDirections.actionSeriesFragmentToDetallesSeriesFragment(
                item.id
            )
        )
    }

    private fun updateTopRatedSeries(){
        viewModel.handleEvent(SeriesContract.Event.GetTopRatedSeries, null, true)
    }

    private fun getSeries(query: String) {
        viewModel.handleEvent(SeriesContract.Event.GetSeriesQuery, query, false)
    }

}