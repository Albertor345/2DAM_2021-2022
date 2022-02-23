package com.example.logincompose.ui.screens.favoritos

import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.example.logincompose.R
import com.example.logincompose.ui.model.ItemUI
import com.example.logincompose.ui.navigation.NavItem
import com.example.logincompose.ui.theme.Orange
import kotlinx.coroutines.launch

@Composable
fun FavoritosScreen(
    onNavigation: (navItem: NavItem) -> Unit,
    onDetailsNavigation: (itemUI: ItemUI) -> Unit,
    viewModel: FavoritosViewModel = hiltViewModel()
) {

    getFavoritos(viewModel)
    val uiState =
        viewModel.uiState.collectAsState()
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(R.string.favoritos_screen_name))
                },
                navigationIcon = {
                    IconButton(onClick = {
                        scope.launch {
                            scaffoldState.drawerState.open()
                        }
                    }) {
                        Icon(Icons.Filled.Menu, null)
                    }
                },
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = Color.White,
                elevation = 10.dp
            )
        },
        drawerContent = {
            Column() {
                Box(
                    modifier = Modifier
                        .height(50.dp)
                        .fillMaxWidth()
                        .background(MaterialTheme.colors.primary)
                        .align(CenterHorizontally)
                ) {
                    Text(
                        text = stringResource(R.string.drawer_top_title),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.ExtraBold,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                Divider(color = Color.White)
                NavigationRail(modifier = Modifier.fillMaxSize()) {

                    NavigationRailItem(
                        selected = false,
                        onClick = { onNavigation(NavItem.Peliculas) },
                        icon = {
                            Box() {
                                Image(
                                    bitmap = BitmapFactory.decodeStream(
                                        context.assets.open(
                                            stringResource(R.string.peliculas_drawer_image_route)
                                        )
                                    )
                                        .asImageBitmap(), contentDescription = null,
                                    modifier = Modifier.size(100.dp)
                                )
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_android_white_24dp),
                                    contentDescription = ""
                                )
                                Text(
                                    text = stringResource(id = R.string.peliculas_drawer_label),
                                    modifier = Modifier.align(BottomCenter),
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Black,
                                    color = Color.White
                                )
                            }
                        }, modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                    )

                    Divider(color = Color.White)

                    NavigationRailItem(
                        selected = false,
                        onClick = { onNavigation(NavItem.Series) },
                        icon = {
                            Box() {
                                Image(
                                    bitmap = BitmapFactory.decodeStream(
                                        context.assets.open(
                                            stringResource(R.string.series_drawer_image_route)
                                        )
                                    )
                                        .asImageBitmap(), contentDescription = null,
                                    modifier = Modifier.size(100.dp)
                                )
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_android_white_24dp),
                                    contentDescription = null
                                )
                                Text(
                                    text = stringResource(id = R.string.series_drawer_label),
                                    modifier = Modifier.align(BottomCenter),
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Black,
                                    color = Color.White
                                )
                            }
                        }, modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                    )
                    Divider(color = Color.White)
                }
            }
        },
        modifier = Modifier.fillMaxSize()
    ) {
        if (uiState.value.items.isNotEmpty()) {
            LazyColumn() {
                items(uiState.value.items) { data ->
                    Card(
                        modifier = Modifier
                            .clickable {
                                onDetailsNavigation(data)
                            }
                            .height(200.dp)
                            .padding(5.dp)
                            .border(
                                width = 2.dp,
                                color = if (data is ItemUI.PeliculaUI) Orange else Color.Magenta
                            )
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
                                    color = if (data is ItemUI.PeliculaUI) Orange else Color.Magenta
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
        } else {
            Column() {
                Box() {
                    Image(
                        bitmap = BitmapFactory.decodeStream(context.assets.open(stringResource(R.string.cajita_con_estrellas_route)))
                            .asImageBitmap(),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.Transparent)
                            .height(300.dp)
                    )
                    Text(
                        text = stringResource(R.string.empty_favoritos_list_message),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.ExtraBold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.align(BottomCenter)
                    )
                }

                FloatingActionButton(
                    onClick = { scope.launch { scaffoldState.drawerState.open() } },
                    modifier = Modifier
                        .align(CenterHorizontally)
                        .padding(12.dp)
                        .weight(1F, false)
                ) {
                    Icon(Icons.Rounded.Add, contentDescription = null)
                }
            }
        }

    }
}

private fun getFavoritos(viewModel: FavoritosViewModel) {
    viewModel.handleEvent(FavoritosContract.Event.GetFavoritos, null)
}