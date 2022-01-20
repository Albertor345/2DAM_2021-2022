package com.example.seriespelisretrofit.ui.peliculas

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.seriespelisretrofit.R
import com.example.seriespelisretrofit.databinding.PeliculasFragmentBinding
import com.example.seriespelisretrofit.ui.model.PeliculaUI
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PeliculasFragment : Fragment() {
    private val viewModel: PeliculasViewModel by viewModels()
    private var _binding: PeliculasFragmentBinding? = null
    private lateinit var adapter: PeliculaAdapter
    private val binding get() = _binding!!

    private var currentPage = 1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = PeliculasFragmentBinding.inflate(inflater, container, false)
        configAdapter()
        observers()
        if (viewModel.currentFilms.value != null) {
            if (viewModel.currentFilms.value?.isEmpty() == true) {
                getPeliculas(getString(R.string.firstPeliculasCallQuery))
            } else {
                adapter.submitList(viewModel.currentFilms.value)
            }
        } else {
            getPeliculas(getString(R.string.firstPeliculasCallQuery))
        }
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.search -> {
                println("pulsado boton search")
            }
            else -> false
        }
        return super.onOptionsItemSelected(item)
    }

    private fun observers() {
        viewModel.error.observe(this, {
            Toast.makeText(this.requireContext(), it, Toast.LENGTH_LONG).show()
        })
        viewModel.currentFilms.observe(this, {
            adapter.submitList(it)
        })
    }

    private fun configAdapter() {
        adapter = PeliculaAdapter(object : PeliculaAdapter.PeliculaAdapterActions {
            override fun detalles(item: PeliculaUI) {
                this@PeliculasFragment.detalles(item)
            }
        })
        binding.recyclerViewPeliculas.adapter = adapter
    }

    private fun detalles(item: PeliculaUI) {
        findNavController().navigate(
            PeliculasFragmentDirections.actionPeliculasFragmentToDetallesPeliculasFragment(
                item.id,
                null
            )
        )
    }

    private fun getPeliculas(query: String) {
        viewModel.getPeliculas(query)
    }

}
