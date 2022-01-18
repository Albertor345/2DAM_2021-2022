package com.example.seriespelisretrofit.ui.favoritos

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.*
import androidx.appcompat.view.ActionMode
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import coil.load
import com.example.seriespelisretrofit.R
import com.example.seriespelisretrofit.databinding.FavoritosFragmentBinding
import com.example.seriespelisretrofit.ui.main.MainActivity
import com.example.seriespelisretrofit.ui.model.FavoritoUI

class FavoritosFragment : Fragment() {

    private lateinit var adapter: FavoritoAdapter
    private lateinit var actionMode: ActionMode
    private val viewModel: FavoritosViewModel by viewModels()
    private var _binding: FavoritosFragmentBinding? = null
    private val binding get() = _binding!!

    private val callback by lazy {
        configContextBar()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FavoritosFragmentBinding.inflate(inflater, container, false)
        observers()
        setListeners()
        return binding.root
    }

    private fun observers() {
        viewModel.error.observe(this, {
            //Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })
        viewModel.favoriteList.observe(this, {
            adapter.submitList(it)
            emptyData()
        })
    }

    private fun setListeners() {
        configAdapter()
        emptyData()
        val touchHelper = ItemTouchHelper(adapter.swipeGesture)
        touchHelper.attachToRecyclerView(binding.recyclerViewFavoritos)
    }

    private fun emptyData() {
        with(binding) {
            if (adapter.itemCount != 0) {
                emptyDataLayout.visibility = View.GONE
            } else {
                imageView.load(
                    Drawable.createFromStream(
                        requireContext().assets.open("images/cajita_con_estrellas.png"),
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
                    it.title = "1 selected"

                }
            }

            override fun itemHasClicked(favorito: FavoritoUI) {
                viewModel.addItemSelected(favorito)
                actionMode.title =
                    "${viewModel.getSelectedItemSize()} items selected"
                viewModel.seleccionaFavorito(favorito)
            }

            override fun isItemSelected(favorito: FavoritoUI): Boolean =
                viewModel.isSelected(favorito)

        })
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