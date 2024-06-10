package com.kkolontay.theworld.ui.screens

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.kkolontay.theworld.api.WorldResponse
import com.kkolontay.theworld.model.Country

@Composable
fun CountryList(countries: WorldResponse, timer: Int, taps: Int, back: Int, refresh: () -> Unit, nextScreen: (Country) -> Unit) {

    when (countries) {
        is WorldResponse.Success -> {

            Column {
                InfoView(taps = taps, refresh = refresh, back = back)
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(16.dp)
                ) {

                    items(countries.list) { country ->
                        Box(modifier = Modifier.clickable { nextScreen(country) }) {
                            CountryItem(country = country)
                        }
                    }
                }
            }
        }
        is WorldResponse.Loading -> {
            Column {
                Text(text = "Loading: ${timer}", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
            }
        }
        else -> {
            Column {
                Text(text = "Error", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
            }
        }
    }
}