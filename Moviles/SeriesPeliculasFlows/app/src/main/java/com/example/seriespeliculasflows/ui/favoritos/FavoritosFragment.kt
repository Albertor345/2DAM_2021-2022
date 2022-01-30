package com.example.seriespeliculasflows.ui.favoritos

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.view.ActionMode
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import coil.load
import com.example.seriespeliculasflows.R
import com.example.seriespeliculasflows.databinding.FavoritosFragmentBinding
import com.example.seriespeliculasflows.ui.main.MainActivity
import com.example.seriespeliculasflows.ui.model.ItemUI
import com.example.seriespeliculasflows.utils.Constants
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavoritosFragment : Fragment() {

    private lateinit var adapter: FavoritoAdapter
    private lateinit var actionMode: ActionMode
    private val viewModel: FavoritosViewModel by viewModels()
    private var _binding: FavoritosFragmentBinding? = null
    private val binding get() = _binding!!

    private val callback by lazy {
        configContextBar()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FavoritosFragmentBinding.inflate(inflater, container, false)
        configAdapter()
        observers()
        getFavoritos()
        return binding.root
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
                    it.items.let { favoritos ->
                        emptyData(favoritos)
                        adapter.submitList(favoritos)
                    }
                }
            }
        }
    }

    private fun getFavoritos() {
        viewModel.handleEvent(FavoritosContract.Event.GetFavoritos, null)
    }

    private fun emptyData(items: List<ItemUI>) {
        with(binding) {
            if (items.isNotEmpty()) {
                emptyDataLayout.visibility = View.GONE
            } else {
                imageView.load(
                    Drawable.createFromStream(
                        requireContext().assets.open(Constants.EMPTY_DATA_IMAGE),
                        null
                    )
                )
                emptyDataLayout.visibility = View.VISIBLE
            }
        }
    }

    private fun configAdapter() {

        adapter = FavoritoAdapter(this.requireContext(), object : FavoritoAdapter.FavoritoActions {
            override fun onDelete(item: ItemUI) = viewModel.delFavorito(item)

            override fun onStartSelectMode() {
                (requireActivity() as MainActivity).startSupportActionMode(callback)?.let {
                    actionMode = it
                    it.title = "${viewModel.getSelectedItemSize()} ${Constants.SELECTED_ITEMS}"
                }
            }

            override fun itemHasClicked(item: ItemUI) {
                viewModel.handleEvent(FavoritosContract.Event.SeleccionarFavorito, item)
                actionMode.title =
                    "${viewModel.getSelectedItemSize()} ${Constants.SELECTED_ITEMS}"
            }

            override fun isItemSelected(item: ItemUI): Boolean =
                viewModel.handleEvent(FavoritosContract.Event.ItemIsSelected, item)

            override fun detalles(item: ItemUI) {
                when (item) {
                    is ItemUI.PeliculaUI -> findNavController().navigate(
                        FavoritosFragmentDirections.actionFavoritosFragmentToDetallesPeliculasFragment(
                            item.id
                        )
                    )
                    is ItemUI.SerieUI -> findNavController().navigate(
                        FavoritosFragmentDirections.actionFavoritosFragmentToDetallesSeriesFragment(
                            item.id
                        )
                    )
                }
            }
        })
        binding.recyclerViewFavoritos.adapter = adapter
        val touchHelper = ItemTouchHelper(adapter.swipeGesture)
        touchHelper.attachToRecyclerView(binding.recyclerViewFavoritos)
    }

    private fun configContextBar() =
        object : ActionMode.Callback {

            override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                activity?.menuInflater?.inflate(R.menu.main_activity_menu, menu)
                return true
            }

            override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                return false
            }

            override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
                return when (item?.itemId) {
                    else -> true
                }
            }

            override fun onDestroyActionMode(mode: ActionMode?) {
                adapter.resetSelectMode()

            }

        }
}