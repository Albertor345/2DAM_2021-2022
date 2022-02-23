package com.example.logincompose.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.logincompose.ui.model.ItemUI
import com.example.logincompose.ui.screens.detallesPeliculas.DetallesPeliculasScreen
import com.example.logincompose.ui.screens.detallesSeries.DetallesSeriesScreen
import com.example.logincompose.ui.screens.favoritos.FavoritosScreen
import com.example.logincompose.ui.screens.peliculas.PeliculasScreen
import com.example.logincompose.ui.screens.series.SeriesScreen

@Composable
fun Navigation() {

    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = NavItem.Favoritos.wholeRoute,
    ) {
        composable(
            route = NavItem.Favoritos.wholeRoute,
            arguments = NavItem.Favoritos.navArgs
        ) {
            FavoritosScreen(onDetailsNavigation = {
                when (it) {
                    is ItemUI.PeliculaUI -> {
                        navController.navigate("${NavItem.DetallesPeliculas.route}/${it.id}")
                    }
                    else -> {
                        navController.navigate("${NavItem.DetallesSeries.route}/${it.id}")
                    }
                }
            }, onNavigation = {
                navController.navigate(it.wholeRoute)
            })
        }
        composable(
            route = NavItem.Series.wholeRoute,
            arguments = NavItem.Series.navArgs
        ) {
            SeriesScreen(
                onNavigation = {
                    navController.navigate("${NavItem.DetallesSeries.route}/${it.id}")
                },
                onPopBackStack = {
                    navController.popBackStack()
                }
            )
        }
        composable(
            route = NavItem.Peliculas.wholeRoute,
            arguments = NavItem.Peliculas.navArgs
        ) {
            PeliculasScreen(
                onNavigation = {
                    navController.navigate("${NavItem.DetallesPeliculas.route}/${it.id}")
                },
                onPopBackStack = {
                    navController.popBackStack()
                })
        }
        composable(
            route = NavItem.DetallesPeliculas.wholeRoute,
            arguments = NavItem.DetallesPeliculas.navArgs
        ) {
            val id = requireNotNull(it.arguments?.get("id")) as Int
            DetallesPeliculasScreen(id, onPopBackStack = {
                navController.popBackStack()
            })
        }
        composable(
            route = NavItem.DetallesSeries.wholeRoute,
            arguments = NavItem.DetallesSeries.navArgs
        ) {
            val id = requireNotNull(it.arguments?.get("id")) as Int
            DetallesSeriesScreen(id, onPopBackStack = {
                navController.popBackStack()
            })
        }
    }

}