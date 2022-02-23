package com.example.logincompose.ui.screens.peliculas

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.example.logincompose.R
import com.example.logincompose.ui.composables.SearchView
import com.example.logincompose.ui.model.ItemUI
import com.example.logincompose.ui.theme.Orange

@Composable
fun PeliculasScreen(
    onNavigation: (itemUI: ItemUI) -> Unit,
    onPopBackStack: () -> Unit,
    viewModel: PeliculasViewModel = hiltViewModel()
) {
    val uiState =
        viewModel.uiState.collectAsState()
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val textState = remember { mutableStateOf(TextFieldValue("")) }

    if (uiState.value.items.isEmpty()) getPeliculas(viewModel)

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(R.string.peliculas_screen_name))
                },
                navigationIcon = {
                    IconButton(onClick = { onPopBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, null)
                    }
                },
                actions = {
                    if (textState.value != TextFieldValue("")) {
                        SearchView(
                            textState = textState,
                            onSearch = { getPeliculas(viewModel, it) }
                        )
                    } else {
                        IconButton(
                            onClick = {
                                textState.value = TextFieldValue(" ")
                            }
                        ) {
                            Icon(Icons.Filled.Search, contentDescription = null)
                        }
                    }

                },
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = Color.White,
                elevation = 10.dp
            )
        }) {
        Column() {
            LazyColumn(modifier = Modifier.weight(15F)) {
                items(uiState.value.items) { data ->
                    Card(
                        modifier = Modifier
                            .clickable {
                                onNavigation(data)
                            }
                            .height(200.dp)
                            .padding(5.dp)
                    ) {
                        Row() {
                            Image(
                                painter = rememberImagePainter(data.imagePath),
                                contentDescription = null
                            )
                            Column() {
                                Text(
                                    text = requireNotNull(data.name),
                                    textAlign = TextAlign.Center,
                                    maxLines = 1,
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    overflow = TextOverflow.Ellipsis,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp,
                                    color = Orange
                                )
                                data.overview?.let { overview ->
                                    Text(
                                        text = overview,
                                        textAlign = TextAlign.Justify,
                                        overflow = TextOverflow.Ellipsis,
                                        maxLines = 6,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(5.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }
            TextButton(
                onClick = { updateUpcomingFilms(viewModel) },
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1F, true)
                    .background(Color.Black)
            ) {
                Text(
                    text = stringResource(R.string.upcoming_films_button_text),
                    color = Color.Magenta,
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp
                )
            }
        }

    }
}

private fun getPeliculas(viewModel: PeliculasViewModel) {
    viewModel.handleEvent(PeliculasContract.Event.GetPeliculasUpcoming, null, false)
}

private fun getPeliculas(viewModel: PeliculasViewModel, query: String) {
    viewModel.handleEvent(PeliculasContract.Event.GetPeliculasQuery, query, false)
}

private fun updateUpcomingFilms(viewModel: PeliculasViewModel) {
    viewModel.handleEvent(PeliculasContract.Event.GetPeliculasUpcoming, null, true)
}






