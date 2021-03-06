package com.example.seriespelisretrofit.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.ActionMode
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.*
import com.example.seriespelisretrofit.R
import com.example.seriespelisretrofit.databinding.ActivityMainBinding
import com.example.seriespelisretrofit.ui.favoritos.FavoritoAdapter
import com.example.seriespelisretrofit.ui.model.FavoritoUI
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.findNavController()
        setListeners()
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_activity_menu, menu)
        return true
    }

    private fun setListeners() {
        configAppBar()
        with(binding) {
            navView.setNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.peliculasFragment, R.id.seriesFragment, R.id.favoritosFragment -> {
                        drawerLayout.close()
                        it.onNavDestinationSelected(navController)
                        true
                    }
                    else -> false
                }
            }
            toolbar.setNavigationOnClickListener {
                drawerLayout.open()
            }
        }
    }

    private fun configAppBar() {
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.peliculasFragment, R.id.seriesFragment, R.id.favoritosFragment
            ), binding.drawerLayout
        ) {
            onBackPressed()
            true
        }

        setSupportActionBar(binding.toolbar)
        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.navView.setupWithNavController(navController)
    }


}







