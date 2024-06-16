package com.kkolontay.theworld.ui.screens.info

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun InfoScreen() {
    Text(modifier = Modifier.padding(16.dp), text = "This is info about this app.")
}