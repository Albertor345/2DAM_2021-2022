package com.example.seriespeliculasflows.ui.seasons

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.example.seriespeliculasflows.databinding.DetallesSeasonFragmentBinding
import com.example.seriespeliculasflows.ui.model.CapituloUI
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

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
                    it.season?.let { it.capitulos.let { adapter.submitList(it) } }
                        ?: emptyList<CapituloUI>()
                }
            }
        }
    }
}