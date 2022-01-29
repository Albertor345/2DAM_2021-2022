package com.example.seriespeliculasflows.ui.seasons

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.seriespeliculasflows.databinding.DetallesSeasonFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetallesSeasonFragment : Fragment() {

    private val viewModel: DetallesSeasonViewModel by viewModels()
    private var _binding: DetallesSeasonFragmentBinding? = null
    private lateinit var adapter: ChapterAdapter
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DetallesSeasonFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args: DetallesSeasonFragmentArgs by navArgs()
        configAdapter()
        observers()
        getCapitulos(args.id, args.seasonNumber)
    }

    private fun getCapitulos(id: Int, seasonNumber: Int) {
        viewModel.getSeason(id, seasonNumber)
    }

    private fun configAdapter() {
        adapter = ChapterAdapter()
        binding.recyclerViewCapitulos.adapter = adapter
    }

    private fun observers() {
        viewModel.error.observe(this, {
            Toast.makeText(this.requireContext(), it, Toast.LENGTH_LONG).show()
        })
        viewModel.capitulos.observe(this, {
            adapter.submitList(it)
        })
    }
}