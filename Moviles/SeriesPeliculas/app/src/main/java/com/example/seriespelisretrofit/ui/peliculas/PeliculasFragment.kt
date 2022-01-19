package com.example.seriespelisretrofit.ui.peliculas

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
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
        observers()
        configAdapter()
        getPeliculas(getString(R.string.firstPeliculasCallQuery))
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.peliculas_fragment_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.search -> {
                val actionSearch = item.actionView as SearchView

                actionSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(p0: String?): Boolean {
                        p0?.let {getPeliculas(it)}
                        return true
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        newText?.let {getPeliculas(it)}
                        return true
                    }
                })
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
