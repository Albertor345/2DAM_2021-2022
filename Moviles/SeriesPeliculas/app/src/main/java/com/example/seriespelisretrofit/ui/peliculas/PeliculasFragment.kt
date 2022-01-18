package com.example.seriespelisretrofit.ui.peliculas

import android.os.Bundle
import android.view.*
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
        observers()
        setListeners()
        getPeliculas()
        return binding.root
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.peliculas_fragment_menu, menu)
    }

    private fun observers() {
        viewModel.error.observe(this, {
            Toast.makeText(this.requireContext(), it, Toast.LENGTH_LONG).show()
        })
        viewModel.currentFilms.observe(this, {
            adapter.submitList(it)
        })
    }

    private fun setListeners() {
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

    private fun getPeliculas() {
        viewModel.getPeliculas(getString(R.string.firstPeliculasCallQuery), currentPage)
    }

}
