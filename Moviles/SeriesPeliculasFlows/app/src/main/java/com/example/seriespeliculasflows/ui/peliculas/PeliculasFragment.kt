package com.example.seriespeliculasflows.ui.peliculas

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
import com.example.seriespeliculasflows.databinding.PeliculasFragmentBinding
import com.example.seriespeliculasflows.ui.model.ItemUI
import com.example.seriespeliculasflows.utils.Constants
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PeliculasFragment : Fragment() {
    private val viewModel: PeliculasViewModel by viewModels()
    private var _binding: PeliculasFragmentBinding? = null
    private lateinit var adapter: PeliculaAdapter
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = PeliculasFragmentBinding.inflate(inflater, container, false)
        configAdapter()
        observers()
        viewModel.handleEvent(PeliculasContract.Event.GetPeliculasUpcoming, null)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.peliculas_fragment_menu, menu)
        val queryTextListener: SearchView.OnQueryTextListener =
            object : SearchView.OnQueryTextListener {
                override fun onQueryTextChange(newText: String): Boolean {
                    getPeliculas(newText)
                    return false
                }

                override fun onQueryTextSubmit(query: String): Boolean {
                    return false
                }
            }
        val actionSearch = menu.findItem(R.id.search).actionView as SearchView
        actionSearch.setOnQueryTextListener(queryTextListener)

        return super.onCreateOptionsMenu(menu, inflater)
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

    private fun configAdapter() {
        adapter = PeliculaAdapter(object : PeliculaAdapter.PeliculaAdapterActions {
            override fun detalles(item: ItemUI.PeliculaUI) {
                this@PeliculasFragment.detalles(item)
            }
        })
        binding.recyclerViewPeliculas.adapter = adapter
    }

    private fun detalles(item: ItemUI.PeliculaUI) {
        findNavController().navigate(
            PeliculasFragmentDirections.actionPeliculasFragmentToDetallesPeliculasFragment(
                item.id
            )
        )
    }

    private fun getPeliculas(query: String) {
        viewModel.handleEvent(PeliculasContract.Event.GetPeliculasQuery, query)
    }

}
