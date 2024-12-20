package com.example.mycinema

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun MovieDetailsScreen(modifier: Modifier = Modifier) {
    MoviewDetailsContent()
}

@Composable
private fun MoviewDetailsContent(modifier: Modifier = Modifier) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "AQUI")

    }
    
}