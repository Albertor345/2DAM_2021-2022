package com.example.firstcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.firstcompose.ui.composables.TextFieldApp
import com.example.firstcompose.ui.theme.LoginComposeTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    DefaultPreview()
                }
            }
        }
    }
}


@Composable
fun DefaultPreview() {

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        val scope = rememberCoroutineScope()
        val snackBarHostState = remember { SnackbarHostState() }
        var name by remember { mutableStateOf("") }
        Scaffold(Modifier.fillMaxHeight()) {
            Column(modifier = Modifier.wrapContentSize(Center)) {
                TextFieldApp(
                    modifier = Modifier.padding(5.dp),
                    value = name,
                    placeHolder = "",
                    onValueChange = {},
                    Icons.Filled.Edit
                )
                Button(onClick = {
                    scope.launch {
                        snackBarHostState.showSnackbar(
                            "Yiipa",
                            "AAAAAAAAAA",
                            SnackbarDuration.Short
                        )
                    }
                }, content = {
                    Row {
                        Icon(Icons.Filled.AccountCircle, contentDescription = "")
                        Text(
                            text = "Login",
                            Modifier
                                .padding(2.dp)
                                .wrapContentHeight(CenterVertically)
                        )
                    }
                }, modifier = Modifier.clickable {

                })
            }
        }

    }


}