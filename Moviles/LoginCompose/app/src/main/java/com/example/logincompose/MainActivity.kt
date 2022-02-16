package com.example.logincompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.logincompose.ui.theme.LoginComposeTheme

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
                    Column(modifier = Modifier.wrapContentSize(Center)) {
                        TextField(value = "", onValueChange = {}, trailingIcon = {
                            Icon(
                                Icons.Filled.Edit,
                                contentDescription = ""
                            )
                        }, placeholder = { Text(text = "Name") }, maxLines = 1)

                        TextField(value = "", onValueChange = {}, trailingIcon = {
                            Icon(
                                Icons.Filled.Edit,
                                contentDescription = ""
                            )
                        }, placeholder = { Text(text = "Password") }, maxLines = 1)
                        Button(onClick = { }, content = {
                            Row {
                                Icon(Icons.Filled.AccountCircle, contentDescription = "")
                                Text(
                                    text = "Login",
                                    Modifier
                                        .padding(2.dp)
                                        .wrapContentHeight(CenterVertically)
                                )
                            }
                        })
                    }
                }
            }
        }
    }

    private fun login() {

    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        val snackbarHostState = remember { SnackbarHostState() }

        Column(modifier = Modifier.wrapContentSize(Center)) {
            TextField(value = "", onValueChange = {}, trailingIcon = {
                Icon(
                    Icons.Filled.Edit,
                    contentDescription = ""
                )
            }, placeholder = { Text(text = "Name") }, maxLines = 1)

            TextField(value = "", onValueChange = {}, trailingIcon = {
                Icon(
                    Icons.Filled.Edit,
                    contentDescription = ""
                )
            }, placeholder = { Text(text = "Password") }, maxLines = 1)
            Button(onClick = { }, content = {
                Row {
                    Icon(Icons.Filled.AccountCircle, contentDescription = "")
                    Text(
                        text = "Login",
                        Modifier
                            .padding(2.dp)
                            .wrapContentHeight(CenterVertically)
                    )
                }
            })
        }
    }


}