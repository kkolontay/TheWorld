package com.kkolontay.theworld.ui.screens
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun InfoView(modifier: Modifier = Modifier.fillMaxWidth().padding(16.dp), taps: Int, refresh: () -> Unit, back: Int) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Text("Taps: ")
        Button(onClick = { refresh() }) {
            Text(text = "Refresh")
        }
        Text("Back: ")
    }
}