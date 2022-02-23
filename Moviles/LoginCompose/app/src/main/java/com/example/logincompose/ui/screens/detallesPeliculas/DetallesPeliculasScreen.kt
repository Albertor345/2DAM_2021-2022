package com.example.logincompose.ui.screens.detallesPeliculas

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.example.logincompose.R

@Composable
fun DetallesPeliculasScreen(
    id: Int,
    onPopBackStack: () -> Unit,
    viewModel: DetallesPeliculasViewModel = hiltViewModel()
) {
    getSerie(id, viewModel)
    val uiState = viewModel.uiState.collectAsState()

    val title = remember { mutableStateOf(uiState.value.pelicula?.name) }
    val pelicula = uiState.value.pelicula.takeIf { it != null }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    title.value?.let { Text(text = it) }
                },
                navigationIcon = {
                    IconButton(onClick = { onPopBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, null)
                    }
                },
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = Color.White,
                elevation = 10.dp,
                actions = {
                    IconButton(onClick = { changeFavStatus(viewModel) }) {
                        val image =
                            if (pelicula?.favorito == true)
                                R.drawable.ic_baseline_favorite_24
                            else R.drawable.ic_baseline_favorite_border_24
                        Icon(
                            painter = painterResource(image),
                            contentDescription = null
                        )
                    }
                }
            )
        },
    ) {
        Column() {
            if (pelicula != null) {
                Image(
                    painter = rememberImagePainter(pelicula.imagePath),
                    contentDescription = null,
                    modifier = Modifier
                        .weight(5F)
                        .fillMaxWidth(),
                    contentScale = ContentScale.Fit
                )

                pelicula.overview?.let { overview ->
                    Text(
                        text = overview,
                        fontSize = 20.sp,
                        modifier = Modifier
                            .weight(12F)
                            .padding(20.dp),
                        textAlign = TextAlign.Justify
                    )
                }
            }
        }
    }

}

private fun changeFavStatus(viewModel: DetallesPeliculasViewModel) {
    viewModel.uiState.value.pelicula?.let {
        if (!it.favorito) {
            viewModel.handleEvent(DetallesPeliculasContract.Event.AddFavorito, null, it)
        } else {
            viewModel.handleEvent(DetallesPeliculasContract.Event.DeleteFavorito, null, it)
        }
    }

}

private fun getSerie(id: Int, viewModel: DetallesPeliculasViewModel) {
    viewModel.handleEvent(DetallesPeliculasContract.Event.GetPelicula, id, null)
}