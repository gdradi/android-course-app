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
fun Screen1(navController: NavController) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("This is Screen 1")
        Button(onClick = { navController.navigate("screen2") }) {
            Text("Go to Screen 2")
        }
    }
}