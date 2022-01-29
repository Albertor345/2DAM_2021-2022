package com.example.seriespeliculasflows.ui.favoritos

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.view.ActionMode
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import coil.load
import com.example.seriespeliculasflows.R
import com.example.seriespeliculasflows.databinding.FavoritosFragmentBinding
import com.example.seriespeliculasflows.ui.main.MainActivity
import com.example.seriespeliculasflows.ui.model.FavoritoUI
import com.example.seriespeliculasflows.utils.Constants
import dagger.hilt.android.AndroidEntryPoint

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
        viewModel.error.observe(this, {
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        })
        viewModel.favoriteList.observe(this, {
            emptyData(it)
            adapter.submitList(it)
        })
    }

    private fun getFavoritos() {
        viewModel.getFavoritos()
    }

    private fun emptyData(favoritos: List<FavoritoUI>) {
        with(binding) {
            if (favoritos.isNotEmpty()) {
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
            override fun onDelete(favorito: FavoritoUI) = viewModel.delFavorito(favorito)

            override fun onStartSelectMode() {
                (requireActivity() as MainActivity).startSupportActionMode(callback)?.let {
                    actionMode = it
                    it.title = "${viewModel.getSelectedItemSize()} ${Constants.SELECTED_ITEMS}"
                }
            }

            override fun itemHasClicked(favorito: FavoritoUI) {
                viewModel.handleEvent(FavoritosContract.Event.SeleccionarFavorito, favorito)
                actionMode.title =
                    "${viewModel.getSelectedItemSize()} ${Constants.SELECTED_ITEMS}"
            }

            override fun isItemSelected(favorito: FavoritoUI): Boolean =
                viewModel.handleEvent(FavoritosContract.Event.ItemIsSelected, favorito)

            override fun detalles(favorito: FavoritoUI) {
                if (favorito.tipo == Constants.PELICULA_TYPE) {
                    findNavController().navigate(
                        FavoritosFragmentDirections.actionFavoritosFragmentToDetallesPeliculasFragment(
                            favorito.id,
                            favorito.tipo
                        )
                    )
                } else {
                    findNavController().navigate(
                        FavoritosFragmentDirections.actionFavoritosFragmentToDetallesSeriesFragment(
                            favorito.id,
                            favorito.tipo
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