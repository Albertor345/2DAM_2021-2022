package com.example.seriespelisretrofit.ui.main

import android.graphics.drawable.Drawable
import android.opengl.Visibility
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.ActionMode
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import coil.load
import com.example.seriespelisretrofit.R
import com.example.seriespelisretrofit.databinding.ActivityMainBinding
import com.example.seriespelisretrofit.ui.model.FavoritoUI
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private val viewModel: MainViewModel by viewModels()

    private lateinit var adapter: FavoritoAdapter
    private val callback by lazy {
        configContextBar()
    }

    private lateinit var actionMode: ActionMode

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.findNavController()
        observers()
        setListeners()
    }

    private fun observers() {
        viewModel.error.observe(this, {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })
        viewModel.favoriteList.observe(this, {

        })
    }

    private fun setListeners() {
        configAdapter()
        emptyData()
        binding.exploreButton.setOnClickListener {

        }
    }

    private fun emptyData() {
        with(binding) {
            if (adapter.itemCount != 0){
                emptyDataLayout.visibility = View.GONE
            }else{
                imageView.load(
                    Drawable.createFromStream(
                        assets.open("images/cajita_con_estrellas.png"),
                        null
                    )
                )
                emptyDataLayout.visibility = View.VISIBLE
            }
        }
    }

    private fun configAdapter() {
        adapter = FavoritoAdapter(this, object : FavoritoAdapter.FavoritoActions {
            override fun onDelete(favorito: FavoritoUI) = viewModel.delFavorito(favorito)

            override fun onStartSelectMode() {
                startSupportActionMode(callback)?.let {
                    actionMode = it;
                    it.title = "1 selected"

                }
            }

            override fun itemHasClicked(favorito: FavoritoUI) {
                actionMode.title =
                    "${adapter.getSelectedItems().size} selected"
                viewModel.seleccionaFavorito(favorito)
            }

            override fun isItemSelected(favorito: FavoritoUI): Boolean =
                viewModel.isSelected(favorito)

        })
    }

    private fun configContextBar() =
        object : ActionMode.Callback {

            override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                menuInflater.inflate(R.menu.main_activity_menu, menu)
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







