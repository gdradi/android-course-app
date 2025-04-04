package com.example.androidappcourse.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun Screen2(navController: NavController) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("This is Screen 2")
        Button(onClick = { navController.navigate("screen1") }) {
            Text("Go to Screen 1")
        }
    }
}