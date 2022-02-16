package com.example.logincompose.ui.composables

import androidx.compose.foundation.layout.Spacer
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp

@Composable
fun BottomBar(modifier: Modifier) {
    BottomAppBar(
        modifier = modifier,
        elevation = 5.dp,
        content = { // Leading icons should typically have a high content alpha
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.high) {
                IconButton(onClick = { /* doSomething() */ }) {
                    Icon(Icons.Filled.Menu, contentDescription = "Localized description")
                }
            }
            // The actions should be at the end of the BottomAppBar. They use the default medium
            // content alpha provided by BottomAppBar
            Spacer(Modifier.weight(1f, true))

            IconButton(onClick = { /* doSomething() */ }) {
                Icon(Icons.Filled.Favorite, contentDescription = "Localized description")
            }
            IconButton(onClick = { /* doSomething() */ }) {
                Icon(Icons.Filled.Favorite, contentDescription = "Localized description")
            }
        })
}