package com.example.logincompose.ui.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.logincompose.utils.Constants

sealed class NavItem(val route: String, args: List<Args> = emptyList()) {

    val wholeRoute = run {
        val argKeys = args.map { "{${it.key}}" }
        listOf(route).plus(argKeys).joinToString("/")
    }

    val navArgs = args.map {
        navArgument(it.key) { type = it.navType }
    }


    object Peliculas : NavItem(route = Constants.RUTA_PELICULAS)

    object Series : NavItem(route = Constants.RUTA_SERIES)

    object Favoritos : NavItem(route = Constants.RUTA_FAVORITOS)

    object DetallesPeliculas : NavItem(route = Constants.RUTA_DETALLES_PELICULAS, listOf(Args.Id))

    object DetallesSeries : NavItem(route = Constants.RUTA_DETALLES_SERIES, listOf(Args.Id))


}

enum class Args(val key: String, val navType: NavType<*>) {
    Id("id", NavType.IntType)
}
